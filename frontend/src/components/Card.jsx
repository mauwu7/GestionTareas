import styles from '../styles/Card.module.css'
import { mdiShareAll, mdiStarPlusOutline, mdiEyePlusOutline } from '@mdi/js'
import { Icon } from '@mdi/react'

export default function Card({title, description}){
    return(
        <div className={styles.card}>
            <h3>Super cool project</h3>
            <p>
                Vestibulum justo ligula, accumsan vel eros ut, ornare feugiat diam. 
                Quisque velit ipsum, pharetra non lorem nec, eleifend gravida tortor. 
                Vestibulum cursus sodales lacus, at vulputate purus eleifend eget.
            </p>
            <div>
                <Icon path={mdiEyePlusOutline} size={1} />
                <Icon path={mdiShareAll} size={1}/>
                <Icon path={mdiStarPlusOutline} size={1}/>
            </div>
        </div>
    )
}