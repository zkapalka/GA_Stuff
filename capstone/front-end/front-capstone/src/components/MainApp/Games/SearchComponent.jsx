import React, { useState } from 'react';
import styled from 'styled-components';

const SearchContainer = styled.div`
    margin-bottom: 20px;
`;

const SearchInput = styled.input`
    padding: 10px;
    width: 200px;
    margin-right: 10px;
`;

const SearchButton = styled.button`
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
`;

const Search = ({ onSearch }) => {
    const [searchName, setSearchName] = useState('');
    const [searchPlatform, setSearchPlatform] = useState('');
    const [sortBy, setSortBy] = useState('');

    const handleSearch = () => {
        onSearch({ name: searchName, platform: searchPlatform, sortBy });
    };

    return (
        <SearchContainer>
            <SearchInput
                type="text"
                placeholder="Search by name"
                value={searchName}
                onChange={(e) => setSearchName(e.target.value)}
            />
            <SearchInput
                type="text"
                placeholder="Search by platform"
                value={searchPlatform}
                onChange={(e) => setSearchPlatform(e.target.value)}
            />
            <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
                <option value="">Sort By</option>
                <option value="name-asc">Name (A to Z)</option>
                <option value="name-desc">Name (Z to A)</option>
            </select>
            <SearchButton onClick={handleSearch}>Search</SearchButton>
        </SearchContainer>
    );
};

export default Search;
