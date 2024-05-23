import React from 'react';
import { Routes, Route } from 'react-router-dom';
import SignUp from './Components/js/SignUp';
import Menu from "./Components/js/Menu";

const App = () => {
    return (
        <div id={"root"} className="App">
            <Menu />
            <div className="auth-wrapper">
                <Routes>
                    <Route path="/job/seeker/register" element={<SignUp />} />
                </Routes>
            </div>
        </div>
    );
};

export default App;
