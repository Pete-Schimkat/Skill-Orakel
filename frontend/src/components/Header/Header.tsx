import { IconButton, Button } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { Sidebar } from './Sidebar';
import { useState } from 'react';
import headerImg from '../../assets/react.svg'

export const Header = () => {
    const [isSidebarOpen, setISidebarOpen] = useState<boolean>(false);

    const handleSidebarOpen = () => {
        setISidebarOpen((prevValue) => !prevValue);
    }

    return (
        <header className="header-nav-container">
            <Button variant="contained" className="header-btn-primary" aria-label="home">Mein Profil</Button>
            <a className="header-logo-container" href="#">
                <img src={headerImg} alt="the logo of skill orakel app ... to be defined"></img>
                <h1>Skill-Orakel</h1>
            </a>
            <nav>
                <IconButton
                    className="navbar-hamburger-menu" color="primary"
                    onClick={() => handleSidebarOpen()}
                >
                    <MenuIcon></MenuIcon></IconButton>
                <IconButton
                    className="navbar-account-menu">
                    <AccountCircleIcon></AccountCircleIcon></IconButton>
            </nav>
            <Sidebar open={isSidebarOpen} onClose={handleSidebarOpen}></Sidebar>
        </header>
    );
}