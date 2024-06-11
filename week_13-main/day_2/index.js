const add = (a, b) => a + b;
console.log(add(2, 3));


console.log("hi");

// Load Posts
function loadPosts() {
    fetch("http://localhost:8080/posts")
      .then((response) => response.json())
      .then((data) => {
          console.log(data);
          handlePosts(data);
      })
      .catch((error) => {
        console.log("Critical error", error);
      });
  }

  loadPosts();
  
  function handlePosts(data) {
    const postsContainer = document.getElementById("posts-container");
    // Clear previous posts
    postsContainer.innerHTML = "";
    
    // Loop through each post and create a DOM element for it
    data.forEach(post => {
        const postElement = document.createElement("div");
        postElement.classList.add("post");
        
        const titleElement = document.createElement("h2");
        titleElement.textContent = post.postTitle;
        
        const contentElement = document.createElement("p");
        contentElement.textContent = post.postContent;

        const userIDElement = document.createElement("p");
        userIDElement.textContent = "Post ID:" + ' ' + post.postID;
        
        postElement.appendChild(titleElement);
        postElement.appendChild(contentElement);
        postElement.appendChild(userIDElement);
        postsContainer.appendChild(postElement);
    });
  }