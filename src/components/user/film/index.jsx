import React, {useEffect} from "react";
import {connect} from "react-redux";
import YouTubeIcon from '@material-ui/icons/YouTube';

import './css/filmPage.css';
import PlayerComponent from "./components/player";

import {getFilmById as getFilmByIdAction, getUsersFilms as getUserFilmsAction, addToWatched as addToWatchedAction} from '../../../redux/modules/films'
import {Badge} from "react-bootstrap";

function FilmPage({films, getFilmById, match, getUsersFilms, addToWatched }) {

    const [value, setValue] = React.useState(3);
    const [hover, setHover] = React.useState(-1);
    const [tab, setTab] = React.useState(1);
    const [status, setStatus] = React.useState(false);

    useEffect(() => {
        getFilmById(match.params.id);
        getUsersFilms();
    }, [])

    const genresCollector = () => {
        return films.film.genres.map(genre => <Badge variant="light" className="_genre">{genre.name}</Badge>);
    }

    const checkStatus = () => {
        let status = false;

        films.userFilms.map(
            (_f) => {
                if (_f.id == films.film.id){
                    status = true;
                }
            }
        )
        return status;
    }

    const onClickAction = () => {
        addToWatched(films.film.id)
        setStatus(true);
    }

    return (<div>
        {films.film.name ?
            <div className="main-film">
                <img className="background" src={films.film.background} alt={films.film.name}/>
                <div className="default-container">
                    <div className="poster">
                        <img src={films.film.poster} alt=""/>
                        <div className="buttons-group button">
                            {checkStatus() == true || status === true? <div className="button">Watched</div> : <div className="button" onClick={onClickAction}>Add to Viewed list</div>}
                        </div>
                    </div>
                    <div className="film-data">
                        <div>
                            <div>
                                <h1>{films.film.name}</h1>
                            </div>
                            <h2><Badge variant="light">Ratings {Math.floor(films.film.votes/films.film.score)}.0 / 10</Badge></h2>
                            <h3>Synopsis</h3>
                            <div className="synopsis">
                                {films.film.description}
                            </div>
                            <div className="genres">
                                <h3>Genres</h3>
                                <p>{genresCollector()}</p>
                            </div>
                        </div>
                        <a href={films.film.trailer} className="youtube-button"> <YouTubeIcon className="icon_youtube"/> Watch the trailer on YouTube</a>
                    </div>
                </div>
                {films.film.episodesData.length ? <PlayerComponent film={films.film}/> :<div></div>}
        </div> : <div className="preload"></div>}
    </div>)
}


export default connect(
    ({films}) => ({films}),
    {
        getFilmById: getFilmByIdAction,
        getUsersFilms: getUserFilmsAction,
        addToWatched: addToWatchedAction
    }
)(FilmPage)