import {Link} from "react-router-dom";
import React from "react";

export default function ({film}) {
    return (
        <div className="__carousel">
            <img src={film.background} alt=""/>
            <div className="__carousel_info">
                <h1>{film.name}</h1>
                <h2>{film.description}</h2>
                <p>{film.episodes} episode</p>
                <div className="card-button"><Link className="links" to={`/film/${film.id}`}>Watch the movie</Link></div>
            </div>
        </div>)
}