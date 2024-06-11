import React from 'react';
import { useParams } from 'react-router-dom';

function TaskDetails({ todos }) {
  const { id } = useParams();
  const task = todos.find(todo => todo.id === id);

  if (!task) {
    return <div>Task not found.</div>;
  }

  return (
    <div>
      <h2>Task Details</h2>
      <p>Name: {task.name}</p>
      <p>Priority: {task.priority}</p>
      <p>Due Date: {task.dueDate}</p>
      <p>Notes: {task.notes}</p>
    </div>
  );
}

export default TaskDetails;
