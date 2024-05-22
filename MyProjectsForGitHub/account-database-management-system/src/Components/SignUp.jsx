import React, { useState } from 'react';
import styled from 'styled-components';

const SignUpContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f2f5;
`;

const SignUpForm = styled.form`
    background: #ffffff;
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    width: 100%;
`;

const FormTitle = styled.h2`
    margin-bottom: 20px;
    font-size: 24px;
    color: #333;
`;

const FormField = styled.div`
    margin-bottom: 20px;
`;

const Label = styled.label`
    display: block;
    margin-bottom: 5px;
    color: #333;
`;

const Input = styled.input`
    width: calc(100% - 20px);
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
`;

const Select = styled.select`
    width: calc(100% - 20px);
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
`;

const Button = styled.button`
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;

    &:hover {
        background-color: #0056b3;
    }
`;

const SignUpFORM = () => {
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

    const passwordsMatch = () => {
        return formData.password === formData.reWritePassword;
    };

    const sendPostRequest = async () => {
        try {
            const response = await fetch('/api/accounts/signup', {
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

        try {
            const result = await sendPostRequest();
            console.log(result);
            alert("Sign up successful!");
        } catch (error) {
            console.error('Error:', error);
            alert(`Sign up failed: ${error.message}`);
        }
    };

    return (
        <SignUpContainer>
            <SignUpForm>
                <FormTitle>Create Account</FormTitle>
                <FormField>
                    <Label>Email</Label>
                    <Input type="email" name="email" value={formData.email} onChange={handleChange} required />
                </FormField>
                <FormField>
                    <Label>First Name</Label>
                    <Input type="text" name="firstName" value={formData.firstName} onChange={handleChange} required />
                </FormField>
                <FormField>
                    <Label>Last Name</Label>
                    <Input type="text" name="lastName" value={formData.lastName} onChange={handleChange} required />
                </FormField>
                <FormField>
                    <Label>Date of Birth</Label>
                    <Input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} required />
                </FormField>
                <FormField>
                    <Label>Gender</Label>
                    <Select name="gender" value={formData.gender} onChange={handleChange} required>
                        <option value="">Select Gender</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </Select>
                </FormField>
                <FormField>
                    <Label>Password</Label>
                    <Input type="password" name="password" value={formData.password} onChange={handleChange} required />
                </FormField>
                <FormField>
                    <Label>Re-write Password</Label>
                    <Input type="password" name="reWritePassword" value={formData.reWritePassword} onChange={handleChange} required />
                </FormField>
                <Button type="submit" onClick={handleSubmit}>
                    Sign Up
                </Button>
            </SignUpForm>
        </SignUpContainer>
    );
};

export default SignUpFORM;
