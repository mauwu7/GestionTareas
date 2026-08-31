const baseUrl = 'http://localhost:8080/auth'

const login = async (content) => {

    const options = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify(content)
    }

    const response = await fetch(`${baseUrl}/login`, options)

    if(!response.ok){
        throw new Error('Login failed')
    }
    

    return await response.json()
}

const createUser = async (content) => {

    console.log(content)

    const options = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify(content)
    }

    const response = await fetch(`${baseUrl}/registro`, options)

    return await response.json()

}


export default { login, createUser }