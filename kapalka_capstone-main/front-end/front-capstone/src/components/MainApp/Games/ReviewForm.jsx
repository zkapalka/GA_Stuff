import React, { useState, useEffect } from 'react';
import styled from 'styled-components';

const ReviewFormContainer = styled.section`
  position: fixed;
  top: 50%;
  left: 50%;
  width: 50%;
  height: 75%;
  transform: translate(-50%, -50%);
  background-color: ${(props) => props.theme.background};
  color: ${(props) => props.theme.text};
  padding: 20px;

  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);

  @media (max-width: 768px) {
    width: 80%;
    height: 80%;
  }

  @media (max-width: 480px) {
    width: 90%;
    height: 90%;
  }
`;


const ReviewForm = ({ gameName, onClose, onSubmit }) => {
  const [userID, setUserID] = useState(null);

  useEffect(() => {
    const userIDLocalStorage = localStorage.getItem('userIDLocalStorage');
    if (userIDLocalStorage) {
      setUserID(userIDLocalStorage);
    }
  }, []);

  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState('');
  const [accessibilityOptions, setAccessibilityOptions] = useState({
    hasSubtitles: false,
    depictsSoundEffectsInCaptions: false,
    hasVisualCues: false,
    hasCleanUI: false,
    hasSkippableAudioMiniGames: false,
  });

  const handleCheckboxChange = (option) => {
    setAccessibilityOptions((prevState) => {
      const newState = {
        ...prevState,
        [option]: !prevState[option],
      };
      return newState;
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    //The JSON object that passes over to database
    const reviewData = {
      rating,
      comment,
      gameName,
      userId: userID,
      accessibilityOptions: { ...accessibilityOptions }, // Create a shallow copy
    };
    onSubmit(reviewData);
    onClose();
  };

  return (
    <ReviewFormContainer>
      <h2>Review for: {gameName}</h2>
      <form onSubmit={handleSubmit}>
        <label>
          <strong>Rating: </strong> Share how accessible you feel this game is. 0 not being very accessible, 5 being extremely accessible
          <br />
          <input
            type="number"
            value={rating}
            onChange={(e) => setRating(parseInt(e.target.value))}
            min={0}
            max={5}
          />
        </label>
        <br />
        <br />

        <label>
          <strong>Comment: </strong> Share your thoughts on this game
          <br />
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
          />
        </label>
        <br />
        <br />

        <label>
          <strong>Accessibility Options: </strong> Share your thoughts on if the game has those features or not. Check the box
          if the game has it, leave it unchecked otherwise
          <br />
          <br />

          <input
            type="checkbox"
            checked={accessibilityOptions.hasSubtitles}
            onChange={() => handleCheckboxChange('hasSubtitles')}
          />
          Subtitles
          <br />

          <input
            type="checkbox"
            checked={accessibilityOptions.depictsSoundEffectsInCaptions}
            onChange={() => handleCheckboxChange('depictsSoundEffectsInCaptions')}
          />
          Sound Effects in Captions
        </label>

        <br />

        <input
          type="checkbox"
          checked={accessibilityOptions.hasVisualCues}
          onChange={() => handleCheckboxChange('hasVisualCues')}
        />
        <label>Has Visual Cues</label>

        <br />

        <input
          type="checkbox"
          checked={accessibilityOptions.hasCleanUI}
          onChange={() => handleCheckboxChange('hasCleanUI')}
        />
        <label>Has Clean UI</label>
        <br />

        <input
          type="checkbox"
          checked={accessibilityOptions.hasSkippableAudioMiniGames}
          onChange={() => handleCheckboxChange('hasSkippableAudioMiniGames')}
        />
        <label>Has Skippable Audio Mini Games</label>

        <br />
        <br />
        <br />
        
        <button type="submit">Submit Review</button>
        <button type="button" onClick={onClose}>
          Cancel
        </button>
      </form>
    </ReviewFormContainer>
  );
};

export default ReviewForm;
