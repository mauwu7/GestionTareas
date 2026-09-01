import { useNavigate } from "react-router-dom"
import styles from '../styles/Home.module.css'
import { Icon } from '@mdi/react';
import { mdiViewDashboard, mdiHome, mdiCardAccountDetailsOutline, mdiMessage, mdiNoteMultiple, mdiAccountGroup } from "@mdi/js";


export default function Home ({ setToken }){

    const navigate = useNavigate()

    const logOut = () => {
        window.localStorage.removeItem('loggedUser')
        setToken(null)
        navigate('/')

    } 


    return (
        <div className={styles.main}>
            <nav className={styles.nav}>
                <div className={styles.headerNav}>
                    <Icon path={mdiViewDashboard} size={2} color='white'/>
                    <h2>Dashboard</h2>
                </div>
                <section className={styles.navMenu}>
                    <Icon path={mdiHome} size={1.2}/>
                    <p>Home</p>
                    <Icon path={mdiCardAccountDetailsOutline} size={1.2}/>
                    <p>Profile</p>
                    <Icon path={mdiMessage} size={1.2}/>
                    <p>Messages</p>
                    <Icon path={mdiNoteMultiple} size={1} />
                    <p>Tasks</p>
                    <Icon path={mdiAccountGroup} size={1} />
                    <p>Grupos</p>
                </section>
            </nav>
            <main className={styles.body}>

            </main>

        </div>
    )
}