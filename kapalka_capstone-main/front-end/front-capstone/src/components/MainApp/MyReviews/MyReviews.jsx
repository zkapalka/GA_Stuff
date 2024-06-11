import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { API_BASE_URL } from '../../utils/SpringConstants';

const ReviewContainer = styled.section`
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    overflow: auto;
    background-color: ${(props) => props.theme.background};
    color: ${(props) => props.theme.text};
`;

const Review = styled.div`
    background-color: ${(props) => props.theme.cardBackground};
    border: 1px solid ${(props) => props.theme.cardBorder};
    border-radius: 8px;
    padding: 16px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin: 10px;
    flex: 1 0 21%;
    color: ${(props) => props.theme.text};

    .delete-button {
        margin-top: 10px;
        cursor: pointer;
        background-color: ${(props) => props.theme.primary};
        color: ${(props) => props.theme.buttonText};
        border: none;
        border-radius: 5px;
        padding: 8px 16px;
    }
`;

const AccessibilityOptions = styled.div`
    margin-top: 10px;
    p {
        margin: 4px 0;
        color: ${(props) => props.theme.text};
    }
`;

const DeleteButton = styled.div`
    background-color: #ff6961; /* Red color */
    color: white; /* White text color */
    padding: 8px 16px; /* Padding around the text */
    border-radius: 4px; /* Rounded corners */
    cursor: pointer; /* Show pointer cursor on hover */
    transition: background-color 0.3s ease; /* Smooth transition on hover */

    &:hover {
        background-color: #cc0000; /* Darker red color on hover */
    }
`;
    

const MyReview = () => {
    const [reviews, setReviews] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchReviews = async () => {
            try {
                const storedUserID = localStorage.getItem('userIDLocalStorage');
                if (storedUserID) {
                    const response = await axios.get(`${API_BASE_URL}/reviews/users/${storedUserID}`);
                    setReviews(response.data);
                }
            } catch (error) {
                setError('Error fetching reviews');
                console.error('Error fetching reviews:', error);
            }
        };

        fetchReviews();
    }, []);

    const handleDeleteReview = async (reviewID) => {
        try {
            await axios.delete(`${API_BASE_URL}/reviews/${reviewID}`);
            // Remove the deleted review from the state
            setReviews(reviews.filter(review => review.reviewID !== reviewID));
        } catch (error) {
            console.error('Error deleting review:', error);
        }
    };

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <ReviewContainer>
            {reviews.length === 0 ? (
                <p>No reviews available.</p>
            ) : (
                
                reviews.map((review, index) => (
                    <Review key={index} className="review">
                        <p><strong>Game Name:</strong> {review.gameName}</p>
                        <p><strong>Rating:</strong> {review.rating}</p>
                        <p><strong>Comment:</strong> {review.comment}</p>
                        {review.accessibilityOptions && (
                            <AccessibilityOptions>
                                <p><strong>Accessibility Options:</strong></p>
                                <p><strong>Has Subtitles:</strong> {review.accessibilityOptions.hasSubtitles ? 'Yes' : 'No'}</p>
                                <p><strong>Depicts Sound Effects In Captions:</strong> {review.accessibilityOptions.depictsSoundEffectsInCaptions ? 'Yes' : 'No'}</p>
                                <p><strong>Has Visual Cues:</strong> {review.accessibilityOptions.hasVisualCues ? 'Yes' : 'No'}</p>
                                <p><strong>Has Clean UI:</strong> {review.accessibilityOptions.hasCleanUI ? 'Yes' : 'No'}</p>
                                <p><strong>Has Skippable Audio Mini Games:</strong> {review.accessibilityOptions.hasSkippableAudioMiniGames ? 'Yes' : 'No'}</p>
                            </AccessibilityOptions>
                        )}<DeleteButton onClick={() => handleDeleteReview(review.reviewID)}>Delete</DeleteButton>
                    </Review>
                ))
            )}
        </ReviewContainer>
    );
};

export default MyReview;
