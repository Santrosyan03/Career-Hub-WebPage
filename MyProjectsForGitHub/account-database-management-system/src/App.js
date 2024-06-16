import React from 'react';
import Menu from "./Components/js/Menu";
import AppContent from "./Components/js/AppContent";
import TestRegister from "./Components/js/TestRegister";

const App = () => {
    return (
        <div id={"root"} className="App">
            <Menu />
            <AppContent />
            <TestRegister />
        </div>
    );
};

export default App;
