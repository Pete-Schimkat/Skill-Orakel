import NavigationBar from './components/Navigationbar/NavigationBar';

import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { Header } from './components/Header/Header';

function App() {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Header></Header>
      <NavigationBar></NavigationBar>
    </LocalizationProvider>
  )
}

export default App
