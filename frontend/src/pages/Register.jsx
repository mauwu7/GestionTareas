import { useState } from "react";
import loginService from "../services/loginService";
import styles from '../styles/LoginForm.module.css'
import { Link, useNavigate } from 'react-router-dom'
import Input from "../components/Input";


export default function RegisterForm (){

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')
    const [username, setUsername] = useState('')
    const navigate = useNavigate()

    const handleCreate = async (event) =>{
        event.preventDefault()
        await loginService.createUser({email, password, name, username})
        setEmail('')
        setName('')
        setPassword('')
        setUsername('')
        navigate('/')
    }

    return(
        <div className={styles.main}>
            <h1 className={styles.header}>Create an account</h1>
            <form className={styles.form} onSubmit={handleCreate}>
                <Input type='text' field={username} name='Username' handler={setUsername}/>
                <Input type='text' field={name} name='Name' handler={setName}/>
                <Input type='password' field={password} name='Password' handler={setPassword}/>
                <Input type='text' field={email} name='Email' handler={setEmail}/>
                <button className={styles.button} type="submit">Submit</button>
            </form>
            <p>Already have an account? <Link to='/'>Sign in now !</Link></p>
        </div>
    )

}