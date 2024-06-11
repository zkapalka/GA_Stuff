import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const HeaderContainer = styled.header`
    width: 95%;
    margin: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    background-color: ${(props) => props.theme.background};
    border: 1px solid ${(props) => props.theme.text};
    border-radius: 10px;
`;

const Nav = styled.nav`
    display: flex;
    align-items: center;
`;

const NavItem = styled(Link)`
    margin: 0 1rem;
    text-decoration: none;
    color: ${(props) => props.theme.text};

    &:hover {
        text-decoration: underline;
    }
`;

const UserProfile = styled.div`
    display: flex;
    align-items: center;
`;

const UserInitial = styled(Link)`
    width: 40px;
    height: 40px;
    background-color: #ccc;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 1rem;
    color: ${(props) => props.theme.text};
`;

const ToggleButton = styled.button`
    margin-left: 1rem;
    padding: 0.5rem 1rem;
    background-color: ${(props) => props.theme.text};
    color: ${(props) => props.theme.background};
    border: none;
    border-radius: 5px;
    cursor: pointer;

    &:hover {
        background-color: ${(props) => props.theme.text};
        color: ${(props) => props.theme.background};
        opacity: 0.8;
    }
`;

const Header = ({ onLogout, isDarkMode, setIsDarkMode }) => {
    // Retrieve the first letter from local storage
    const userInitial = localStorage.getItem('userInitial');

    const toggleDarkMode = () => {
        setIsDarkMode((prevMode) => !prevMode);
    };

    return (
        <HeaderContainer>
            <Nav>
                <NavItem to="/home">Ꮆㄖ丂ㄩ 卄卂几ᗪ丂</NavItem>
                <NavItem to="/home">Home</NavItem>
                <NavItem to="/about">About</NavItem>
                <NavItem to="/games">Games</NavItem>
                <NavItem to="/my-reviews">My Reviews</NavItem>
                <NavItem to="/other-reviews">Other Reviews</NavItem>
            </Nav>
            <UserProfile>
                <UserInitial to="/profile">{userInitial}</UserInitial>
                <button onClick={onLogout}>Logout</button>
                <ToggleButton onClick={toggleDarkMode}>
                    {isDarkMode ? 'Light Mode' : 'Dark Mode'}
                </ToggleButton>
            </UserProfile>
        </HeaderContainer>
    );
};

export default Header;
