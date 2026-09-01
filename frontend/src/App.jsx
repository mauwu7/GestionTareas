import Login from './pages/Login'
import { useEffect, useState } from 'react'
import { Routes, Route } from 'react-router-dom'
import Register from './pages/Register'
import Home from './pages/Home'

function App() {

    const [token, setToken] = useState(null)
    const [checkingSession, setCheckingSession] = useState(true)

    useEffect(()=>{
        const loggedUserJSON = window.localStorage.getItem('loggedUser')
        if(loggedUserJSON){
            try{
              setToken(JSON.parse(loggedUserJSON))
            }
            catch{
                window.localStorage.removeItem('loggedUser')
            }
        }
        setCheckingSession(false)
     },[]) 

     if(checkingSession) return null

     /*Cambio momentaneo  */
    return(
        <Routes>                            
            <Route path='/' element={ !token ? <Home setToken={setToken}/>:<Home setToken={setToken}/>}/>
            {!token && <Route path='/create' element={<Register />}/>}
        </Routes>
    )
}
export default App
