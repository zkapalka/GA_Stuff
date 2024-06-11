# Capstone Overview

This project is designed to be the capstone for the General Assembly bootcamp, and this project consists of a front-end built with React and the backend with Spring. 

Ad a Deaf gamer, I've always been mindful of how my deafness can affect my experience with playing games. Over the years, I've learned that there are some games and some game genres that just doesn't mesh well with my capacity and my preferences. 

An example is that I never got into Call of Duty or FPS games because those games typically rely heavily on audiological cues to identify player locations in-game. Additionally, not all games are accessible, for example, Star Wars Battlefront 1 and 2 were known to have serious problems with captions such as it being too difficult to read in-game or just simply not showing up. This resulted in a poor experience for me. And I've always wanted to be able to have a way for other Deaf gamers to share their experience as far as "Hey, this game is good for Deaf gamers because it has captions, visual cues and etc. 

Deaf gamers typically are split in Deaf and Deaf/Blind gamers, however for the scope of this project, I will focus on Deaf gamers only, and it is my hope that in the future, I can refactor this project to be inclusive of Deaf/Blind gamers. 

The flow of this project.
- User create an account/log in
- User browse/filter by list of games called via API
- User select game and the app will have a pop-up where users can fill a form with comments and select from a list of accessibilities feature that the game has and then rank the game on how accessible it is for Deaf gamers from 1 star to 5 with 1 star being the worst
- The form will then save to PostreSQL in a separate table 
- User will have a separate page where they can look at the games where they reviewed
- User can edit profile as needed

## Giant Bomb API

First and foremost, all credits goes to Giant Bomb for their excellent API and the work that they have put in their API. Their API made my life so that much more easy and their documenation made it a breeze to set up and convert their feed from XML to JSON.

https://www.giantbomb.com/api/

## Front-End

I made a deliberate decision to ensure that as many stuff were done on the backend as far as validation, data storage and etc goes because I wanted to be able to really DRY myself and to cut back on the risks of bugs or unexpected behaviors. An example is by using the inbuilt validation rules used in Spring instead of applying any validation on the React end. 

I used localStorage to store several info such as userID, isDarkMode, userInitials and isAuthenticated as a way to ensure that the info is shared all across the components more efficiently without worrying about getting dragged down by trying to figure out how to pass those as props.

## Back-end

Thanks to my Spring project, I was much more comfortable tacking Spring this time around and was able to really finish and wrap stuff on the backend with far less time than I needed previously. 

One unexpected issue I did run in was using the Giant Bomb API, I originally wanted to develop a method in Spring that will use the game controller and then populate the game table, unfortunately, it didn't work out the way I wanted it to due to the Giant Bomb API reacting to Spring as a scrapper and blocking access, so, I had to pivot and re-build some of the information and methods all over Spring to work with React calling on the Giant Bomb API and it was able to populate the page with the results due to React not acting as a scrapper. 

But thanks to that API, I was able to devise a method to ensure that users could click on a given game, and then that would trigger a review that would then allow users to input their reviews and then share it with logged in users. 

### Thoughts

For the most part, I was able to mirror a lot of the stuff I did for my Spring project in this capstone mostly because I was able to just "recycle" a lot of stuff as far as set-up, CRUD, and stuff I've learned. However, I've learned that I had to switch around some stuff up due to some stuff not working the way I wanted it to such as Spring being unable to call on the Giant Bomb API.  But with that being said; this capstone definitely taught me the importance of working to ensure that your code are modular, and how to pivot if something isn't working and be willing to accept a workable solution instead of searching of the most optimal solution, and sometimes the workable solution is the most optimal solution. 

### Entities
- User
- Review
- AccessibilityOptions
- Games

User is self explantory that generate ID and registration date and accept first name, last name, email, password, and bday

Review accept rating (1 to 5), and comments and has a one to many relationship with user and one to many relationship with Game

Game contains information such as publisher, genre, and name

AccessibilityOptions includes information such as captions, has visual cues, sound effect in captions and skippable mini games with audiological cues.

I wanted to make sure that accessibilityoptions and Review had a one-one relationship and I was able to achieve that by utilizing DTO to ensure that each review will include the accessibillity options and that it's not feasible for an accessibility option to be created on its own since I want accessibility options to be a sub-selection of review itself.

I also wanted each review to be able to reference to user and game and then tie those reviews to each entities.

LogIn and SignUpRequest functions as DTO and both have validation so that we can ensure that the users will get a clear message if something goes wrong

### Relationship

One-To-One: Review to AccessibilityOptions
One-To-Many: User to Review, Game to Review
Many-To-Many: Game to AccessibilityOptions

WARNING: Defining one to one relationship needs to be done correctly because it makes a difference if you do it bi-directional vs unidirectional! I was having issues with getting access options to save to review, and it was because I defined the relationship in both models. However, if I only definied it to Review to access options, I wouldn't have ran into that.

With regards to Review, I made the decision to do one-one with Review and Accessibility Options because I wanted to ensure that the whole review aspect had two separate areas that could be easily updated as needed. 

## Testing

### Backend

Testing overall took less time to set up since I was focused mostly on addressing unique scenarios. However, if I could go back and redo it, I would try to standardize it a bit more so that I can ensure that we have our basic tests and our unique test scenarios created and deployed to catch any issues.

I found that AI was really invaluable in helping me fine-tune some of my test cases, however, it's still important to understand how some test methods needs to be modified.

