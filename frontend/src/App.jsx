import Login from './pages/Login'
import { useEffect, useState } from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Register from './pages/Register'

function App() {

    const [token, setToken] = useState(null)

    useEffect(()=>{
        const loggedUserJSON = window.localStorage.getItem('loggedUser')
        if(loggedUserJSON){
            setToken(JSON.parse(loggedUserJSON))
        }
     },[token]) ///Cambiar

    return(
        <>
        {!token ? 
        <Router>
            <Routes>
                <Route path='/' element={ <Login/> }/>
                <Route path='/create' element={ <Register /> }/>
            </Routes>
        </Router>
        :
        <Router>
            <Routes>
                <Route path='/home'/>
            </Routes>
        </Router>
        }
        </>
    )
}
export default App
