import React, {useEffect} from 'react';
import {connect} from "react-redux";
import {Container} from "@material-ui/core";

import {getFilms as getFilmsAction} from "../../../redux/modules/films";

import BigPhoto from './components/bigPhoto'
import WrapperComponent from './components/wrapper'


function PreviewPage({films, getFilms}) {


    useEffect(() => {
        getFilms()
    }, [])

    return (
        films.films.length > 0 ? <Container maxWidth="xl">
            <div>
                <BigPhoto film={films.films[12]}/>
                <WrapperComponent list={films.films.sort((a, b) => {
                        if (new Date(a.created) < new Date(b.created) ) return 1;
                        return -1;
                    }
                ).slice(0, 8)} slice={6} name="Recently added"/>
                <WrapperComponent list={films.films.sort((a, b) => {
                        if (a.score/a.votes < a.score/a.votes ) return 1;
                        return -1;
                    }
                ).slice(0, 8)} slice={6} name="High rating"/>
            </div>
        </Container> : <div className="preload">ss{console.warn(films)}</div>
    )
}

export default connect(
    ({films}) => ({films}),
    {
        getFilms: getFilmsAction,
    }
)(PreviewPage)
