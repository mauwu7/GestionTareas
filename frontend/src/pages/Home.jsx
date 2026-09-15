import { useNavigate } from "react-router-dom"
import styles from '../styles/Home.module.css'
import { Icon } from '@mdi/react';
import { mdiViewDashboard, mdiHome, mdiCardAccountDetailsOutline, mdiMessage, mdiNoteMultiple, mdiAccountGroup, mdiBellOutline, mdiAccount, mdiCog, mdiHelpBox, mdiSecurity } from "@mdi/js";


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
                    <ul className={styles.containerList}>
                        <li>
                            <Icon path={mdiHome} size={1.2}/>
                            <p>Home</p>
                        </li>
                        <li>
                            <Icon path={mdiCardAccountDetailsOutline} size={1.2}/>
                            <p>Profile</p>
                        </li>
                        <li>
                            <Icon path={mdiMessage} size={1.2}/>
                            <p>Messages</p>
                        </li>
                        <li>
                            <Icon path={mdiNoteMultiple} size={1} />
                            <p>Tasks</p>
                        </li>
                        <li>
                            <Icon path={mdiAccountGroup} size={1} />
                            <p>Grupos</p>
                        </li>
                    </ul>
                </section>
            </nav>
            <main className={styles.body}>
                <header className={styles.headerMain}>
                    <div>
                        <Icon path={mdiAccount} size={4}/>
                        <div>
                            <p>Hi there,</p>
                            <p>Usuario administrador (@Hola)</p>
                        </div>
                    </div>
                    <div>
                        <Icon path={mdiBellOutline} size={1.5}/>
                        <Icon path={mdiCog}  size={1.5}/>
                        <Icon path={mdiHelpBox} size={1.5} />
                        <Icon path={mdiSecurity} size={1.5}/>
                    </div>
                </header>
                <section className={styles.sectionMain}>
                    <h2>Your Groups</h2>
                    <h2>Announcements</h2>
                    <section style={{border: '1px solid black'}}>

                    </section >
                    <aside style={{border: '1px solid black'}}>

                    </aside>
                </section>
            </main>

        </div>
    )
}