import { useState } from 'react'
import loginService from '../services/loginService'
import styles from '../styles/LoginForm.module.css'
import { Link } from 'react-router-dom'
import Input from '../components/Input'

export default function LoginForm(){
    
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handleLogin = async (event) =>{
        event.preventDefault()
        await loginService.login({email, password})
        setEmail("")
        setPassword("")
    }

    return(
        <div className={styles.main}>
            <h1 className={styles.header}>Login</h1>
          <form className={styles.form} onSubmit={handleLogin}>
            <Input type='text' field={email} name='Email' handler={setEmail}/>
            <Input type='password' field={password} name='Password' handler={setPassword}/>
            <button className={styles.button} type='submit'>Submit</button>
          </form>
          <p>Don't have an account yet? <Link to='/create'>created now!</Link></p>
        </div>
    )
}