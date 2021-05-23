import {Grid} from "@material-ui/core";
import {Link} from "react-router-dom";
import React from "react";

export default function WrapperSearchComponent({films}) {
    const card = (film) =>  {
        return (
            <Grid item lg={0}>
                <div className="wrapper-card_search" key={film.id}>
                    <img src={film.poster} alt={film.name}/>
                    <div className="info_search">
                        <h3>{film.name}</h3>
                        <p>{film.episodes} episodes</p>
                        <div className="card-button_search"><Link className="links" to={`/film/${film.id}`}>Watch the movie</Link></div>
                    </div>
                </div>
            </Grid>)
    }


    return (
        <div>
                <Grid container spacing={3} direction="row"
                      justify="flex-start"
                      className="grid_content"
                      alignItems="left"
                      justifyContent="space-between"
                >
                    {console.log(films.length + "films")}
                    {films.length && films.map(film => card(film))}
                </Grid>
        </div>
    )
}