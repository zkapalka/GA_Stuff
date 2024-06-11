import React, { useState } from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../../utils/SpringConstants';

const ProfileEditForm = ({ user, onCancel, onSaveChanges }) => {
    const [formData, setFormData] = useState({
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        registrationDate: user.registrationDate,
        password: user.password,
        birthDate: user.birthDate,
    });

    const [passwordInput, setPasswordInput] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    // FUNCTIONS
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handlePasswordChange = (e) => {
        setPasswordInput(e.target.value);
    };

    const handleNewPasswordChange = (e) => {
        setNewPassword(e.target.value);
    };

    const handleConfirmPasswordChange = (e) => {
        setConfirmPassword(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Verify password before submitting changes
        if (passwordInput.trim() !== user.password) {
            alert('Please enter the correct current password');
            return;
        }

        if (newPassword && newPassword !== confirmPassword) {
            alert('New password and confirm password do not match');
            return;
        }

        const updatedFormData = {
            ...formData,
            password: newPassword ? newPassword : formData.password,
        };

        const storedUserID = localStorage.getItem('userIDLocalStorage');
        try {
            if (storedUserID) {
                await axios.put(`${API_BASE_URL}/users/${storedUserID}`, updatedFormData);
                onSaveChanges(); // Exit edit mode upon successful update
            }
        } catch (error) {
            // Handle error
            console.error('Error updating user:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <p>First Name: <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} /></p>
            <p>Last Name: <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} /></p>
            <p>Email: <input type="text" name="email" value={formData.email} onChange={handleChange} /></p>
            <p>Current Password: <input type="password" name="password" value={passwordInput} onChange={handlePasswordChange} /></p>
            <p>New Password: <input type="password" name="newPassword" value={newPassword} onChange={handleNewPasswordChange} /></p>
            <p>Confirm New Password: <input type="password" name="confirmPassword" value={confirmPassword} onChange={handleConfirmPasswordChange} /></p>
            <p>Birth Date: <input type="date" name="birthDate" value={formData.birthDate} onChange={handleChange} /></p>
            <button type="submit">Save Changes</button>
            <button type="button" onClick={onCancel}>Cancel</button>
        </form>
    );
};

export default ProfileEditForm;
