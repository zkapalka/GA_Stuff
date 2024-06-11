function ListComponent({ todos, onDelete }) {
  return (
    <div className="list-container">
      {todos.map((todo, index) => (
        <div key={index} className="todo-item">
          <p>{todo}</p>
          <button onClick={() => onDelete(index)}>Delete</button>
        </div>
      ))}
    </div>
  );
}

export default ListComponent;
