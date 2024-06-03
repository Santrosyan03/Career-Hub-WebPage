import React from 'react';
import { Link } from 'react-router-dom';
import '../../Components/css/Menu.css';
import logo from '../../assets/logo.jpg';

const Menu = () => {
    return (
        <nav className="menu">
            <Link to="/">
                <div className="menu-logo">
                    <img
                        src={logo}
                        alt="CareerHub Logo"
                        className="logo-image"
                    />
                </div>
            </Link>
            <ul>
                <li>
                    <Link to="/jobs">
                        Jobs
                    </Link>
                </li>
                <li>
                    <Link to="/trainings">
                        Trainings
                    </Link>
                </li>
                <li>
                    <Link to="/companies">
                        Companies
                    </Link>
                </li>
                <li>
                    <Link to="/about-us">
                        About Us
                    </Link>
                </li>
                <li>
                    <Link to="/contact">
                        Contact
                    </Link>
                </li>
                <div className="dropdown">
                    <li className="dropbtn">
                        For Companies
                    </li>
                    <div className="dropdown-content">
                        <Link to="/companies/login">
                            Sign In
                        </Link>
                        <Link to="/companies/register">
                            Register
                        </Link>
                        {/*<Link to="/companies/job-packages">*/}
                        {/*    Job Packages*/}
                        {/*</Link>*/}
                        {/*<Link to="/companies/training-packages">*/}
                        {/*    Training Packages*/}
                        {/*</Link>*/}
                    </div>
                </div>
                <div className="dropdown">
                    <li className="dropbtn">
                        For Job Seekers
                    </li>
                    <div className="dropdown-content">
                        <Link to="/job/seeker/login">
                            Sign In
                        </Link>
                        <Link to="/job/seeker/register">
                            Register
                        </Link>
                    </div>
                </div>
            </ul>
        </nav>
    );
};

export default Menu;
