import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import React, {Component} from "react";
import {BrowserRouter, Route, Switch} from "react-router-dom";

/* Services */
import LocalStorageService from "./services/LocalStorageService";
import UserService from "./services/UserService";

/* Routes */
import UserRoutes from "./components/routes/userRoutes"
import AuthorizationRoutes from "./components/routes/authorizationRoutes"

/* Components */
import NavigationBarComponent from './components/other/navbar'

export default class App extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            user: LocalStorageService.isActive(),
            userData: LocalStorageService.getUser(),
            recently: []
        }
    }

    render() {
        return (
            <div className="body">
                <BrowserRouter>
                    {window.location.pathname.startsWith("/admin") ||
                    <NavigationBarComponent user={this.state.userData} isUser={this.state.user}/>}
                    {this.state.user == false ?
                        <AuthorizationRoutes/> : <UserRoutes userHasRoleAdmin={UserService.userHasRole("ROLE_ADMIN")}/>
                    }
                </BrowserRouter>
            </div>)
    }
}

