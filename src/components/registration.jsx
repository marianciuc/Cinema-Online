import React, {Component} from 'react';

import AuthenticationService from "../services/AuthenticationService";

import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import {makeStyles} from '@material-ui/core/styles';
import {Button} from "@material-ui/core";
import LocalStorageService from "../services/LocalStorageService";
import {Link} from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        '& > * + *': {
            marginTop: theme.spacing(2),
        },
    },
}));

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const vertical = "top";
const horizontal = 'right';

export default class Registration extends Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
            username: '',
            password: '',
            open: false,
            error: false,
            email: '',
            errorText: ""
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    handleChange(event) {
        switch (event.target.type) {
            case "text":
                this.setState({username: event.target.value});
            case "password":
                this.setState({password: event.target.value});
            case "email":
                this.setState({email: event.target.value});
            default:
                break;
        }
    }


    validate(){
        const username = this.state.username;
        const password = this.state.password;
        const email = this.state.email;

        if (email.indexOf("@") == -1 || email.indexOf(".") == -1 || password.length < 6 || username.length < 6 || email.length < 5){
            this.setState({error: true, errorText: "You entered invalid data"});
            setTimeout(()=> {this.setState({error: false, errorText: ""});}, 1000)
            return false;
        } else return true;
    }


    handleSubmit(event) {
        if (this.validate()){
            AuthenticationService.register(this.state.username, this.state.password, this.state.email).then((r) => {
                if (!r){
                    this.setState({error: true, errorText: "Server error, it is possible that a user with this name already exists"})
                    setTimeout(()=> {this.setState({error: false, errorText: ""});}, 2000)
                } else {
                    this.setState({open: true});
                    setTimeout(()=> {document.location.reload()}, 1000)
                }
            });
        }
    }

    render() {
        return (<div className="container p-5 mt-5">
            <div className="authorization-box">
                <h1 className="welcome-text">Welcome to Online Cinema! 👋</h1>
                <p className="welcome-info">Enter your username & password to continue.</p>
                <div className="authorization-form">
                    <p font-weight="600" color="grey.5" className="tag">Username<span
                        className="tag">*</span></p>
                    <input type="text" placeholder="Your login" onChange={this.handleChange}/>
                    <p font-weight="600" color="grey.5" className="tag">Email<span
                        className="tag">*</span></p>
                    <input type="email" placeholder="Your email" onChange={this.handleChange}/>
                    <p font-weight="600" color="grey.5" className="tag">Password<span
                        className="tag">*</span></p>
                    <input type="password" placeholder="Your password" onChange={this.handleChange}/>
                    <div className="button-orange" onClick={this.handleSubmit}>Register</div>
                    <p className="_info text-center">If you are already registered, log in <Link to={"/"}> here</Link>.</p>
                    <Snackbar anchorOrigin={{vertical, horizontal}} open={this.state.open}
                              autoHideDuration={1000}>
                        <Alert severity="success">
                            Successful registration!
                        </Alert>
                    </Snackbar>
                    <Snackbar anchorOrigin={{vertical, horizontal}} open={this.state.error}
                              autoHideDuration={1000} resumeHideDuration={1000}>
                        <Alert severity="error">
                            {this.state.errorText}
                        </Alert>
                    </Snackbar>
                </div>
            </div>
        </div>)
    }
}

