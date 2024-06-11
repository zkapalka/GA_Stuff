import React, { useState } from 'react';
import ButtonComponent from '../ButtonComponent/ButtonComponent';
import ListComponent from '../ListComponent/ListComponent';
import './HomeContainer.css';

function HomeContainer({ todos, addTodo, deleteTodo }) {
  const [inputValue, setInputValue] = useState('');
  const [taskCount, setTaskCount] = useState(todos.length);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleAddTodo = () => {
    if (inputValue.trim() !== '') {
      const newTodo = {
        id: Math.random().toString(36).substr(2, 9), // Generate a unique ID
        name: inputValue,
      };
      addTodo(newTodo);
      setInputValue('');
      setTaskCount(taskCount + 1); // Increment the task count
    }
  };

  const handleDeleteTodo = (id) => {
    deleteTodo(id);
    setTaskCount(taskCount - 1); // Decrement the task count
  };

  const handleClearTodos = () => {
    todos.forEach(todo => deleteTodo(todo.id)); // Delete each todo
    setTaskCount(0); // Reset the task count when clearing all todos
  };
  
  

  return (
    <div className="home-container">
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

export default HomeContainer;
