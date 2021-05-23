import axios from "axios";
import ServerException from "../../exceptions/ServerException";

const SERVER_CONFIG = require('../../constants/server')

const moduleName = 'films';

const GET_ALL_GENRES = `${moduleName}/GET_ALL_GENRES`;


const defaultState = {
    genres: []
};

/*
    { type: ACTION, payload: [] }
 */
export default (state = defaultState, {type, payload}) => {
    switch (type) {
        case GET_ALL_GENRES:
            return {...state, genres: payload};
        default:
            return state;
    }
};

export const getGenres = () => async (dispatch) => {
    axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + "/api/v1/genre/get", {
        method: 'GET',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
            'Authorization': `Bearer_${JSON.parse(localStorage.getItem("user")).token}`,
            withCredentials: true,
        },
    }).then(({data}) => dispatch({type: GET_ALL_GENRES, payload: data})).catch(error =>
        console.log(error))
}