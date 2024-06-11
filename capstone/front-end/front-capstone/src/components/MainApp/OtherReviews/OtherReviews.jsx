import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../../utils/SpringConstants';
import styled from 'styled-components';

const ReviewsContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  overflow: auto;
  background-color: ${(props) => props.theme.background};
`;

const Review = styled.div`
  background-color: ${(props) => props.theme.cardBackground};
  border: 1px solid ${(props) => props.theme.cardBorder};
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 10px;
  flex: 1 0 21%;
`;

const AccessibilityOptions = styled.div`
  margin-top: 10px;

  p {
    margin: 4px 0;
    color: ${(props) => props.theme.text};
  }
`;

const SearchInput = styled.input`
  margin-bottom: 20px;
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
`;

const ToggleButton = styled.button`
  margin: 20px;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  background-color: ${(props) => props.theme.buttonBackground};
  color: black;
  cursor: pointer;

  &:hover {
    background-color: ${(props) => props.theme.buttonHoverBackground};
  }
`;

const OtherReviews = () => {
  const [reviews, setReviews] = useState([]);
  const [filteredReviews, setFilteredReviews] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);
  const [showHighRated, setShowHighRated] = useState(false);

  const fetchReviews = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/reviews`);
      setReviews(response.data);
      setFilteredReviews(response.data);
    } catch (error) {
      setError('Error fetching reviews');
      console.error('Error fetching reviews:', error);
    }
  };

  const fetchHighRatedReviews = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/reviews/high-rated`);
      setReviews(response.data);
      setFilteredReviews(response.data);
    } catch (error) {
      setError('Error fetching high-rated reviews');
      console.error('Error fetching high-rated reviews:', error);
    }
  };

  useEffect(() => {
    if (showHighRated) {
      fetchHighRatedReviews();
    } else {
      fetchReviews();
    }
  }, [showHighRated]);

  useEffect(() => {
    const filtered = reviews.filter(review =>
      review.gameName && review.gameName.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredReviews(filtered);
  }, [searchTerm, reviews]);

  const handleSearchChange = event => {
    setSearchTerm(event.target.value);
  };

  const toggleReviews = () => {
    setShowHighRated(!showHighRated);
  };

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <SearchInput
        type="text"
        value={searchTerm}
        onChange={handleSearchChange}
        placeholder="Search by game name..."
      />
      <p>You can search all reviews by game titles using the search above</p>
      <p>You can also search for highly-rated reviews by clicking the button below!</p>
      <ToggleButton onClick={toggleReviews}>
        {showHighRated ? 'Show All Reviews' : 'Ꮆㄖ丂ㄩ me: Show High-Rated Reviews'}
      </ToggleButton>
      <ReviewsContainer>
        {filteredReviews.length === 0 ? (
          <p>No reviews available.</p>
        ) : (
          filteredReviews.map((review, index) => (
            <Review key={index} className="review">
              <p>
                <strong>Game Name:</strong> {review.gameName}
              </p>
              <p>
                <strong>Rating:</strong> {review.rating}
              </p>
              <p>
                <strong>Comment:</strong> {review.comment}
              </p>
              {review.accessibilityOptions && (
                <AccessibilityOptions>
                  <p>
                    <strong>Accessibility Options:</strong>
                  </p>
                  <p>
                    <strong>Has Subtitles:</strong> {review.accessibilityOptions.hasSubtitles ? 'Yes' : 'No'}
                  </p>
                  <p>
                    <strong>Depicts Sound Effects In Captions:</strong> {review.accessibilityOptions.depictsSoundEffectsInCaptions ? 'Yes' : 'No'}
                  </p>
                  <p>
                    <strong>Has Visual Cues:</strong> {review.accessibilityOptions.hasVisualCues ? 'Yes' : 'No'}
                  </p>
                  <p>
                    <strong>Has Clean UI:</strong> {review.accessibilityOptions.hasCleanUI ? 'Yes' : 'No'}
                  </p>
                  <p>
                    <strong>Has Skippable Audio Mini Games:</strong> {review.accessibilityOptions.hasSkippableAudioMiniGames ? 'Yes' : 'No'}
                  </p>
                </AccessibilityOptions>
              )}
            </Review>
          ))
        )}
      </ReviewsContainer>
    </div>
  );
};

export default OtherReviews;
