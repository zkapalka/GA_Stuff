
const friends = ["ross", "rachel", "joey", "monica", "phoebe", "chandler"];


for (let i = 0; i < friends.length; i++) {
  console.log("Sending email to ", friends[i])
}

// using for each

function sayHi(name){
    console.log("Hi to you, ", name)
}

function give10DollarsToAPerson(name) {
    console.log("I am giving 10$ to ", name);
  }
  
  friends.forEach(give10DollarsToAPerson)

  friends.forEach(sayHi)