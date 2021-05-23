import React, {Component} from "react";
import {Grid} from "@material-ui/core";
import {Link} from "react-router-dom";


export default class Wrapper extends Component {

    constructor(props, context) {
        super(props, context);
        this.state = {}
    }

    card(film) {
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

    collector(list) {
        if (window.screen.width <= 1366){
            return list.slice(0, this.props.slice).map(film => this.card(film));
        } return list.map(film => this.card(film));
    }

    render() {
        return (
            <div>
                <h2>{this.props.name}</h2>
                <Grid container spacing={3} direction="row"
                      justify={this.props.justify || "center"}
                      alignItems={this.props.alignItems || "center"}>
                    {this.collector(this.props.list)}
                </Grid>
            </div>
        )
    }


}