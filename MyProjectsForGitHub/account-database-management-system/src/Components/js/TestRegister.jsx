import React, { useState } from 'react';

const TestRegister = () => {
    const [formData, setFormData] = useState({
        email: '',
        firstName: '',
        lastName: '',
        dateOfBirth: '',
        gender: '',
        password: ''
    });
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setError(null);
        setSuccess(false);

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/job/seeker/register", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.withCredentials = true;

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    setSuccess(true);
                    console.log('Registration successful:', xhr.responseText);
                } else if (xhr.status >= 400 && xhr.status < 500) {
                    setError(`Client error: ${xhr.responseText}`);
                } else if (xhr.status >= 500) {
                    setError(`Server error: ${xhr.responseText}`);
                } else {
                    setError('Unknown error occurred.');
                }
            }
        };

        xhr.onerror = () => {
            setError('Network error. Please check your network connection.');
        };

        xhr.send(JSON.stringify(formData));
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="email" name="email" value={formData.email} onChange={handleChange} placeholder="Email" required />
            <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} placeholder="First Name" required />
            <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} placeholder="Last Name" required />
            <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} placeholder="Date of Birth" required />
            <input type="text" name="gender" value={formData.gender} onChange={handleChange} placeholder="Gender" required />
            <input type="password" name="password" value={formData.password} onChange={handleChange} placeholder="Password" required />
            <button type="submit">Register</button>
            {error && <div style={{ color: 'red' }}>{error}</div>}
            {success && <div style={{ color: 'green' }}>Registration successful!</div>}
        </form>
    );
};

export default TestRegister;
