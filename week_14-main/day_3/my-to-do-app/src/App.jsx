import HomeContainer from './components/HomeContainer/HomeContainer';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState } from 'react';
import Nav from './components/NavBar/NavBar';
import HistoryContainer from './components/HistoryContainer/HistoryContainer';
import SearchContainer from './components/SearchContainer/SearchContainer';
import TaskDetails from './components/TaskDetails/TaskDetails';
import ErrorPage from './components/ErrorPage/ErrorPage';


function App() {
  const [todos, setTodos] = useState([]);

  const addTodo = (todo) => {
    setTodos([...todos, todo]);
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter(todo => todo.id !== id));
  };

  return (
    <Router>
      <div>
        <Nav />
        <Routes>
          <Route path="/" element={<HomeContainer todos={todos} addTodo={addTodo} deleteTodo={deleteTodo} />} />
          <Route path="/history" element={<HistoryContainer todos={todos} />} />
          <Route path="/search" element={<SearchContainer todos={todos} />} />
          <Route path="/todos/:id" element={<TaskDetails todos={todos} />} />
          <Route path="*" element={<ErrorPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
