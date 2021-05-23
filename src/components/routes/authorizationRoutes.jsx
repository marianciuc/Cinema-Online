import {Route, Switch} from "react-router-dom";
import React from "react";

import RegistrationComponent from "../registration";
import AuthenticationComponent from "../authentication";


export default function authorizationRoutes(){
    return(
        <Switch>
            <Route exact path="/register"><RegistrationComponent/></Route>
            <Route path="/" basename><AuthenticationComponent/></Route>
        </Switch>
    )
}