import {useState} from 'react'
import loginService from '../../services/loginService'
import styles from './LoginForm.module.css'

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
        <div className={styles.main}>
            <h1 className={styles.header}>Login</h1>
          <form className={styles.form} onSubmit={handleLogin}>
              <div className={styles.div}>
                  <label>
                      <p style={{marginBottom: '5px'}}>Email</p>
                      <input className={styles.input} placeholder='Email' name='email'type='text' value={email} onChange={({target}) => setEmail(target.value)}/>
                  </label>
              </div>
              <div className={styles.div}>
                  <label>
                      <p style={{marginBottom: '5px'}}>Password</p>
                      <input className={styles.input} placeholder='Password' name='password' type='password' value={password} onChange={({target}) => setPassword(target.value)}/>
                  </label>
              </div>
              <button className={styles.button} type='submit'>Submit</button>
          </form>
          <p>Don't have an account yet? <a className={styles.link}>created now!</a></p>
        </div>
    )
}