import {Route, Switch} from "react-router-dom";
import RegistrationComponent from "../registration";
import AuthenticationComponent from "../authentication";
import React from "react";


export default function adminRoutes(){
    return(
        <Switch>
            <Route exact path="/register"><RegistrationComponent/></Route>
            <Route path="/" basename><AuthenticationComponent/></Route>
        </Switch>
    )
}