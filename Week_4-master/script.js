/* VARIABLES*/
const skillForm = document.getElementById('skillForm');
const skillInput = document.getElementById('skillInput');
const skillSectionContent = document.querySelector('.skill-section-content');
const sortButton = document.querySelector('.sort-button')
const searchInput = document.getElementById('searchInput');
const searchButton = document.querySelector('.search-container button')
const zenQuotes = "https://corsproxy.io/?https://zenquotes.io/api/quotes"
const inspirationalPostList = document.querySelector('.inspirational-post-list');
let posts = [];
let reversedPostarray = false;

/* EVENTS*/
skillForm.addEventListener('submit', appendSkillsToDiv);
sortButton.addEventListener('click', sortPostArray)
searchButton.addEventListener('click', searchPosts);
document.addEventListener('DOMContentLoaded', handleDarkModeChange)
document.addEventListener('DOMContentLoaded', attachNameUnderProfile)

/* FUNCTIONS */

/* ASIDES STUFF  */
//This lets you add skills to the skill section in the index.html
function appendSkillsToDiv(event) {
    event.preventDefault();

    // Get the entered skill value
    const enteredSkill = skillInput.value;

    if (enteredSkill.trim() !== '') {
        // Create a new li for the skill
        const skillListItem = document.createElement('li');
        skillListItem.textContent = enteredSkill;
        
        // Append the li to the skill-section-content
        skillSectionContent.appendChild(skillListItem);

        // Clear the input field after submitting
        skillInput.value = '';
    }
}

  // This function will handle the Zen Quotes API
  function handleZenQuotesAPI(data) {
    const quotes = data
    
    inspirationalPostList.innerHTML = '';
    const randomIndex = Math.floor(Math.random()* quotes.length)
    const randomQuote = quotes[randomIndex]

    if (randomQuote) {
      const setUpQuote = document.createElement("p")
      setUpQuote.textContent = `${randomQuote.q}`

      const setUpAuthor = document.createElement("p")
      setUpAuthor.textContent = `Author - ${randomQuote.a}`

      inspirationalPostList.appendChild(setUpQuote)
      inspirationalPostList.appendChild(setUpAuthor)
    }

}


  // This displays a new zenQuote randomly
 function getZenQuoteAPI(url) {
    fetch(url)
      .then((responses) => responses.json()) 
      .then((data) => {console.log(data); handleZenQuotesAPI(data)})
      .catch((err) => console.log(err));
  }

// DARK MODE STUFF
// function applyDarkModeStyles() {
//     document.body.classList.add('dark-mode');
//   }

// function removeDarkModeStyles() {
//     document.body.classList.remove('dark-mode');
//   }

function handleDarkModeChange() {
    const darkModePreference = localStorage.getItem('darkMode');

    if (darkModePreference === 'enabled') {
      document.body.classList.toggle('dark-mode')
  }
}

// POSTS, COMMENTS, LIKES AND DELETE
function addPost() {
    const postInput = document.getElementById('post-input');
    const postText = postInput.value.trim();
  
    if (postText !== '') {
      const post = {
        text: postText,
        likes: 0,
        comments: [],
        timestamp: new Date().getTime()
      };
  
      posts.unshift(post); // Add the new post to the beginning of the array
  
      displayPosts();
      postInput.value = ''; // Clear the input field
    }
  }

  // This will set up posts and comments, additionally, it calls likeComment, likePost, and set a max of 4 comments per post
