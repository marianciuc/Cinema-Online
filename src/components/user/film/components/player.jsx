import {Component, useState} from "react";

import "../../../css/Player.css"
import ReactPlayer from "react-player";
import {Dropdown} from "react-bootstrap";

function Player({film}) {
    const [episode, setEpisode] = useState(1);
    const [episodesData, setEpisodesData] = useState(film.episodesData ? film.episodesData.sort(function (a, b) {
        return -(b.number - a.number);
    }) : []);


    const changeEpisode = (number) => {
        setEpisode(number)
    }
    const episodeCollector = () => {
        return episodesData.map(e => <Dropdown.Item onClick={() => {
            changeEpisode(e.number)
        }}>{e.number} episode</Dropdown.Item>)
    }
    return (
        <div>
                <div className="film-container">
                    <h1>{film.episodesData.length > 1 && "All series of the"} {film.name} in the professional voiceover</h1>
                </div>
                {film.episodesData.length > 1 && <div className="button-wrapper film-container">
                    <Dropdown>
                        <Dropdown.Toggle id="dropdown-basic" className="dropdown-button">
                            {episode} episode
                        </Dropdown.Toggle>
                        <Dropdown.Menu>
                            {episodeCollector()}
                        </Dropdown.Menu>
                    </Dropdown>
                </div>}
                <div className="film-container">
                    <ReactPlayer
                        className='player'
                        url={episodesData[episode - 1].src}
                        controls
                    />
                </div>
        </div>
    )
}

export default Player