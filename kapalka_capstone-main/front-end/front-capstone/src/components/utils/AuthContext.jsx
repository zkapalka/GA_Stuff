import React, { createContext, useContext, useState } from 'react';

// Create AuthContext
const AuthContext = createContext();

// AuthProvider component to wrap around the part of the app where you want to use this context
export const AuthProvider = ({ children }) => {
    const [userID, setUserID] = useState(null);

    return (
        <AuthContext.Provider value={{ userID, setUserID }}>
            {children}
        </AuthContext.Provider>
    );
};

// Custom hook to use the AuthContext
export const useAuth = () => {
    return useContext(AuthContext);
};
