/* VARIABLES*/
const logInForm = document.querySelector('#loginForm')
const registrationForm = document.querySelector('#registerForm')
const modeToggle = document.getElementById('modeToggle');
const modeText = document.querySelector('.light-to-dark');

/* EVENTS*/
logInForm.addEventListener('submit', redirectToAnotherPage);
modeToggle.addEventListener('change', lightToDarkMode)
registrationForm.addEventListener('submit', registerAndStoreCredentials)


/* FUNCTIONS */
//This takes you to the home page after you log-in and check your authentication
function redirectToAnotherPage(event) {
    event.preventDefault();
    const usernameCheck = localStorage.getItem('username');
    const passwordCheck = localStorage.getItem ('password')
    if ( usernameCheck === document.getElementById('username').value && passwordCheck === document.getElementById('password').value ) {
        window.location.href="index.html"
    } else if (usernameCheck !== document.getElementById('username').value){
        alert("Check your username, bro")
    }
    else if (passwordCheck !== document.getElementById('password').value){
        alert("Check your password, broseph")
    } else {
        alert("You may need to make a new account")
    }
}

function googleAlert() {
    alert("Google Log-in Button clicked")
}

function facebookAlert() {
    alert("Facebook Log-in Button clicked")
}

//toggle dark mode off and on and store preferences in local storage 
function lightToDarkMode() {
    const currentMode = document.body.classList.contains('dark-mode');
    if (currentMode) {
        localStorage.setItem('darkMode', 'disabled')
    } else {
        localStorage.setItem('darkMode', 'enabled')
    } 

    document.body.classList.toggle('dark-mode');
    modeText.textContent = document.body.classList.contains('dark-mode') ? 'Dark Mode' : 'Light Mode';
    
}

function registerAndStoreCredentials() {
    // Get values from registration form fields and store it
    const username = document.getElementById('newUsername').value;
    const password = document.getElementById('newPassword').value;

    localStorage.setItem('username', username);
    localStorage.setItem('password', password);

    alert("Thank you for registering. Now, please log in with your credentials")
}