import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import styled, { ThemeProvider, createGlobalStyle } from 'styled-components';
import Home from './components/MainApp/Home/Home';
import EntryPage from './components/EntryPage/EntryPage';
import Header from './components/Header/Header';
import About from './components/MainApp/About/About';
import Game from './components/MainApp/Games/Games';
import MyReview from './components/MainApp/MyReviews/MyReviews';
import OtherReview from './components/MainApp/OtherReviews/OtherReviews';
import Profile from './components/MainApp/Profile/Profile';
import Footer from './components/Footer/Footer';
import ErrorPage from './components/ErrorPage/ErrorPage';

import { AuthProvider } from './components/utils/AuthContext';

// Define light and dark themes
const lightTheme = {
    background: 'white',
    text: 'black',
    primary: '#007bff',
    buttonText: 'white',
    cardBackground: '#f9f9f9',
    cardBorder: '#ddd',
    disabled: '#d3d3d3',
  };
  
  const darkTheme = {
    background: 'black',
    text: 'white',
    primary: '#1e90ff',
    buttonText: 'black',
    cardBackground: '#333',
    cardBorder: '#555',
    disabled: '#555',
  };

// Global styles
const GlobalStyle = createGlobalStyle`
body, html, #root {
    margin: 0;
    padding: 0;
    height: 100%;
    font-family: Arial, Helvetica, sans-serif;
  }

  body {
    background-color: ${(props) => props.theme.background};
    color: ${(props) => props.theme.text};
    transition: all 0.2s linear;
  }
`
const MainContent = styled.main`
  flex: 1;
  padding: 20px;
  background-color: ${(props) => props.theme.background};
`;;



const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(
        localStorage.getItem('isAuthenticated') === 'true'
    );

    const [isDarkMode, setIsDarkMode] = useState(
        localStorage.getItem('isDarkMode') === 'true'
    );

    useEffect(() => {
        localStorage.setItem('isDarkMode', isDarkMode);
    }, [isDarkMode]);

    const handleLoginSubmit = () => {
        setIsAuthenticated(true);
        localStorage.setItem('isAuthenticated', 'true');
    };

    const handleLogout = () => {
        setIsAuthenticated(false);
        localStorage.setItem('isAuthenticated', 'false');
        console.log('User logged out, isAuthenticated set to false');
    };

    return (
        <AuthProvider>
        <ThemeProvider theme={isDarkMode ? darkTheme : lightTheme}>
            <GlobalStyle />
            <Router>
                {isAuthenticated ? (
                    <>
                        <Header onLogout={handleLogout} isDarkMode={isDarkMode} setIsDarkMode={setIsDarkMode} />
                       <MainContent>
                        <Routes>
                            <Route path="/home" element={<Home />} />
                            <Route path="/about" element={<About />} />
                            <Route path="/games" element={<Game />} />
                            <Route path="/my-reviews" element={<MyReview />} />
                            <Route path="/other-reviews" element={<OtherReview />} />
                            <Route path="/profile" element={<Profile />} />
                            <Route path="/entrypage" element={<Navigate to="/home" />} />
                            <Route path="*" element={<ErrorPage/>} />
                        </Routes>
                        </MainContent>
                        <Footer />
                    </>
                ) : (
                    <Routes>
                        <Route path="/entrypage" element={<EntryPage onLogin={handleLoginSubmit} />} />
                        <Route path="*" element={<Navigate to="/entrypage" />} />
                    </Routes>
                )}
            </Router>
        </ThemeProvider>
        </AuthProvider>
    );
};

export default App;
