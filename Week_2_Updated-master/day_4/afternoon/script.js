console.log("connected");

function sayHello () {
    console.log("Hello")
}

sayHello()

function squareMe(n) {
    console.log( n ** 2)
}

squareMe(5)

let name = "Vlad"

function changeName () {
    name = "Bobby Ray"
    
}

changeName()

console.log(name)

let array = [1, 2, 3, 4]

function arrayTuneUp() {
    array.unshift("flabbergasted")
    array.push("flabbergasted")
}

arrayTuneUp()

console.log(array)