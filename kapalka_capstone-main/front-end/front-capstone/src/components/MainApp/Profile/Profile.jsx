import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { API_BASE_URL } from '../../utils/SpringConstants'; // Adjust the path as needed
import ProfileEditForm from './EditProfile';

// Styled components
const ProfileContainer = styled.section`
  padding: 20px;
  background-color: ${(props) => props.theme.background};
  color: ${(props) => props.theme.text};
  border: 1px solid ${(props) => props.theme.cardBorder};
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

const ProfileTitle = styled.h2`
  color: ${(props) => props.theme.primary};
`;

const ProfileInfo = styled.div`
  margin-top: 10px;
`;

const ProfileButton = styled.button`
  padding: 8px 16px;
  margin-top: 10px;
  background-color: ${(props) => props.theme.primary};
  color: ${(props) => props.theme.text};
  border: 1px solid ${(props) => props.theme.cardBorder};
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  
`;

const Profile = () => {
  const [user, setUser] = useState(null);
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const storedUserID = localStorage.getItem('userIDLocalStorage');
        if (storedUserID) {
          const response = await axios.get(`${API_BASE_URL}/users/${storedUserID}`);
          setUser(response.data);

          const firstName = response.data.firstName;
          const firstLetter = firstName.charAt(0).toUpperCase();

          // Store the first letter in local storage
          localStorage.setItem('userInitial', firstLetter);
        }
      } catch (error) {
        console.error('Error fetching user:', error);
      }
    };

    fetchUser();
  }, []); // No need to trigger fetchUser when userID changes

  // To allow for graceful fail
  if (!user) {
    return <div>Unable to load profile</div>;
  }

  const handleEditProfile = () => {
    setEditMode(true);
  };

  const handleCancelEdit = () => {
    setEditMode(false);
  };

  const handleSaveChanges = () => {
    setEditMode(false);
    window.location.reload(); //Force the window to refresh after saving changes
  };

  return (
    <ProfileContainer>
      <ProfileTitle>User Profile</ProfileTitle>
      <ProfileInfo>
        <p>Name: {user.firstName} {user.lastName}</p>
        <p>Email: {user.email}</p>
        <p>Password: Hidden for security reasons</p>
        <p>Registration Date: {user.registrationDate}</p>
        <p>Birth Date: {user.birthDate}</p>
      </ProfileInfo>
      {!editMode && (
        <ProfileButton onClick={handleEditProfile}>Edit Profile</ProfileButton>
      )}
      {editMode && (
        <ProfileEditForm
          user={user}
          onCancel={handleCancelEdit}
          onSaveChanges={handleSaveChanges}
        />
      )}
    </ProfileContainer>
  );
};

export default Profile;
