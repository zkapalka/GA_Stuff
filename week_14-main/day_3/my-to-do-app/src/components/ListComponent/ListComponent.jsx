import React from 'react';
import './ListComponent.css';

function ListComponent({ todos, onDelete }) {
  return (
    
    <ul className="list-container">
      {todos.map(todo => (
        <li key={todo.id}>
          {todo.name}
          {onDelete && <button onClick={() => onDelete(todo.id)}>Delete</button>}
        </li>
      ))}
    </ul>

  );
}

export default ListComponent;
