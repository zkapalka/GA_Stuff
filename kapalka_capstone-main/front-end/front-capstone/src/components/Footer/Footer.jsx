import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const FooterContainer = styled.footer`
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

const FooterNav = styled.nav`
    display: flex;
    align-items: center;
`;

const FooterNavItem = styled(Link)`
    margin: 0 1rem;
    text-decoration: none;
    color: ${(props) => props.theme.text};

    &:hover {
        text-decoration: underline;
    }
`;

const FooterSocials = styled.div`
    display: flex;
    align-items: center;
`;

const SocialLink = styled.a`
    margin: 0 0.5rem;
    color: ${(props) => props.theme.text};
    text-decoration: none;

    &:hover {
        text-decoration: underline;
    }
`;

const Copyright = styled.div`
    margin-top: 10px;
    color: ${(props) => props.theme.text};
`;

const Footer = () => {
    return (
        <FooterContainer>
            <FooterNav>
                <FooterNavItem to="/contact">Contact</FooterNavItem>
                <FooterNavItem to="/terms">Terms of Service</FooterNavItem>
                <FooterNavItem to="/privacy">Privacy Policy</FooterNavItem>
            </FooterNav>
            <FooterSocials>
                <SocialLink href="https://facebook.com" target="_blank" rel="noopener noreferrer">Facebook</SocialLink>
                <SocialLink href="https://twitter.com" target="_blank" rel="noopener noreferrer">Twitter</SocialLink>
                <SocialLink href="https://instagram.com" target="_blank" rel="noopener noreferrer">Instagram</SocialLink>
            </FooterSocials>
            <Copyright>
                &copy; {new Date().getFullYear()} Kapalka Time. All rights reserved.
            </Copyright>
        </FooterContainer>
    );
};

export default Footer;
