import React, {Component, useState} from "react";
import {Grid} from "@material-ui/core";
import {Link} from "react-router-dom";
import TableBody from "@material-ui/core/TableBody";


export default function Wrapper({films}) {

    const [filter, setFilter] = useState('');
    const [data, setData] = useState(films);


    const handleChange = (e) => {
        setFilter(e.target.value);
        let temp = [];

        films.map((f) => {
            if (f.name.toLowerCase().search(e.target.value.replace(/[!@#$%^&*]/g, "").toLowerCase()) != -1) {
                temp.push(f)
            }
        })
        setData(temp);
    }


    const card = (film) => {
        return (
            <Grid item lg={0}>
                <div className="wrapper-card" key={film.id}>
                    <img src={film.poster} alt={film.name}/>
                    <div className="info">
                        <h3>{film.name}</h3>
                        <p>{film.episodes} episodes</p>
                        <div className="card-button"><Link className="links" to={`/film/${film.id}`}>Watch the movie</Link></div>
                    </div>
                </div>
            </Grid>)
    }

    const collector = (list) => {
        return list.map(film => card(film));
    }

        return (
            <div>
                <div className="__search_bar">
                    <p className="__search_bar_label">Search</p>
                    <input type="text" className="__search_input" placeholder="Search film by name" onChange={handleChange}/>
                </div>
                <Grid container spacing={3} direction="row"
                      justify="left"
                      alignItems="left">
                    {data.length ? collector(data) : collector(films)}
                </Grid>
            </div>
        )
}