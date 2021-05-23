import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import films, {
    getFilms as getFilmsAction
} from "../../../redux/modules/films";
import WrapperSearchComponent from "./component/wrapper";


function SearchComponent({films, getFilms, match}) {

    useEffect(() => {
            getFilms();
    }, [])



    return (
        <div className="_search_container">
            <div className="_search_content">
               <WrapperSearchComponent films={films.films}/>
            </div>
        </div>
    )
}


export default connect(
    ({films}) => ({films}),
    {
        getFilms: getFilmsAction
    }
)(SearchComponent);