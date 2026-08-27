import {useState} from 'react'
import loginService from '../services/loginService'

export default function LoginForm(){
    
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handleLogin = async (event) =>{
        event.preventDefault()
        await loginService.login(email, password)
        setEmail("")
        setPassword("")
    }

    return(
        <form onSubmit={handleLogin}>
            <div>
                <label>
                    Email
                    <input type='text' value={email} onChange={({target}) => setEmail(target.value)}/>
                </label>
            </div>
            <div>
                <label>
                    Password
                    <input type='text' value={password} onChange={({target}) => setPassword(target.value)}/>
                </label>
            </div>
            <button type='submit'>Submit</button>
        </form>
    )
}