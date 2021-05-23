import axios from "axios";
import LocalStorageService from "../../services/LocalStorageService";
import {getFilms} from "./films";
import ServerException from "../../exceptions/ServerException";

const SERVER_CONFIG = require('../../constants/server')

const moduleName = 'users';

const GET_USERS = `${moduleName}/GET_USERS`;
const DELETE_USERS = `${moduleName}/DELETE_USERS`;

const defaultState = {
    users: [],
};

/*
    { type: GET_USERS, payload: [] }
 */
export default (state = defaultState, {type, payload}) => {
    switch (type) {
        case GET_USERS:
            return {...state, users: payload};
        case DELETE_USERS:
            return {...state, users: payload};
        default:
            return state;
    }
};

export const getUsers = () => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + `/API/v1/ADMIN/USER/GET_ALL`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer_${JSON.parse(localStorage.getItem("user")).token}`,
                'Content-Type': 'text/plain',
                'X-My-Custom-Header': 'value-v',
            },
        }).then(({data}) =>  dispatch({type: GET_USERS, payload: data})).catch(error => {
            ServerException(error.response.message, error.response.status)
        });
    } catch (error) {
        console.log(error);
    }
}