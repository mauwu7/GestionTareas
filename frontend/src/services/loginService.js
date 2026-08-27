const baseUrl = 'http://localhost:8080/auth'

const login = async (email, password) => {

    const options = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify({email: email, password: password})
    }

    const response = await fetch(`${baseUrl}/registro`, options)

    if(!response.ok){
        throw new Error('Login failed')
    }
    
    const respuesta = await response.json()

    console.log(respuesta)

    return respuesta

}


export default {login}