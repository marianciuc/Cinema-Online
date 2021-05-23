import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

import './css/main.css'

import FilmsComponent from "./films";
import UsersComponent from './users';
import SidebarComponent from './sidebar'
import NavigationBarComponent from "./navbar";


const style = {
    root: {
        display: "flex"
    },
    content: {
        paddingTop: "68px",
        marginTop: "0px",
        minHeight: "100vh",
        width: "100%",
        position: "relative",
        backgroundColor: "#F4F5FF",
    },
}


export default class Index extends Component{


    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <div style={style.root}>
                <Router>
                    <NavigationBarComponent/>
                    <SidebarComponent/>
                    <div style={style.content}>
                            <Switch>
                                <Route keyProp={1} exact path="/admin/films"><FilmsComponent/></Route>
                                <Route keyProp={2} exact path="/admin/users"><UsersComponent/></Route>
                            </Switch>
                    </div>
                </Router>
            </div>
    );
    }
    }