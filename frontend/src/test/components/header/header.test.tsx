import { cleanup, fireEvent, render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it } from 'vitest';
import { Header } from '../../../components/Header/Header';

describe('test header component', () => {

    beforeEach(() => {
        cleanup();
    });

    it("renders button with display value 'mein Profil'", () => { 
        render(<Header></Header>);
        const profilButton = screen.getByLabelText('home');
        expect(profilButton).toBeInTheDocument();
    });

    it('renders link element containing the logo of the site', () => { 
        render(<Header></Header>);
        const logoLink = screen.getByRole('link');
        const logoImage = screen.getByAltText('the logo of skill orakel app ... to be defined');
        expect(logoLink).toContainElement(logoImage);
    });

    it('renders link element containing the title of the site', () => { 
        render(<Header></Header>);
        const logoLink = screen.getByRole('link');
        const title = screen.getByText('Skill-Orakel');
        expect(logoLink).toContainElement(title);
    });

    it('renders hamburgerMenu button to open sidebar', () => { 
        render(<Header></Header>);
        const buttons = screen.getAllByRole('button');
        const hamburgerMenu = buttons.find(button => button.classList.contains('navbar-hamburger-menu'));
        expect(hamburgerMenu).toBeInTheDocument();
    });

    it('renders account settings button', () => { 
        render(<Header></Header>);
        const buttons = screen.getAllByRole('button');
        const accountMenu = buttons.find(button => button.classList.contains('navbar-account-menu'));
        expect(accountMenu).toBeInTheDocument();
    });

    it('does not render sidebar when hamburger button has not clicked', async () => {
        render(<Header></Header>);
        const navbar = await screen.queryByTestId('sidebar');
        expect(navbar).not.toBeInTheDocument();
    })

    it('renders sidebar when hamburger menu is clicked', async () => {
        const user = userEvent.setup();
        render(<Header></Header>);
        const buttons = screen.getAllByRole('button');
        const hamburgerMenu = buttons.find(button => button.classList.contains('navbar-hamburger-menu'));

        if(hamburgerMenu == null){
            throw new Error('Test failed because hamburger button could not be found in component');
        }

        await user.click(hamburgerMenu);
        const navbar = await screen.getByTestId('sidebar');
        expect(navbar).toBeInTheDocument();
    });

    it('does not render sidebar when user clicks somewhere on the screen while sidebar is open', async () => {
        const user = userEvent.setup();
        render(<Header></Header>);
        const buttons = screen.getAllByRole('button');
        const hamburgerMenu = buttons.find(button => button.classList.contains('navbar-hamburger-menu'));

        if(hamburgerMenu == null){
            throw new Error('Test failed because hamburger button could not be found in component');
        }
        
        await user.click(hamburgerMenu);
        // sidebar now open
        /* 
            clicking on the backdrop should close sidebar
            it will still be mounted on the react DOM but not visible
        */ 
        const backdrop = document.getElementsByClassName('MuiBackdrop-root');
        await fireEvent.click(backdrop[0]);
        const navbar = await screen.queryByTestId('sidebar');
        expect(navbar).not.toBeVisible();
    });
})