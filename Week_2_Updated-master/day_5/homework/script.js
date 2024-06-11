

console.log("script file wire up!!!")



console.log(data)

// 1. allNames:
// Use .forEach to print all persons names in the console.

data.forEach(element => {console.log(element.name);
});

// 2. totalAgeAllPersons:
// Find the total combined age of all persons.

function ageCombined (getAge){
    let age = []
    age = data.map(person => person.age)
    let totalAge = Number(age.reduce((acc, num) => acc + num, 0));
    return totalAge
    
}

console.log(ageCombined(data));

// 3. totalAgeAllPets:
// Find the total combined age of all pets.

function petAgeCombined(getPetAge){
    let petAge = 0;

    data.forEach(element => {
        element.pets.forEach (element2 => {
            petAge += element2.age;
        });
        
    })
    return petAge
}

console.log(petAgeCombined(data));


// 4. oldEnough:
// Use .filter() to create an array of people old enough to be president.



function whoYourFuturePrezIs (person) {
    const namesOfCandidates = data.filter(person => person.age >= 35).map(candidate => candidate.name);
    return namesOfCandidates
}

console.log(whoYourFuturePrezIs(data));

// 5. sadPeople:
// Use .filter() to create an array of "sad" people (people with no pets).

function pplWithNoPets (noPets) {
    const sadPeopleWithNoPets = data.filter(person => person.pets.length === 0).map(noPets => noPets.name);
    return sadPeopleWithNoPets;
}

console.log(pplWithNoPets(data));

// 6. warAndPeace:
// Use.map() to create an array called warAndPeace that has, in each index the string "war" or "peace"
//   * the string "war" if the person at that has BOTH a cat AND a dog.
//   * the string "peace" otherwise.

function catDogTime(pet_status) {
    const warAndPeace = data.map(person => {
        if (person.pets.some(pet => pet.type === 'cat') && person.pets.some(pet => pet.type === 'dog')) {
            return {
                name: person.name,
                pet_status: 'war'
            }
        } else {
            return {
            name: person.name,
            pet_status: 'peace'
        }
        }
    });

    return warAndPeace

}


console.log(catDogTime(data));



// 7. justSpock:
// Create an array of all the pet objects where the pets named is "spock".

function whoHasSpock (petName) {
    let spockOwners = data.map(owner => {
        let spockPet = owner.pets.find(pet => pet.name === 'spock');
        if (spockPet) {
            return {
                name: owner.name,
                pet_name: spockPet.name
            };
        }
        return null;
    }).filter(owner => owner != null); 

    return spockOwners
}
    


console.log(whoHasSpock(data));

// HUNGRY FOR MORE???

// 8. catYears:
// Find the total combined age of all cats (type: "cat").

function petCatAgeCombined(getCatAge){
    let petCatAge = 0;

    data.forEach(element => {
        element.pets.forEach (element2 => {
            if (element2.type === 'cat'){
            petCatAge += element2.age;
            }
        });
        
    })
    return petCatAge
}

console.log(petCatAgeCombined(data));



// 9. combinedAgeOfAllPetsOfDrinkers:
// Find the combined age of all pets belonging to people old enough to legally purchase alcohol

function pplWhoDrinksAndTheirPetAge (person) {
    const drinkers = data.filter(person => person.age >= 21);
    // return drinkers
    let drinkerPetTotalAge = 0
    drinkers.forEach(element => {
        element.pets.forEach (element2 => {
            drinkerPetTotalAge += element2.age;
        })
    })
    return drinkerPetTotalAge
    
}

console.log(pplWhoDrinksAndTheirPetAge(data));




// 10. youngestPets:
// Create an array containing the youngest pet each pet owner has.
// Here's the answer: 
/*
[ { type: 'rock', name: 'herbert', age: 126003219 },
  { type: 'cat', name: 'colby', age: 1 },
  { type: 'guinea pig', name: 'kirk', age: 1 },
  { type: 'dog', name: 'midnight', age: 3 },
  { type: 'dog', name: 'quinn', age: 8 },
  { type: 'dog', name: 'hazel', age: 7 },
  { type: 'monstera', name: 'holey', age: 1 },
  { type: 'goldfish', name: 'betsy', age: 4 },
  { type: 'cat', name: 'rosie', age: 0.005 },
  { type: 'dog', name: 'nacho', age: 1 },
  { type: 'bearded dragon', name: 'ferg', age: 3 },
  { type: 'cockatoo', name: 'spockatoo', age: 33 },
  { type: 'cat', name: 'mija', age: 6 },
  { type: 'cat', name: 'caesar', age: 3 },
  { type: 'cat', name: 'eddie', age: 7 },
  { type: 'cat', name: 'rocky', age: 5 },
  { type: 'snake', name: 'reginald', age: 12 } ]
*/

function yougestPetForEachPerson (youngest) {
    return youngest.filter( person => person.pets && person.pets.length > 0).map (person => {
        const youngestPetPer = person.pets.reduce((minPet, currentPet) => {
            return currentPet.age < minPet.age ? currentPet : minPet;
        }, person.pets[0]);
    
        return {
            type: youngestPetPer.type,
            name:youngestPetPer.name,
            age: youngestPetPer.age
        }

    });
}



console.log(yougestPetForEachPerson(data));


// 11. petNameString:
// Create a string that is all the pets names separated by spaces (order doesn't matter).

function giveMeAllYourPetName (petname) {
    let allDemPetNames = [];
    data.forEach (element => {
        element.pets.forEach(element2 => allDemPetNames.push(element2.name))
    })
    return allDemPetNames.join(' ')
}

console.log(giveMeAllYourPetName(data));