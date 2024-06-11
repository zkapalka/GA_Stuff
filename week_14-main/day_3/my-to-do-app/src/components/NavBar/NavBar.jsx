import React from 'react';
import { Link } from 'react-router-dom';
import './NavBar.css'

function Nav() {
  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/history">History</Link></li>
        <li><Link to="/search">Search</Link></li>
      </ul>
    </nav>
  );
}

export default Nav;
