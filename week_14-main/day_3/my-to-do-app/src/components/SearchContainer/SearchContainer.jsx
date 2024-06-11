import React, { useState } from 'react';
// a

function SearchContainer({ todos }) {
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  const handleSearch = () => {
    if (searchQuery.trim() === '') {
      setSearchResults([{ id: 'no-input', name: 'Please enter a search term' }]);
      return;
    }

    const results = todos.filter(todo => todo.name.toLowerCase().includes(searchQuery.toLowerCase()));
    
    if (results.length === 0) {
      setSearchResults([{ id: 'no-results', name: 'No related tasks found' }]);
    } else {
      setSearchResults(results);
    }
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Search tasks..."
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
      />
      <button onClick={handleSearch}>Search</button>
      <ul>
        {searchResults.map(result => (
          <li key={result.id}>{result.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default SearchContainer;
