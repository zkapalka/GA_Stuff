import React, { useState } from 'react';
import ButtonComponent from '../ButtonComponent/ButtonComponent';
import ListComponent from '../ListComponent/ListComponent';
import './ToDoContainer.css';

function ToDoContainer() {
  const [inputValue, setInputValue] = useState('');
  const [todos, setTodos] = useState([]);
  const [taskCount, setTaskCount] = useState(0);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  //Add task
  const handleAddTodo = () => {
    if (inputValue.trim() !== '') {
      setTodos([...todos, inputValue]);
      setInputValue('');
      setTaskCount(taskCount + 1); // Increment the task count
    }
  };

// Delete at individual 
  const handleDeleteTodo = (index) => {
    const newTodos = [...todos];
    newTodos.splice(index, 1);
    setTaskCount(taskCount - 1); // Increment the task count
    setTodos(newTodos);
  };


// Delete all tasks
  const handleClearTodos = () => {
    setTodos([]);
    setTaskCount(0); // Reset the task count when clearing all todos
  };

  return (
    <div className="todo-container">
      <h1>To-Do List</h1>
      <div className="input-container">
        <input
          type="text"
          placeholder="Add a new todo"
          value={inputValue}
          onChange={handleInputChange}
        />
        <ButtonComponent label="Add" onClick={handleAddTodo} />
        <ButtonComponent label="Clear All" onClick={handleClearTodos} />
      </div>
      <div className="counter">Tasks Added: {taskCount}</div>
      <ListComponent todos={todos} onDelete={handleDeleteTodo} />
    </div>
  );
}

export default ToDoContainer;
