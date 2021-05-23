import MovieOutlinedIcon from '@material-ui/icons/MovieOutlined';
import WatchedComponent from "./components/watched";
import {useEffect, useState} from "react";
import {connect} from "react-redux";
import {deleteFromWatched as deleteFromWatchedAction} from "../../../redux/modules/films";
import SettingsOutlinedIcon from '@material-ui/icons/SettingsOutlined';

import films, {getUsersFilms as getUserFilmsAction} from "../../../redux/modules/films";
import Settings from "./components/settings";

const SETTINGS = 'SETTINGS';
const WATCHED = 'WATCHED';

function ProfileComponent({films, getUsersFilms, deleteFromWatched}) {
    const [active, setActive] = useState(WATCHED);
    const [update, setUpdate] = useState(false);

    useEffect(() => {
        getUsersFilms();
    }, [])

    const deleteFilm = (film) => {
        deleteFromWatched(film.id).then(()=> {
            getUsersFilms();
        })
    }

    return (
        <div className="profile_container">
            <div className="profile-data">
                <img src="https://avatars.githubusercontent.com/u/39910684?v=4" alt=""/>
                <h2>Username</h2>
                <h3>boosth14@gmail.com</h3>
                <p>Watched: {films.userFilms.length}</p>
                <div className="_logout_button">Log out</div>
            </div>
            <div className="profile_content">
                <div className="__tabs">
                    <div className={`__tab ${active == WATCHED && "__active"}`} onClick={() => setActive(WATCHED)}>
                        <MovieOutlinedIcon className="_tab_icon"/> Watched
                    </div>
                    <div className={`__tab ${active == SETTINGS && "__active"}`}
                         onClick={() => setActive(SETTINGS)}><SettingsOutlinedIcon className="_tab_icon"/> Settings
                    </div>
                </div>
                {active == WATCHED && <WatchedComponent films={films.userFilms} deleteAction={deleteFilm}/>}
                {active == SETTINGS && <Settings films={films.userFilms} deleteAction={deleteFilm}/>}
            </div>
        </div>
    )
}

export default connect(
    ({films}) => ({films}),
    {getUsersFilms: getUserFilmsAction, deleteFromWatched: deleteFromWatchedAction}
)(ProfileComponent)