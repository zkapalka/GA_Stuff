# Week 12 Project

## Summary
This is the final project for Spring. We have two separate microservices, the main job board application with 5 entities
- Address
- User
- Comment
- Post
- Tags

And the notification microservice with one entity
- Notification

The flow is that there can't be posts, comments, or tags without an user, so we create an user first. And the user will create a post, and then comment on it, and the comment will trigger notifications to be saved on the notification microservice and we should be able to use endpoints to obtain the JSON objects from endpoints. 

## Sample JSON Requests

###USER
{
        "userName": "test",
        "email": "test@example.com",
        "password": "hashed_password",
        "profilePictureUrl": "https://example.com/profile.jpg",
        "bio": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien eu libero pharetra convallis."
}

### ADDRESS 
{
"street": "123 Main St",
"city": "Anytown",
"state": "Somestate",
"zipCode": "12345",
"country": "Somecountry"
}

### POST : Must have a valid userID otherwise this will fail
{
"postTitle": "Post 4",
"postContent": "test mctesty",
"postLikes": 4,
"mediaUrl": "example.com",
"userID": 1
}

### COMMENT : Must have a valid postID and userID otherwise this will fail
{
  "commentText": "Your comment text goes here",
  "commentLikes": 10,
  "postID": 1,
  "userID": 1
}

### TAG : Must have a valid postID otherwise this will fail
{
"tagName" : "test",
"tagDescription": "This is fleek",
"postID" : 1
}

### USER 
{
        "userName": "test",
        "email": "test@example.com",
        "password": "hashed_password",
        "profilePictureUrl": "https://example.com/profile.jpg",
        "bio": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien eu libero pharetra convallis."
  } 


## Endpoint Testing

Below are valid endpoints testing

### USER 8080

* 		GET /users : Retrieve a list of all users. (so we can see who has signed up)
* 		GET /users/{userId} : Retrieve a specific user by ID.
* 		GET /users/{userId}/posts : Allows us to see the posts of a specific user
*       GET /users/{usersId}/comments : Allows us to see the comments of a specific user
*       GET /user/{id}/notifications : Allows us to see the notifications for a specific user
*       GET /users/{userId}/address : Retrieve the address of a specific user
* 		POST /users : Create a new user- (when user creates an account)
*       POST /users/{userId}/address : Create the address of a specific user.
* 		PUT /users/{userId} : Update an existing user by ID.
*       PUT/users/{userId}/address : Update the address of a user.
* 		DELETE /users/{userId} : Delete a user by ID.

### ADDRESS 8080

*       GET/address : Get all addresses
*       GET/address/{id} : Get address by id
*       POST/addresses : create address
*       PUT /addresses/{id} : update address
*       DELETE/address/{id} : delete address by ID
*                 

### POST 8080

* 		GET /posts : Retrieve a list of all posts.
*       GET /posts/{postId} : Retrieve a specific post by ID.
*       GET /posts/{postId}/comments : Retrieve all comments for a specific post.
*       GET /post/{postID}/tag : Retrieve the tag of a specific post
*       POST /posts/{postId}/comments : Add a new comment to a post.
* 		POST /posts : Create a new post, must have an existing user first!
* 		PUT /posts/{postId} : Update an existing post by ID.
* 		DELETE /posts/{postId} : Delete a post by ID.
            

### COMMENT 8080

*       GET/comments : Get all addresses
*       GET/comments/{id} : Get comment by id
*       POST/comments : create comment, must have an existing user and post!
*       PUT /comment/{id} : update comment by ID
*       DELETE/comment/{id} : delete comment by ID

### TAGS 8080

*       GET/tags : Get all tags
*       GET/tags/{id} : Get tag by id
*       POST/tags : create tag, must have an existing post!
*       PUT /tags/{id} : update tag by id
*       DELETE/tags/{id} : delete tag by ID

### NOTIFICATION 8081
* 		GET /notifications: Retrieve a list of all notifications
*       POST /notifications: Create a new tag.
* 		PUT /notifications/{id}: Update an existing tag by ID.
* 		DELETE /notifications/{id}: Delete a tag by ID.          


## What I Learnt

- I thought about using a generic service layer to let me be able to borrow from one service layer for basic CRUD, but I realized that I would have to override every single thing, so, that was pointless, so I dropped it, but then I found out that I was approaching it the wrong way, my mentor showed me how to use an abstract repo, however, I wasn't able to do it in time. 

- I was deliberate about using @Column because I’ve noticed that if I don’t leave the id variable as literally id, the connection when using CRUD can get really awkward and does a bad job of recognizing it. Now, we can refactor it to a different name, but I’ve noticed that if I don’t use anything other than id, it can work very awkwardly with the unit tests. And then I eventually decided on removing it since I noticed that it was affecting my code and I was being inconsistent with it, so I made the decision to remove all of it just so that we could ensure that nothing was impacting the model variables. I did learn that @Column can affect your DTO's, which helped factor into my decision.

- IntelliJ does a great job of making small suggestions such as using isEmpty instead of ! isPresent() on methods, and they also lets you know what methods and imports are being used and what's not.  Example below

                if (!postService.getPostById(id).isPresent())   And this can be changed to 
                (postService.getPostById(id).isEmpty())


- One thing I wanted to do is to make this more bulletproof by building in exceptions and data validation for my services and my controllers and I wanted to ensure that both layers have their own approach to handling errors. Unfortunatley, I ran out of time to try to do @Validation. But my approach was that by ensuring that both ends have their approaches, we can reduce the risk of user errors and accidentally breaking stuff. It's one thing to have an application break with one user, and another thing to have an application break with 1,000 users. 

- For the controller I wanted to focus on responseEntity and HTTP responses since I felt like that made more sense and services, I wanted to focus on try/catch for the services since the services are what actually runs the controller methods.

- One thing I still absolutely struggle with is DRY since I feel like I’m repeating myself a lot, but I’m just not sure exactly how to yet.

-  I ran into something interesting with Postman. Specifically two things

1. I was checking for duplicate ID’s like this 

            @PostMapping
                public ResponseEntity<Address> createAddress(@RequestBody Address address) {
                // Check if the address ID already exists
                Optional<Address> existingAddressOptional = addressService.getAddressById(address.getId());    if (existingAddressOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
                Address createdAddress = addressService.createAddress(address);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
            }

However, Spring handles the risk of duplicate ID by generating ID for us, so there’s no need to do that, and we can just do

            @PostMapping
            public ResponseEntity<Address> createAddress(@RequestBody Address address) {
                Address createdAddress = addressService.createAddress(address);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
            }

However, it's a valid response to data validate this especially if users have the ability to "create" their ID, however, if users have no way of touching the user id, then it's a moot point. 

- You don’t always need a service method for a controller method

- Some built-in methods that work great with List does not work great with Set. Kathy and I found that add() doesn’t work at all with Set and that was why my posts weren’t showing up at first when I was first using Set

- while testing, constructor parameters can be affected by MockitoAnnotations.openMocks(this) in testing while using @BeforeEach

## Suggested improvements

- Update repo to use one abstract repo that all other repos/services can extend so that that we only have to write the basic CRUD methods in one place instead of across multiple services. Would reduce the amount of lines and would reduce the risk of errors. You can @Override methods as needed or just create specific service methods as needed

- Use Validation on all models such as required length for Username, max length allowed for post text, comment text and etc. That way we can reduce the chance of accidentally breaking stuff.

- Focus on testing first and then writing methods to measure against that test