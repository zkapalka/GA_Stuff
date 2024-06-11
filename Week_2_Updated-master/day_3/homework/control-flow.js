let score = 0;

console.log("connected")


function getGrade (score){
    if (isNaN(score) || score < 0 || score > 100 ){
        return "Please put in a valid numerical score from 0 to 100"
    } else if (score >= 90) {
        return "You got an A! Outstanding!"
    } else if (score <= 89 && score >= 80) {
        return "You got an B. That's a solid one"
    } else if (score <= 79 && score >= 70) {
        return "You got an C. That's getting dicey"
    } else if (score <= 69 && score >= 60) {
        return "You got an D. Bro."
    } else {
        return "That's a F. 💀💀💀"
    }
}

console.log(getGrade(score))