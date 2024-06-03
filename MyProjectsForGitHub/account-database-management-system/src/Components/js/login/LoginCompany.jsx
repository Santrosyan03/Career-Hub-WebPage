import React, { useState, useEffect } from 'react';
import '../../css/RegisterAndLogIn.css';
import axios from 'axios';

const LoginCompany = () => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const [csrfToken, setCsrfToken] = useState('');

    useEffect(() => {
        const fetchCsrfToken = async () => {
            try {
                const response = await axios.get('/csrf-token');
                setCsrfToken(response.data.token);
            } catch (error) {
                console.error('Error fetching CSRF token:', error);
            }
        };
        fetchCsrfToken();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('/login', formData, {
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': csrfToken
                }
            });

            if (response.status === 200) {
                alert("Login successful!");
                window.location.href = '/dashboard';
            } else {
                alert("Login failed. Please check your credentials.");
            }
        } catch (error) {
            console.error('Error:', error);
            alert(`Login failed: ${error.message}`);
        }
    };

    return (
        <div className="LoginContainer">
            <form className="LoginForm" onSubmit={handleSubmit}>
                <h2 className="FormTitle">
                    Company Login
                </h2>
                <div className="FormField">
                    <label className="Label">
                        Email
                    </label>
                    <input type="email"
                           name="email"
                           className="Input"
                           value={formData.email}
                           onChange={handleChange}
                           required
                    />
                </div>
                <div className="FormField">
                    <label className="Label">
                        Password
                    </label>
                    <input type="password"
                           name="password"
                           className="Input"
                           value={formData.password}
                           onChange={handleChange}
                           required
                    />
                </div>
                <button type="submit" className="Button">
                    Login
                </button>
            </form>
        </div>
    );
};

export default LoginCompany;
