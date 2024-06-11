import React from 'react';
import RegistrationForm from "../RegistrationForm/RegistrationForm";
import LoginForm from "../LoginForm/LoginForm";

const EntryPage = ({ onLogin }) => {
    return (
        <div>
            <h2>Welcome to Ꮆㄖ丂ㄩ 卄卂几ᗪ丂</h2>
            <p>Please either register or log in</p>
            <RegistrationForm />
            <LoginForm onLogin={onLogin} />
        </div>
    );
};

export default EntryPage;
