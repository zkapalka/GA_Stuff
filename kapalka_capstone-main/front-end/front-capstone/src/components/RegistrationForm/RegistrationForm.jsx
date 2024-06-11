import React, { useState } from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../utils/SpringConstants';
import './RegistrationForm.css';

const RegistrationForm = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        confirmPassword: '',
        firstName: '',
        lastName: '',
        email: '',
        birthDate: ''
    });

    const [errors, setErrors] = useState([]);
    const [successMessage, setSuccessMessage] = useState('');

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const validateBirthDate = () => {
        const currentDate = new Date();
        const selectedDate = new Date(formData.birthDate);
        const minAgeDate = new Date();
        minAgeDate.setFullYear(minAgeDate.getFullYear() - 18);

        return selectedDate <= minAgeDate;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors([]); // Clear any previous errors
        setSuccessMessage(''); // Clear any previous success message

        // Check if passwords match
        if (formData.password !== formData.confirmPassword) {
            setErrors(['Passwords do not match']);
            return;
        }

        // Validate birth date
        if (!validateBirthDate()) {
            setErrors(['You must be at least 18 years old to register']);
            return;
        }

        try {
            const response = await axios.post(`${API_BASE_URL}/users/signup`, formData);
            console.log(response.data); // Handle success response
            setSuccessMessage(response.data);
         
        } catch (error) {
            if (error.response && error.response.data) {
                const errorData = Array.isArray(error.response.data) ? error.response.data : [error.response.data];
                setErrors(errorData);
            } else {
                setErrors(['An unexpected error occurred']);
            }
        }
    };

    return (
        <div className="registration-container">
            <h2>Registration Form</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
                <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
                <input type="password" name="confirmPassword" placeholder="Confirm Password" value={formData.confirmPassword} onChange={handleChange} required />
                <input type="text" name="firstName" placeholder="First Name" value={formData.firstName} onChange={handleChange} required />
                <input type="text" name="lastName" placeholder="Last Name" value={formData.lastName} onChange={handleChange} required />
                <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
                <input type="date" name="birthDate" placeholder="Birth Date" value={formData.birthDate} onChange={handleChange} required />
                <button type="submit">Register</button>
            </form>
            {errors.length > 0 && (
                <div className="error-messages">
                    {errors.map((error, index) => (
                        <p key={index}>{error}</p>
                    ))}
                </div>
            )}
            {successMessage && (
                <div className="success-message">
                    <p>{successMessage}</p>
                </div>
            )}
        </div>
    );
};

export default RegistrationForm;
