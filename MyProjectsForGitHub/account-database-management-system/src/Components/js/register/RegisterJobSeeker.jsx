import React, { useState } from 'react';
import '../../css/RegisterAndLogIn.css';

const RegisterJobSeeker = () => {
    const [formData, setFormData] = useState({
        email: '',
        firstName: '',
        lastName: '',
        dateOfBirth: '',
        gender: '',
        password: '',
        reWritePassword: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const clearFormData = () => {
        setFormData({
            email: '',
            firstName: '',
            lastName: '',
            dateOfBirth: '',
            gender: '',
            password: '',
            reWritePassword: ''
        });
    };


    const passwordsMatch = () => {
        return formData.password === formData.reWritePassword;
    };

    const isPasswordInCorrectType = () => {
        return /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/.test(formData.password);
    };

    const isAllFieldsFilledExceptPassword = () => {
        return formData.email !== "" &&
               formData.firstName !== "" &&
               formData.lastName !== "" &&
               formData.dateOfBirth !== "" &&
               formData.gender !== "";
    }

    const sendPostRequest = async () => {
        try {
            const response = await fetch('/job/seeker/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message);
            }

            return response.json();
        } catch (error) {
            throw new Error(`Network error: ${error.message}`);
        }
    };


    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!passwordsMatch()) {
            alert("Passwords do not match");
            return;
        }

        if (!isPasswordInCorrectType()) {
            alert("Password should be at least 8 character long and include at least one number, one letter (uppercase and lowercase) and one symbol");
            return;
        }

        if (!isAllFieldsFilledExceptPassword()) {
            alert("Make sure that all fields are filled!");
            return;
        }

        try {
            const result = await sendPostRequest();
            console.log(result);
            alert("Sign up successful!");
            clearFormData();
        } catch (error) {
            console.error('Error:', error);
            alert(`Sign up failed: ${error.message}`);
        }
    };

    const redirectToLogin = () => {
        window.location.href = '/job/seeker/login';
    };

    return (
        <div className="SignUpContainer">
            <form className="SignUpForm">
                <h2 className="FormTitle">
                    Create Account For Job Seeker
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
                        First Name
                    </label>
                    <input type="text"
                           name="firstName"
                           className="Input"
                           value={formData.firstName}
                           onChange={handleChange}
                           required
                    />
                </div>
                <div className="FormField">
                    <label className="Label">
                        Last Name
                    </label>
                    <input type="text"
                           name="lastName"
                           className="Input"
                           value={formData.lastName}
                           onChange={handleChange}
                           required
                    />
                </div>
                <div className="FormField">
                    <label className="Label">
                        Date of Birth
                    </label>
                    <input type="date"
                           name="dateOfBirth"
                           className="Input"
                           value={formData.dateOfBirth}
                           onChange={handleChange}
                           required
                    />
                </div>
                <div className="FormField">
                    <label className="Label">
                        Gender
                    </label>
                    <select name="gender"
                            className="Select"
                            value={formData.gender}
                            onChange={handleChange}
                            required
                    >
                        <option value="">
                            Select Gender
                        </option>
                        <option value="male">
                            Male
                        </option>
                        <option value="female">
                            Female
                        </option>
                        <option value="other">
                            Other
                        </option>
                    </select>
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
                <div className="FormField">
                    <label className="Label">
                        Re-write Password
                    </label>
                    <input type="password"
                           name="reWritePassword"
                           className="Input"
                           value={formData.reWritePassword}
                           onChange={handleChange}
                           required
                    />
                </div>
                <button type="submit"
                        className="Button"
                        onClick={handleSubmit}
                >
                    Register
                </button>
                <div className="already-have-account">
                    <p className="login-text">
                        Already have an account:
                    </p>
                    <button className="login-button"
                            onClick={redirectToLogin}
                    >
                        Log In
                    </button>
                </div>
            </form>
        </div>
    );
};

export default RegisterJobSeeker;
