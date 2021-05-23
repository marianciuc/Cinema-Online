import React, {Component} from "react";
import {AppBar, Button, IconButton, Toolbar, Typography} from "@material-ui/core";
import PowerSettingsNewIcon from '@material-ui/icons/PowerSettingsNew';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

const drawerWidth = 240;

const styles = {
    appBar: {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: drawerWidth,
    },
}

export default class Navbar extends Component {

    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <AppBar position="fixed" style={styles.appBar}>
                <Toolbar className="toolbar">
                    <Typography variant="h6" noWrap>
                        {this.props.name || "Admin panel"}
                    </Typography>
                    <div>
                        <a href="/">
                            <PowerSettingsNewIcon className="navbar-icon" />
                        </a>
                    </div>
                </Toolbar>
            </AppBar>
        )
    }
}