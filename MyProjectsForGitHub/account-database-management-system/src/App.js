import React from 'react';
import { Routes, Route } from 'react-router-dom';
import RegisterJobSeeker from './Components/js/register/RegisterJobSeeker';
import Menu from "./Components/js/Menu";
import RegisterCompany from "./Components/js/register/RegisterCompany";
import LoginJobSeeker from "./Components/js/login/LoginJobSeeker";
import LoginCompany from "./Components/js/login/LoginCompany";

const App = () => {
    return (
        <div id={"root"} className="App">
            <Menu />
            <div className="auth-wrapper">
                <Routes>
                    <Route path="/job/seeker/register" element={ <RegisterJobSeeker />} />
                    <Route path="/companies/register" element={ <RegisterCompany />} />
                    <Route path="/job/seeker/login" element={ <LoginJobSeeker />} />
                    <Route path="/companies/login" element={ <LoginCompany />} />
                </Routes>
            </div>
        </div>
    );
};

export default App;
