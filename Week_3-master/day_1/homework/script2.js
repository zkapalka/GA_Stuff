document.addEventListener('DOMContentLoaded', function () {
    const display = document.querySelector('.display');
    const buttons = document.querySelectorAll('.button');
    const equalsButton = document.getElementById('equals');
    const clearButton = document.getElementById('clear');
  
    let currentInput = '';
    let currentOperator = '';
    let firstOperand = '';
  
    buttons.forEach(button => {
      button.addEventListener('click', function () {
        const buttonText = button.textContent;
  
        // Handle numeric buttons
        if (!isNaN(buttonText) || buttonText === '.') {
          currentInput += buttonText;
          updateDisplay();
        }
  
        // Handle operator buttons
        else if (isOperator(buttonText)) {
          handleOperator(buttonText);
        }
      });
    });
  
    equalsButton.addEventListener('click', calculateResult);
    clearButton.addEventListener('click', resetCalculator);
  
    function isOperator(input) {
      return ['+', '-', '*', '/'].includes(input);
    }
  
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
  
    function calculateResult() {
      if (currentInput !== '' && firstOperand !== '' && currentOperator !== '') {
        const operand1 = parseFloat(firstOperand);
        const operand2 = parseFloat(currentInput);
  
        switch (currentOperator) {
          case '+':
            currentInput = operand1 + operand2;
            break;
          case '-':
            currentInput = operand1 - operand2;
            break;
          case '*':
            currentInput = operand1 * operand2;
            break;
          case '/':
            currentInput = operand1 / operand2;
            break;
        }
  
        updateDisplay();
        firstOperand = '';
        currentOperator = '';
      }
    }
  
    function resetCalculator() {
      currentInput = '';
      currentOperator = '';
      firstOperand = '';
      updateDisplay();
    }
  
    function updateDisplay() {
      display.textContent = currentInput || '0';
    }
  });
  