/* 
VARIABLES
*/

const jokesURLAPI = "https://official-joke-api.appspot.com/random_ten"
const catFactsURLAPI = "https://catfact.ninja/facts?limit=10"
const memesURLAPI = "https://api.imgflip.com/get_memes"
const loadMemeButton = document.getElementById('memes-section')
const loadJokeButton = document.getElementById('joke-section')
const loadCatFactsButton = document.getElementById('cat-section')
const gallery = document.querySelector(".gallery")

/* 
EVENTS
*/

//This is for the memes
loadMemeButton.addEventListener("click", loadMemeImages);
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("refreshMemeButton")) {
        refreshMemeImagesFunction();
    }
});

//This is for the jokes
loadJokeButton.addEventListener("click", fetchJokes);
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("refreshJokeButton")) {
        refreshJokeFunction();
    }
});

//This is for the cat facts
loadCatFactsButton.addEventListener("click", fetchCatFacts);
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("refreshCatFactsButton")) {
        refreshCatFactsFunction();
    }
});

/* 
FUNCTIONS
*/

// Meme sections

//This handles the function to create button and images and set it up for the meme stuff
function handleMemeImages(data){
    const memes = data.data.memes

    gallery.innerHTML = ''
    const randomIndex = Math.floor(Math.random() * memes.length)
    const randomMeme = memes[randomIndex];

    if (randomMeme) { 
        const button = document.createElement("button")
        button.textContent = "Refresh";
        button.classList.add("refreshMemeButton");

        const backButton = document.createElement("button")
        backButton.textContent = "Home"
        backButton.id = "goBackID"
        backButton.addEventListener("click", goBackToOriginalHTML);

        const img = document.createElement("img");
        img.src = randomMeme.url;

        gallery.appendChild(img);
        gallery.appendChild(button)
        gallery.appendChild(backButton)
    }
}

//This fetches the API and runs the function for handling meme images
function loadMemeImages(){
    fetch(memesURLAPI)
        .then((response) => {return response.json();})
        .then((data) => {console.log(data); handleMemeImages(data)})
        .catch((err) => console.log(err))
}

//This is called by the refresh button for the meme stuff
function refreshMemeImagesFunction() {
    loadMemeImages();
}


// Joke section
function fetchJokes(){
    fetch(jokesURLAPI)
        .then((response) => {return response.json();})
        .then((data) => {console.log(data); handleJokes(data)})
        .catch((err) => console.log(err))
}

function handleJokes(data){
    const jokes = data

    gallery.innerHTML = ''
    const randomIndex = Math.floor(Math.random() * jokes.length)
    const randomJoke = jokes[randomIndex];

    if (randomJoke) { 
        const setupParagraph = document.createElement("p");
        setupParagraph.textContent = `${randomJoke.setup}`;

        const punchlineParagraph = document.createElement("p");
        punchlineParagraph.textContent = `${randomJoke.punchline}`;

        const button = document.createElement("button")
        button.textContent = "Refresh";
        button.classList.add("refreshJokeButton");

        const backButton = document.createElement("button")
        backButton.textContent = "Home"
        backButton.id = "goBackID"
        backButton.addEventListener("click", goBackToOriginalHTML);

        gallery.appendChild(setupParagraph)
        gallery.appendChild(punchlineParagraph)
        gallery.appendChild(button)
        gallery.appendChild(backButton)
    }
}

function refreshJokeFunction() {
    fetchJokes();
}

// Cat fact sections 
function fetchCatFacts(){
    fetch(catFactsURLAPI)
        .then((response) => {return response.json();})
        .then((data) => {console.log(data); handleCatFacts(data)})
        .catch((err) => console.log(err))
}

function handleCatFacts(data){
    const catFacts = data.data

    gallery.innerHTML = ''
    const randomIndex = Math.floor(Math.random() * catFacts.length)
    const randomcatFacts = catFacts[randomIndex];

    if (randomcatFacts) { 
        const button = document.createElement("button")
        button.textContent = "Refresh";
        button.classList.add("refreshCatFactsButton");

        const backButton = document.createElement("button")
        backButton.textContent = "Home"
        backButton.id = "goBackID"
        backButton.addEventListener("click", goBackToOriginalHTML);

        const catParagraph = document.createElement("p");
        catParagraph.textContent = `${randomcatFacts.fact}`

        gallery.appendChild(catParagraph);
        gallery.appendChild(button)
        gallery.appendChild(backButton)
    }
}

function refreshCatFactsFunction() {
    fetchCatFacts();
}

//This is for the home button that lets you go back to the start.
function goBackToOriginalHTML(){
    window.location.href = "index.html"
}
