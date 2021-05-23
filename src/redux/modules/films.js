import axios from "axios";
import ServerException from "../../exceptions/ServerException";

const SERVER_CONFIG = require('../../constants/server')

const moduleName = 'films';

const GET_FILMS = `${moduleName}/GET_FILMS`;
const DELETE_FILMS = `${moduleName}/DELETE_FILMS`;
const GET_FILM_BY_ID = `${moduleName}/GET_FILM_BY_ID`;
const GET_FILMS_BY_NAME = `${moduleName}/GET_FILMS_BY_NAME`;
const GET_USER_FILMS = `${moduleName}/GET_USER_FILMS`;
const DELETE_FROM_LIST = `${moduleName}/DELETE_FROM_LIST`;
const ADD_TO_WATCHED = `${moduleName}/ADD_TO_WATCHED`;


const defaultState = {
    films: [],
    film: {},
    userFilms: [],
};

/*
    { type: ACTION, payload: [] }
 */
export default (state = defaultState, {type, payload}) => {
    switch (type) {
        case GET_FILMS:
            return {...state, films: payload};
        case DELETE_FILMS:
            return {...state, films: payload};
        case GET_FILM_BY_ID:
            return {...state, film: payload};
        case GET_FILMS_BY_NAME:
            return {...state, search: payload};
        case GET_USER_FILMS:
            return {...state, userFilms: payload};
        case DELETE_FROM_LIST:
            return {...state};
        case ADD_TO_WATCHED:
            return {...state};
        default:
            return state;
    }
};

export const getUsersFilms = () => async (dispatch) => {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + "/api/v1/film/GET_USER_FILMS", {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                'Authorization': `Bearer_${JSON.parse(localStorage.getItem("user")).token}`,
                withCredentials: true,
            },
        }).then(({data}) => dispatch({type: GET_USER_FILMS, payload: data})).catch(error =>
                ServerException(error.response.message, error.response.status))
}

export const getFilms = () => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + "/api/v1/open/film/get", {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                withCredentials: true,
            },
        }).then(({data}) => dispatch({type: GET_FILMS, payload: data}))
    } catch (error) {
        console.log(error);
    }
}

export const getFilmsByName = (name) => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + `/api/v1/open/film/name/${name}`, {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                withCredentials: true,
            },
        }).then(({data}) => dispatch({type: GET_FILMS_BY_NAME, payload: data}));
    } catch (error) {
        console.error(error)
    }
}


export const deleteFilm = (id) => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + `/api/v1/open/film/delete/${id}`, {
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                withCredentials: true,
            },
        });
        dispatch({type: DELETE_FILMS, payload: getFilms()})
    } catch (error) {
        console.log(error);
    }
}

export const getFilmById = (id) => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + `/api/v1/open/film/${id}`, {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Methods": "DELETE, POST, GET, OPTIONS",
                withCredentials: true,
            },
        }).then(({data}) => dispatch({type: GET_FILM_BY_ID, payload: data}))
    } catch (error) {
        console.log(error)
    }
}

export const addToWatched = (id) => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + `/api/v1/film/ADD_TO_VIEWED_LIST/${id}`, {
            method: 'Post',
            headers: {
                'Authorization': `Bearer_${JSON.parse(localStorage.getItem("user")).token}`,
                'Content-Type': 'text/plain',
                'X-My-Custom-Header': 'value-v',
            },
        })
    } catch (error) {
        console.error(error)
    }
}

export const deleteFromWatched = (id) => async (dispatch) => {
    try {
        axios(SERVER_CONFIG.IP + SERVER_CONFIG.PORT + `/api/v1/film/DELETE_FROM_LIST/${id}`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer_${JSON.parse(localStorage.getItem("user")).token}`,
                'Content-Type': 'text/plain',
                'X-My-Custom-Header': 'value-v',
            },
        })
    } catch (error) {
        console.error(error)
    }
}