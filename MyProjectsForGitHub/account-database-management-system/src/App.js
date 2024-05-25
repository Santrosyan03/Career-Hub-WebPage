import React from 'react';
import { Routes, Route } from 'react-router-dom';
import RegisterJobSeeker from './Components/js/RegisterJobSeeker';
import Menu from "./Components/js/Menu";
import RegisterCompany from "./Components/js/RegisterCompany";

const App = () => {
    return (
        <div id={"root"} className="App">
            <Menu />
            <div className="auth-wrapper">
                <Routes>
                    <Route path="/job/seeker/register" element={<RegisterJobSeeker />} />
                    <Route path="/companies/register" element={<RegisterCompany />} />
                </Routes>
            </div>
        </div>
    );
};

export default App;