An example is below

                I'm using DataAccessException for all of my service layers and while testing
                I found that the best way for testing to recognize that you're using DataAccessException is to do it like this.
                Look at the line marked with "!!!" on both end.

                ```
                @Test
                void findById_DataAccessException() {
                    // Mock repository method to throw a TransientDataAccessException directly
                !!! when(accessibilityOptionRepository.findById(anyLong())).thenThrow(new DataAccessException("Test Exception") {}); !!!

                    // Perform service action
                    ResponseEntity<AccessibilityOptions> response = accessibilityOptionService.findById(1L);

                    // Verify repository method was called
                    verify(accessibilityOptionRepository, times(1)).findById(anyLong());

                    // Verify response
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                    assertNull(response.getBody());
                }
                ```
                instead of

                ```
                @Test
                void findById_DataAccessException() {
                    // Mock repository method to throw a TransientDataAccessException directly
                !!! when(accessibilityOptionRepository.findById(anyLong())).thenThrow(DataAccessException.class); !!!

                    // Perform service action
                    ResponseEntity<AccessibilityOptions> response = accessibilityOptionService.findById(1L);

                    // Verify repository method was called
                    verify(accessibilityOptionRepository, times(1)).findById(anyLong());

                    // Verify response
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                    assertNull(response.getBody());
                }
                ```

                For whatever reason, it looks like testing doesn't have the ability to directly interact with DataAccessException itself, and you have to create an instance of it in order to use it for testing

### Frontend 

I don't feel super confident about using Jest at this point, but I can definitely see the merits just like what we have for Spring test.

This section is a bit sparse because I don't know that much about Jest yet compared to Spring testing. But the nice thing is that it allows us to essentially set up rails like in a bowling lane where you can pop up the rails and make sure that the bowling ball doesn't fall in the gutter. If the ball falls in the gutter while the rail is up, then we know that something has seriously gone wrong and it warrants a review.

### Postman Requests

AccessibilityOptionsController - all CRUD tested. Updated to only POST with review

AuthController - Only set up to POST sign-up and log-in, cannot handle any other users actions

        Sign-Up 
            POST: {
            "username": "john_doe",
            "password": "securePassword123",
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "birthDate": "1990-01-01"
            }

        Log-In

            meant to succeed
            POST: {
            "email": "john.doe@example.com",
            "password": "securePassword123"
            }

            meant to fail
            POST: {
            "email": "test@deed.com",
            "password": "securePassword123"
            }


GameController ALL CRUD TESTED
            POST: {
            "name": "Awesome Game",
            "publisher": "Great Publisher",
            "genre": "Action"
            }

ReviewController - ALL CRUD TESTED

    Review object capture user ID and game ID and then add accessibility options as separate object



            POST {
            "rating": 4,
            "comment": "Great game!"
            }

        User/Game/Accessibility Review
            POST: {
            "userId": 1,
            "gameName": "test game",
            "rating": 4,
            "comment": "This game is really fun!",
            "accessibilityOptions": {
                "hasSubtitles": true,
                "depictsSoundEffectsInCaptions": false,
                "hasVisualCues": true,
                "hasCleanUI": false,
                "hasSkippableAudioMiniGames": true
                }
            }
        

UserController ALL CRUD TESTED - This controller cannot create users, only RUD stuff


## Additional Thoughts 

This was frankly overwhelming at first, but thankfully, one of the biggest lessons that we've learned so far is to break down stuff in smaller pieces, and by utilizing the principles of SWE with working with blocks instead of a whole project, I was able to make steady progress on this and make good time on my Spring as compared to my previous Spring project.

Working on the captsone definitely helped me to understand how React work, especially with useState and useEffect, however, I fully admit that my understanding of set and handle can definitely use some reinforcement.

But in all, this was a really nice project to really showcase what I do know and I'm really pleased with how much I've came in those last 16 weeks. 

### Validation

I really wanted to go above and beyond to really make it impossible for an invalid account to be created such as email with no .com at end and for none of the user profile to be blank.

However, I didn't want to force a massive if/else so, I was able to use @NotBlank, @Valid and @Validated to help evaluate the validation of a sign-up and because of that validation of the sign-up, it allows us to not worry about having to validate users. 

Some things I've learned about @NotBlank, it only works with String, not with numbers so stuff like ID and birth date are out. So, we can do @NotNull instead. 

Password validation is something I went back and forth on. I realized that my original approach to validation was pretty simplistic, and to cut down on malicious attempts, I had to force users to improve their password security, so I did that by implementing strict password requirements and requiring users to input their passwords twice before they can register.

### Using AI and SO and Google and other resources.

One painful thing I've learned over this capstone is to make sure that whatever 3rd party you're using as far as React, Spring and etc. Make sure that you know that stuff can change at a blistering fast pace. 

For example, I was struggling with using React-Router-Dom to get my pages to render, and I could only make it render with a hard refresh. I later learned that I was using 5 for RRD and not 6, and some stuff in RRD 5 were deprcated, and obviously not supported anymore, so, by reading documentation and updating my tags, I got stuff to work well.

CHECK DOCUMENTATIONS

Using AI is incredibly powerful, however, many of them are time-gated, such as ChatGPT3.5 being gated at Jan 2022 so, some information that was given that looks good actually are outdated, and the information is more than 2 years old at that point, so, with that in consideration, when we use AI, we need to be mindful of its limitation and use it as a tool to help with some aspects.

Google and SO have their own role as far as resources being used for this. And thanks to Google and other articles given to me by mentors, I was better able to understand exactly how people are maintaining their documentation and how they pass out information because for example, I was having issues with my React rendering correctly and if not for Chad and his articles, I wouldn't have figured out how to ensure that I'm using the correct version for libraries such as react-router-dom. 

