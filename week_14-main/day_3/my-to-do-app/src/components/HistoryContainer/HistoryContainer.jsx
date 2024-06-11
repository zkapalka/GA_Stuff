import React from 'react';

function HistoryContainer({ todos }) {
  return (
    <div>
      <h2>Task History</h2>
      <ul>
        {todos.map((todo, index) => (
          <li key={index}>
            <strong>Task:</strong> {todo.name}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default HistoryContainer;
