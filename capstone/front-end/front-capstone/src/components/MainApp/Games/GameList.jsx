import { useState } from 'react';
import styled from 'styled-components';
import ReviewForm from './ReviewForm';
import axios from 'axios';

//CSS
const GameListContainer = styled.section`
    display: flex;
    flex-direction: column;
    gap: 20px;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    background-color: ${(props) => props.theme.background};
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    overflow: auto; /* Add scrollbar when content overflows */
    color: ${(props) => props.theme.text}; /* Text color based on theme */
`;

const GameItemsContainer = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
`;

const GameItemContainer = styled.div`
    padding: 20px;
    background-color: ${(props) => props.theme.background};
    border: 1px solid ${(props) => props.theme.text};
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 20vh;
    text-align: center;
    cursor: pointer; /* Add cursor pointer for clickable items */
    color: ${(props) => props.theme.text}; /* Text color based on theme */
`;

const GameImage = styled.img`
    max-width: 100%;
    height: auto;
    border-radius: 10px;
`;

const PlatformList = styled.ul`
    list-style: none;
    padding: 0;
`;

const PlatformItem = styled.li`
    margin: 5px 0;
    color: ${(props) => props.theme.text}; /* Text color based on theme */
`;

const GameURL = styled.a`
    word-wrap: break-word;
    word-break: break-all;
    white-space: normal;
    display: block;
`;

const PaginationContainer = styled.div`
    display: flex;
    justify-content: center;
    margin-top: 20px;
`;

const PaginationButton = styled.button`
    margin: 0 10px;
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;

    &:disabled {
        background-color: #d3d3d3;
        cursor: not-allowed;
    }
`;

//FUNCTIONS
const GameList = ({ games, onNextPage, onPrevPage, page, totalResults, limit }) => {
    const [selectedGame, setSelectedGame] = useState(null);

    const handleGameClick = (game) => {
        setSelectedGame(game);
    };

    const handleReviewClose = () => {
        setSelectedGame(null); // This will close the review form modal
    };

    const handleReviewSubmit = async (reviewData) => {
        try {
            const response = await axios.post('http://localhost:8080/reviews', reviewData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
    
            if (response.status === 201) {
                const review = response.data;
                // Close the review form modal or handle success as needed
                setSelectedGame(null);
            } else {
                console.error('Failed to submit review:', response.statusText);
                // Handle error scenario
            }
        } catch (error) {
            console.error('Error submitting review:', error);
            // Handle error scenario
            if (error.response && error.response.data) {
                console.error('Response data:', error.response.data);
            }
        }
    };

    return (
        <GameListContainer>
            <GameItemsContainer>
                {games.map(game => (
                    <GameItemContainer key={game.id} onClick={() => handleGameClick(game)}> {/* Add onClick handler */}
                        <h2>{game.name}</h2>
                        {game.image && <GameImage src={game.image.medium_url} alt={game.name} />}
                        <p><strong>Game URL: </strong>  
                                <GameURL href={game.site_detail_url} target="_blank" rel="noopener noreferrer">
                                    {game.site_detail_url}
                                </GameURL>
                            </p>
                        <h3>Platforms:</h3>
                        <PlatformList>
                            {game.platforms && game.platforms.map(platform => (
                                <PlatformItem key={platform.id}>{platform.name}</PlatformItem>
                            ))}
                        </PlatformList>
                    </GameItemContainer>
                ))}
                {selectedGame && (
                    <ReviewForm
                        gameName={selectedGame.name} // Pass the name of the selected game
                        onSubmit={handleReviewSubmit}
                        onClose={handleReviewClose} // Pass the onClose function
                    />
                )}
            </GameItemsContainer>
            <PaginationContainer>
                <PaginationButton onClick={onPrevPage} disabled={page === 0}>
                    Previous
                </PaginationButton>
                <PaginationButton onClick={onNextPage} disabled={(page + 1) * limit >= totalResults}>
                    Next
                </PaginationButton>
            </PaginationContainer>
        </GameListContainer>
    );
};
export default GameList;
