import React, {Component} from 'react';

import AuthenticationService from "../services/AuthenticationService";

import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import {makeStyles} from '@material-ui/core/styles';
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

export default class Authentication extends Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
            username: '',
            password: '',
            open: false,
            error: false
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
            default:
                break;
        }
    }


    handleSubmit(event) {
        AuthenticationService.login(this.state.username, this.state.password).then(r => {
            if (LocalStorageService.isActive()){
                this.setState({open: true});
                setTimeout(()=> {document.location.reload()}, 1000)
            } else {
                this.setState({error: true});
                setTimeout(()=> {this.setState({error: false});}, 2000)
            }
        });
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
                    <p font-weight="600" color="grey.5" className="tag">Pasword<span
                        className="tag">*</span></p>
                    <input type="password" placeholder="Your password" onChange={this.handleChange}/>
                    <div className="button-orange" onClick={this.handleSubmit}>Log in</div>
                    <p className="_info text-center">If you are a new user, sign up <Link to={"/register"}> here</Link>.</p>
                    <Snackbar anchorOrigin={{vertical, horizontal}} open={this.state.open}
                              autoHideDuration={1000}>
                        <Alert severity="success">
                            Successful login!
                        </Alert>
                    </Snackbar>
                    <Snackbar anchorOrigin={{vertical, horizontal}} open={this.state.error}
                              autoHideDuration={1000} resumeHideDuration={1000}>
                        <Alert severity="error">
                            Login error!
                        </Alert>
                    </Snackbar>
                </div>
            </div>
        </div>)
    }
}

