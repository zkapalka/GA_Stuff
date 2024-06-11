import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const ErrorContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center;
    background-color: ${(props) => props.theme.background};
    color: ${(props) => props.theme.text};
`;

const ErrorMessage = styled.h1`
    font-size: 2.5rem;
    margin-bottom: 1rem;
`;

const ErrorDescription = styled.p`
    font-size: 1.25rem;
    margin-bottom: 2rem;
`;

const HomeButton = styled(Link)`
    padding: 0.75rem 1.5rem;
    background-color: ${(props) => props.theme.buttonBackground};
    color: ${(props) => props.theme.buttonText};
    border: none;
    border-radius: 5px;
    text-decoration: none;
    cursor: pointer;

    &:hover {
        background-color: ${(props) => props.theme.buttonHoverBackground};
    }
`;

const ErrorPage = () => {
    return (
        <ErrorContainer>
            <ErrorMessage>404 - Page Not Found</ErrorMessage>
            <ErrorDescription>Sorry, the page you are looking for does not exist.</ErrorDescription>
            <HomeButton to="/">Go to Home</HomeButton>
        </ErrorContainer>
    );
};

export default ErrorPage;
