import Drawer from '@mui/material/Drawer';
import reactImg from '../../assets/react.svg'

export const Sidebar = ({ open, onClose }: { open: boolean, onClose: () => void }) => {
    return (
        <Drawer
            variant="temporary"
            ModalProps={{
                keepMounted: false,
            }}
            open={open}
            anchor={'right'}
            onClose={() => onClose()}>

            <nav className="sidebar-navigation" data-testid="sidebar">
                <a className="header-logo-container" href="#">
                    <img src={reactImg} alt="the logo of skill orakel app ... to be defined"></img>
                    <h1>Skill-Orakel</h1>
                </a>

                <ul className="sidebar-menu-list">
                    <li><a href="#">Skills</a></li>
                    <li><a href="#">Skillprofile</a></li>
                    <li><a href="#">Projekte</a></li>
                    <li><a href="#">Skills</a></li>
                </ul>
            </nav>
        </Drawer>
    );
}