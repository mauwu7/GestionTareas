import { useNavigate } from "react-router-dom"
import styles from '../styles/Home.module.css'


export default function Home ({ setToken }){

    const navigate = useNavigate()

    const logOut = () => {
        window.localStorage.removeItem('loggedUser')
        setToken(null)
        navigate('/')

    } 

    /**Cambiar orden etiquetas */

    return (
        <div className={styles.main}>
            <header className={styles.header}>

            </header>
            <nav className={styles.nav}>

            </nav>
            <main className={styles.body}>

            </main>
        </div>
    )
}