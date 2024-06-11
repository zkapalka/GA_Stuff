import React, { useState } from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../utils/SpringConstants';
import './LoginForm.css';
import { useAuth } from '../utils/AuthContext';
import styled from 'styled-components';

const ErrorMessage = styled.div`
    color: red;
    background-color: #fdd;
    border: 1px solid red;
    padding: 10px;
    margin-top: 10px;
    border-radius: 5px;
`;


const LoginForm = ({ onLogin }) => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    const { setUserID } = useAuth();
    const [error, setError] = useState(null); // Use a single error state for simplicity

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        setError(null); // Clear any previous errors
        try {
            const response = await axios.post(`${API_BASE_URL}/users/login`, formData);
            console.log(response.data);
            const { userID } = response.data;
            setUserID(userID);
            localStorage.setItem('userIDLocalStorage', userID);

            
            console.log(userID);
            onLogin();
        } catch (error) {
            if (error.response && error.response.data) {
                const errorData = error.response.data.message || 'An unexpected error occurred.';
                setError(errorData);
               
            } else {
                setError('An unexpected error occurred.');
            }
        }
    };

    return (
        <div className="log-in-container">
            <h2>Login Form</h2>
            <form onSubmit={handleLoginSubmit}>
                <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
                <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
                <button type="submit">Login</button>
            </form>
            {error && <ErrorMessage>{error}</ErrorMessage>}
        </div>
    );
};

export default LoginForm;
