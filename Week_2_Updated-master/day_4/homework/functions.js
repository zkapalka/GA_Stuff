console.log("test");

// Basic Function

// Greeting Function

function yoBro (name) {
    return "Hello what's up " + name
}

let testResults = yoBro("Zach")
console.log(testResults);

// Concatenator

function mergeTwoStrings(s1, s2) {
    return s1 + ' ' + s2
}

let mergeTest = mergeTwoStrings("Baby", "Hit me one more time")

console.log(mergeTest);

// Power Function

function numberToGivenPower (number, exponent){
    return number ** exponent
}

let powerTest = numberToGivenPower(3, 3)

console.log(powerTest);

// Array Sum

function arraySum(arr) {
    return arr.reduce((accumulator, currentValue)=> accumulator+ currentValue, 0);
}

let arrayTest = [1, 2,3,4,5]

console.log(arraySum(arrayTest));


// Function Returning Function

// Counter Function

function functionThatReturnAnotherFunction() {
    return function arraySum(arr) {
        return arr.length
    }
}

let testFunctionReturn = functionThatReturnAnotherFunction();
console.log(testFunctionReturn([1, 2, 3, 4, 5, 6]));

// Function With Side Effects

// Array Modifiers

const problemSixArray = ["I", "Do", "Not", "Like", "Green", "Ham", "Nor", "Green", "Eggs"]

function modifyProblemSixArray() {
    problemSixArray.unshift("Bro")
    problemSixArray.push("No cap")
}

modifyProblemSixArray()
console.log(problemSixArray);

// Complex Challenges

/* Array Filter and Modifier, I wasn't sure if you wanted the returned array with even numbers to be added up because
 the directions weren't clear */

function addAllEvenNumbers (arr) {
    let newArraySum = []; 

    for (let zz = 0; zz < arr.length; zz++){
        if (arr[zz] % 2 === 0) {
            newArraySum.push(arr[zz]);
        }
    }
    return newArraySum.reduce((accumulator, currentValue)=> accumulator+ currentValue, 0);
}

let evenArrayTest = [1, 2 , 3, 4, 5, 6]

console.log(addAllEvenNumbers(evenArrayTest));

// Return Highest, Lowest, Average Salary, this was hard
const employees = [
    { id: 1, name: "John Doe", department: "Engineering", salary: 70000 },
    { id: 2, name: "Jane Smith", department: "Marketing", salary: 65000 },
    { id: 3, name: "Sam Williams", department: "Engineering", salary: 80000 },
    { id: 4, name: "Sandra Lee", department: "Finance", salary: 75000 }
  ];

function salaryInfoReturn (salaryInfo) {
   let salaries = salaryInfo.map(s => s.salary);

   let highestSalary = Math.max(...salaries)
   let lowestSalary = Math.min(...salaries)
   let averageSalary = salaries.reduce((acc, salary) => acc + salary, 0) / salaries.length;

   return {
    highest_Salary: highestSalary,
    lowest_Salary: lowestSalary,
    average_Salary: averageSalary

   };

}

const salaryAnalysis = salaryInfoReturn(employees)

console.log(salaryAnalysis);