function displayPosts() {
    const postList = document.getElementById('post-list');
    postList.innerHTML = '';

    posts.forEach((post, postIndex) => {

      // Create a div with post
        const postElement = document.createElement('div');
        postElement.classList.add('post');

        //This create the post text area
        const paragraph = document.createElement('p');
        paragraph.textContent = post.text;
        postElement.appendChild(paragraph);

        //Creates the comment div
        const commentsDiv = document.createElement('div');
        commentsDiv.id = `comments-${postIndex}`;


        //Creates the like button for post and attach the like post function
        const likePostBtn = document.createElement('button');
        likePostBtn.innerHTML = `Like (${post.likes})`;
        likePostBtn.onclick = () => likePost(postIndex);
        postElement.appendChild(likePostBtn);

        //Creates the close button and attaches the close button function
        const closeBtn = document.createElement('span');
        closeBtn.innerHTML = '&times;';
        closeBtn.classList.add('close-btn');
        closeBtn.addEventListener('click', () => removePost(postIndex));
        postElement.appendChild(closeBtn);

        // Limit the number of displayed comments to 4 and creates the comments div along with the like and close button, similar to posts
        const maxComments = Math.min(4, post.comments.length);

        for (let i = 0; i < maxComments; i++) {
            const commentDiv = document.createElement('div');
            commentDiv.classList.add('comment');

            const commentText = document.createTextNode(post.comments[i].text);
            commentDiv.appendChild(commentText);

            const closeBtn = document.createElement('span');
            closeBtn.innerHTML = '&times;';
            closeBtn.classList.add('close-btn');
            closeBtn.addEventListener('click', () => removeComment(postIndex, i));
            commentDiv.appendChild(closeBtn);

            const likeCommentBtn = document.createElement('button');
            likeCommentBtn.innerHTML = `Like (${post.comments[i].likes})`;
            likeCommentBtn.onclick = () => likeComment(postIndex, i);
            commentDiv.appendChild(likeCommentBtn);

            commentsDiv.appendChild(commentDiv);
        }

        postElement.appendChild(commentsDiv);

        // Display comment button only if the maximum number of comments is not reached
        if (post.comments.length < 4) {
            const inputDiv = document.createElement('div');
            const commentInput = document.createElement('textarea');
            commentInput.placeholder = 'Type your comment here...';
            inputDiv.appendChild(commentInput);

            const commentBtn = document.createElement('button');
            commentBtn.innerHTML = 'Comment';
            commentBtn.onclick = () => addComment(postIndex, commentInput.value);
            inputDiv.appendChild(commentBtn);

            postElement.appendChild(inputDiv);
        }

        //Attaches the actual post element to the div postList
        postList.appendChild(postElement);
    });
}

// This remove posts at the index level
function removePost(index) {
    console.log(`Post at index ${index} was removed`);
    posts.splice(index, 1);
    displayPosts();
}

// This allows you to remove comment at the index level
function removeComment(postIndex, commentIndex) {
    // Check if the postIndex is valid
    if (postIndex >= 0 && postIndex < posts.length) {
        const post = posts[postIndex];
        
        // Check if the commentIndex is valid
        if (commentIndex >= 0 && commentIndex < post.comments.length) {
            // Remove the comment from the post's comments array
            post.comments.splice(commentIndex, 1);
            
            // Update the display
            displayPosts();
        }
    }
}

// This allows you to comment on a given post
function addComment(postIndex, commentText) {
    if (commentText.trim() !== '') {
      const newComment = {
        text: commentText,
        likes: 0,
      };
      posts[postIndex].comments.push(newComment);
      displayPosts();
    }
}

//This allows you to like posts individually
function likePost(index) {
    posts[index].likes++;
    displayPosts();
  }

  // This let you like comments individually
  function likeComment(postIndex, commentIndex) {
    if (commentIndex >= 0 && commentIndex < posts[postIndex].comments.length) {
        posts[postIndex].comments[commentIndex].likes++;
        displayPosts();
    }
}


  // This will sort the array by oldest or newest
  function sortPostArray() {
    if (posts.length > 0) {
      reversedPostarray = !reversedPostarray; // Toggle the state
  
      if (reversedPostarray) {
        posts.sort((a, b) => a.timestamp - b.timestamp);
        sortButton.textContent = 'Sorted by oldest posts at top';
      } else {
        posts.sort((a, b) => b.timestamp - a.timestamp);
        sortButton.textContent = 'Sorted by newest post at top';
      }
  
      displayPosts();
    }
  }

  // This let you search by posts
  function searchPosts() {
    const searchTerm = searchInput.value.toLowerCase().trim();
  
    if (searchTerm !== '') {
      const filteredPosts = posts.filter(post => post.text.toLowerCase().includes(searchTerm));
  
      if (filteredPosts.length > 0) {
        posts = filteredPosts;
        displayPosts();
      } else {
        alert('No matching posts found.');
      }
    } else {
      alert('Please enter a search term.');
    }
  }

  function attachNameUnderProfile() {
    const userName = localStorage.getItem('username')
    const userNameLocation = document.getElementById('username-location')
    userNameLocation.textContent = `Hi, ${userName}`
    
  }

  // Initial display
displayPosts();
getZenQuoteAPI(zenQuotes);

