import styles from '../styles/LoginForm.module.css'

export default function Input({type, field, handler,name}){
    return(
        <div className={styles.div}>
            <label>
                <p className={styles.p}>{name}</p>
                <input className={styles.input} type={type} value={field} onChange={({target}) => handler(target.value)}/>
            </label>    
        </div>
    )
}