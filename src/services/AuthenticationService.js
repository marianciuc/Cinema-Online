import axios from "axios";

const server = require('../constants/server.js')

class AuthenticationService {
    login(username, password) {
        console.log(server)
        return axios( server.IP + server.PORT + `/api/v1/auth/login`, {
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                withCredentials: true,
            },
            data: {
                username: username,
                password: password,
            }
        } ).then(res => {
            if ( res.data.token ) {
                console.log(res.data)
                localStorage.setItem( "user", JSON.stringify(res.data) );
            }
        }).catch(e => {console.log(e)});
    }

    register(username, password, email) {
        return axios( server.IP + server.PORT + `/api/v1/auth/register`, {
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                withCredentials: true,
            },
            data: {
                username,
                password,
                email
            }
        } ).catch(e => {console.log(e); return "Registration error"});
    }
}

export default AuthenticationService.prototype