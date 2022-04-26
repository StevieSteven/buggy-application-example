import React from "react";
import {
    Route,
    BrowserRouter as Router,
    Routes, useNavigate
} from "react-router-dom";
import {ListOverviewPage} from "./pages/ListOverviewPage";
import {ListDetailPage} from "./pages/ListDetailPage";
import {AppBar, Typography} from "@mui/material";


function App() {

    return (
        <div className="App">
            <Router>
                <AppContent/>
            </Router>
        </div>
    );
}

const AppContent = () => {

    const navigate = useNavigate();
    return (
        <div>
            <AppBar onClick={() => navigate("/")} position={"sticky"}>
                <Typography variant={"h1"}>Buggy App</Typography>
            </AppBar>
            <Routes>
                <Route path={"/"} element={<ListOverviewPage/>}/>
                <Route path={"/details/:id"} element={<ListDetailPage/>}/>
            </Routes>
        </div>
    )
}

export default App;
