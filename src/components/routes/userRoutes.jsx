import {Route, Switch} from "react-router-dom";
import React from "react";

import SearchComponent from "../user/search";
import MainComponent from "../user/main";
import FilmsComponent from "../user/film";
import AdminComponent from "../admin"
import ProfileComponent from "../user/profile";
import AllFilmsComponent from "../user/all";


export default function userRoutes({userHasRoleAdmin}){
    return(
        <Switch>
            <Route path="/film/:id" component={FilmsComponent}/>
            <Route exact path="/user" component={ProfileComponent}/>
            <Route path="/films" component={AllFilmsComponent}/>
            { userHasRoleAdmin === false || <Route path="/admin" component={AdminComponent}/>}
            <Route path="/" component={MainComponent}/>
        </Switch>
    )
}