import React from 'react';
import styled from 'styled-components';

const HomeContainer = styled.section`
padding: 2rem;
margin: 2rem;
background-color: ${(props) => props.theme.background};
border: 1px solid ${(props) => props.theme.text};
border-radius: 10px;
width: 100vh;
`;

const Home = () => {
    return (
        <HomeContainer>
            <h1>Welcome to Ꮆㄖ丂ㄩ 卄卂几ᗪ丂</h1>
            <p>This is a site created by a Deaf gamer for Deaf gamers.</p>
            <p>
                The purpose of this game is to allow users to select games, and then write reviews and share their opinions on how accessible a particular game is for a Deaf gamer. Due to the nature of this being a project, this only focuses on Deaf gamers, and not Deaf/Blind gamers.
            </p>
            <p>
                Users will be able to write their reviews and see reviews made by other people.
            </p>
        </HomeContainer>
    );
};

export default Home;
