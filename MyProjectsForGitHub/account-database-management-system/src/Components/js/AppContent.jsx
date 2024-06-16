import * as React from 'react';

import Buttons from './Buttons';
import AuthContent from './AuthContent';
import LoginForm from './LoginForm';
import WelcomeContent from './WelcomeContent';
import {setAuthHeader} from "./axios_helper";
import axios from "axios";

export default class AppContent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome"
        };
    }

    login = () => {
        this.setState({ componentToShow: "login" });
    };

    logout = () => {
        this.setState({ componentToShow: "welcome" });
        setAuthHeader(null);
    };

    onLogin = (e, email, password) => {
        e.preventDefault();
        axios.post("/job/seeker/login", {
            email: email,
            password: password
        }).then(
            (response) => {
                setAuthHeader(response.data.token);
                this.setState({ componentToShow: "messages" });
            }).catch(
            (error) => {
                setAuthHeader(null);
                this.setState({ componentToShow: "welcome" });
                console.error('Login error:', error);
            }
        );
    };

    onRegister = async (e, email, firstName, lastName, dateOfBirth, gender, password) => {
        e.preventDefault();
        try {
            const response = await axios.post('/job/seeker/register', {
                email: email,
                firstName: firstName,
                lastName: lastName,
                dateOfBirth: dateOfBirth,
                gender: gender,
                password: password
            });

            console.log('Registration successful:', response.data);
            this.setState({ componentToShow: "welcome" });
        } catch (error) {
            console.error('Registration error:', error);
            alert(`Sign up failed: ${error.message}`);
        }
    };

    render() {
        return (
            <>
                <Buttons
                    login={this.login}
                    logout={this.logout}
                />

                {this.state.componentToShow === "welcome" && <WelcomeContent />}
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />}
                {this.state.componentToShow === "messages" && <AuthContent />}
            </>
        );
    }
}
