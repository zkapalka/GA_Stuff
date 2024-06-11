// This set up the document to only run javascript once everything is loaded.
document.addEventListener('DOMContentLoaded', function () {

//GLOBAL VARIABLE SECTION
    const display = document.querySelector('.display');
    const buttons = document.querySelectorAll('.button');
    const equalsButton = document.getElementById('equals');
    const clearButton = document.getElementById('clear');
    const historyDiv = document.querySelector('.history');
  
    let currentInput = '';
    let currentOperator = '';
    let firstOperand = '';
    let result = null;
    let calculationHistory = [];
  
//EVENT SECTIONS

// This set up 
    equalsButton.addEventListener('click', calculateResult);
    clearButton.addEventListener('click', resetCalculator);
  
    buttons.forEach(button => {
      button.addEventListener('click', function () {
        const buttonText = button.textContent;
  
        if (!isNaN(buttonText)) {
          currentInput += buttonText;
          updateDisplay();
        } else if (isOperator(buttonText)) {
          handleOperator(buttonText);
        }
      });
    });

/* FUNCTION SECTION
 the real meat of this script*/

// Check to see if the input is an operator or not
    function isOperator(input) {
      return ['+', '-', '*', '/'].includes(input);
    }

 // This allows you to check to see if the input is empty or not. If it is not empty then 
 // it checks to see if the first operand is empty, and then assign the current input to the 
 // first operand
    function handleOperator(operator) {
      if (currentInput !== '') {
        if (firstOperand === '') {
          firstOperand = currentInput;
          currentInput = '';
          currentOperator = operator;
        } else {
          calculateResult();
          currentOperator = operator;
        }
      }
    }
  

// This will figure out the operations and return it. It also re-init the variables to
// let you do more calcuations
    function calculateResult() {
      if (currentInput !== '' && firstOperand !== '' && currentOperator !== '') {
        const operand1 = parseFloat(firstOperand);
        const operand2 = parseFloat(currentInput);
  
        switch (currentOperator) {
          case '+':
            result = result !== null ? result + operand2 : operand1 + operand2;
            break;
          case '-':
            result = result !== null ? result - operand2 : operand1 - operand2;
            break;
          case '*':
            result = result !== null ? result * operand2 : operand1 * operand2;
            break;
          case '/':
            result =
              result !== null
                ? result / operand2
                : operand2 !== 0
                ? operand1 / operand2
                : 'You done messed up A-A-ron. Press C to start over';
            break;
        }
        calculationHistory.push(`${operand1} ${currentOperator} ${operand2} = ${result}`);
        updateDisplay(result);
        updateHistory();
      }
  
      currentInput = '';
      firstOperand = '';
      currentOperator = '';
    }


// This reset the calc for you
    function resetCalculator() {
      currentInput = '';
      currentOperator = '';
      firstOperand = result !== null ? result : '';
      result = null;
      updateDisplay();
    }
  

// This updates the calcu display field with the most updated value
    function updateDisplay(value = currentInput) {
      display.textContent = value !== null ? value : '0';
    }
  
    // This will let you add elements to the history div and it adds a delete button
    // in the same div as the history entry
    function updateHistory() {
        historyDiv.innerHTML = '';
        calculationHistory.forEach((calculation, index) => {
            const historyEntry = document.createElement('div');
            historyEntry.classList.add('history-entry');

            const p = document.createElement('p');
            p.textContent = calculation;

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Delete';
            deleteButton.classList.add('delete-button');
            deleteButton.addEventListener('click', () => deleteHistoryEntry(index));

            historyEntry.appendChild(p);
            historyEntry.appendChild(deleteButton);
            historyDiv.appendChild(historyEntry);
  });
}

// This section will let you change between light and dark mode
    const modeToggleBtn = document.getElementById('modeToggle');
    let isDarkMode = false;
  
    modeToggleBtn.addEventListener('click', toggleMode);
  
    function toggleMode() {
      isDarkMode = !isDarkMode;
  
      if (isDarkMode) {
        document.documentElement.style.setProperty('--bg-color', '#1a1a1a');
        document.documentElement.style.setProperty('--text-color', '#ffffff');
      } else {
        document.documentElement.style.setProperty('--bg-color', '#ffffff');
        document.documentElement.style.setProperty('--text-color', '#000000');
      }
    }

// This section will let you clear all of your history with the press of a button
    const clearAllHistoryButton = document.getElementById('clearAllHistoryBtn');
    clearAllHistoryButton.addEventListener('click', clearAllHistory);

    function clearAllHistory() {
        const historyDiv = document.querySelector('.history')
        historyDiv.innerHTML = '';
    }

// This function was created to delete history of specific history entries
    function deleteHistoryEntry(index) {
    calculationHistory.splice(index, 1);
    updateHistory();
  }

    

  });
  