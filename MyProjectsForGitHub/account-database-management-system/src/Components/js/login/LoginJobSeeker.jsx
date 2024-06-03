import React, {useState} from 'react';
import '../../css/RegisterAndLogIn.css';

const LoginJobSeeker = () => {

    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
           ...formData,
            [name] : value
        });
    }

    const isEmailFieldFilled = () => {
        return formData.email !== "";
    }

    const clearFormData = () => {
        setFormData({
            email: '',
            password: ''
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!isEmailFieldFilled()) {
            alert("Make sure that email field is filled!");
            return;
        }

        try {
            const result = await sendPostRequest();
            console.log(result);
            alert("Login successful!");
            clearFormData();
        } catch (error) {
            console.error('Error:', error);
            alert(`Login failed: ${error.message}`);
        }
    };

    const sendPostRequest = async () => {
        try {
            const response = await fetch('/job/seeker/login', {
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

    return (
        <div className="LoginContainer">
            <form className="SignUpForm">
                <h2 className="FormTitle">
                    Login to Job Seeker Account
                </h2>
                <div className="FormField">
                    <label className="Label">
                        Email
                    </label>
                    <input type="email"
                           name="email"
                           className="Input"
                           onChange={handleChange}
                           required
                    />
                </div>
                <div className="FormField">
                    <label className="Label">Password</label>
                    <input type="password"
                           name="password"
                           className="Input"
                           onChange={handleChange}
                           required
                    />
                </div>
                <button type="submit"
                        className="Button"
                        onClick={handleSubmit}
                >
                    Log In
                </button>
            </form>
        </div>
    );
};

export default LoginJobSeeker;
