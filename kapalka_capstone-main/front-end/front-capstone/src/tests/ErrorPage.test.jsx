import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // Import Jest DOM matchers
import ErrorPage from '../components/ErrorPage/ErrorPage';

describe('ErrorPage component', () => {
  test('renders error message and description', () => {
    // Render the ErrorPage component
    render(<ErrorPage />);
    
    // Assert that the error message and description are rendered
    expect(screen.getByText('404 - Page Not Found')).toBeInTheDocument();
    expect(screen.getByText('Sorry, the page you are looking for does not exist.')).toBeInTheDocument();
  });

  test('renders a link to go to home', () => {
    // Render the ErrorPage component
    render(<ErrorPage />);
    
    // Assert that the "Go to Home" link is rendered with the correct URL
    const homeLink = screen.getByRole('link', { name: /go to home/i });
    expect(homeLink).toBeInTheDocument();
    expect(homeLink).toHaveAttribute('href', '/');
  });
});
