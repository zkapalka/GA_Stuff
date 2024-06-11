# React + Vite

## Question 1

The counter will be two. Because there are two separate increment functions, however, it will all run at once, so, when you click on the button, it won't add just one, but two instead.

## Question 2

It still would be two, however, it's refering to previousCount as a state to hold the increment, but since there are two calls ot that. It is approaching the increment a bit differently since it's calling on a => to store the value and then passing that variable to update the value. 

## Question 3

It's directly mutating the items by pushing bananas. It's a big no-no because you don't want to do unexpected change in React. 

To avoid this, it's better to use a different way such as setItems with a new array to receive the mutated data.

## Question 4

... is a way for you to look into the full scope of an array and give all results in that array and then set it up for however you want to use it as. Without the ... the item array wouldn't be able to pass on the values of apple and oranges.