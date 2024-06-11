import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import GameList from './GameList';
import { Giant_Bomb_API_Key } from '../../utils/Giant_Bomb_API_Key';
import Search from './SearchComponent';

const GameContainer = styled.section`
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: ${(props) => props.theme.background};
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    overflow: auto; /* Add scrollbar when content overflows */
    color: ${(props) => props.theme.text}; /* Text color based on theme */
`;

const Game = () => {
    const [games, setGames] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(0);
    const [totalResults, setTotalResults] = useState(0);
    const [searchParams, setSearchParams] = useState({ name: '', platform: '', sortBy: '' });
    const [selectedGame, setSelectedGame] = useState(null); // Track the selected game
    const limit = 50;

    //This will pull from the API and fill out the game container with a list of the game.
    // When name or platform are used, it will then repeat the API call, but with the filters in place already
    useEffect(() => {
        const fetchGames = async () => {
            setLoading(true);
            try {
                const filterParams = [];
                const { name, platform, sortBy } = searchParams;

                //This only kicks in if name or platform has anything in it, and then it will search for it.
                if (name) filterParams.push(`name:${name}`);
                const filterString = filterParams.length ? `&filter=${filterParams.join(',')}` : '';
                const sortString = sortBy ? `&sort=name:${sortBy === 'name-asc' ? 'asc' : 'desc'}` : '';

                const response = await fetch(`https://corsproxy.io/?https://www.giantbomb.com/api/games/?api_key=${Giant_Bomb_API_Key}&format=json&field_list=id,image,name,platforms,site_detail_url&limit=${limit}&offset=${page * limit}${filterString}${sortString}`);
                const data = await response.json();

                let filteredGames = data.results;

                if (name) {
                    filteredGames = filteredGames.filter(game => game.name.toLowerCase().includes(name.toLowerCase()));
                }

                if (platform) {
                    filteredGames = filteredGames.filter(game => {
                        return game.platforms && game.platforms.some(p => 
                            p.name.toLowerCase().includes(platform.toLowerCase())
                        );
                    });
                }

                if (sortBy) {
                    filteredGames.sort((a, b) => {
                        const nameA = a.name.toUpperCase(); // ignore upper and lowercase
                        const nameB = b.name.toUpperCase(); // ignore upper and lowercase
                        if (sortBy === 'name-asc') {
                            return nameA.localeCompare(nameB);
                        } else {
                            return nameB.localeCompare(nameA);
                        }
                    });
                }

                setGames(filteredGames.slice(0, limit)); // Apply pagination here if needed
                setTotalResults(data.number_of_total_results);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching games:', error);
                setLoading(false);
            }
        };

        fetchGames();
    }, [page, searchParams]);

    const handleNextPage = () => {
        setPage(prevPage => prevPage + 1);
    };

    const handlePrevPage = () => {
        setPage(prevPage => Math.max(prevPage - 1, 0));
    };

    const handleSearch = ({ name, platform, sortBy }) => {
        setSearchParams({ name, platform, sortBy });
        setPage(0);  // Reset to first page on new search
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <GameContainer>
            <h1>Game List</h1>
            <Search onSearch={handleSearch} />
            <p>Please click on a game to add your review!</p>
            <GameList 
                games={games} 
                onNextPage={handleNextPage} 
                onPrevPage={handlePrevPage} 
                page={page} 
                totalResults={totalResults} 
                limit={limit} 
            />
        </GameContainer>
    );
};

export default Game;
