import React from 'react';
import styled from 'styled-components';

const AboutContainer = styled.div`
padding: 2rem;
background-color: ${(props) => props.theme.background};
border: 1px solid ${(props) => props.theme.text};
border-radius: 10px;
margin-top: 2rem;
`;

const About = () => {
    return (
        <AboutContainer>
            <h1>About This Project</h1>
            <p>
                The creator made this project as part of the capstone project given by General Assembly. The project features a stack consisting of React for the frontend, Spring Boot for the backend, and PostgreSQL as the database.
            </p>
            <p>
                This project utilizes REST APIs to pass information between the Spring Boot backend and the React frontend.
            </p>
        </AboutContainer>
    );
};

export default About;
