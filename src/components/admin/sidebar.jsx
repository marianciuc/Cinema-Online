import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import GroupIcon from '@material-ui/icons/Group';
import AdbIcon from '@material-ui/icons/Adb';
import {Link} from "react-router-dom";
import MovieIcon from '@material-ui/icons/Movie';
import Filter6Icon from '@material-ui/icons/Filter6';
import SupervisedUserCircleIcon from '@material-ui/icons/SupervisedUserCircle';
import TheatersIcon from '@material-ui/icons/Theaters';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
    },
    toolbar: theme.mixins.toolbar,
}));

export default function PermanentDrawerLeft() {
    const classes = useStyles();

    return (
        <Drawer
            className={classes.drawer}
            variant="permanent"
            classes={{
                paper: classes.drawerPaper,
            }}
            anchor="left"
        >
            <div className={classes.toolbar}/>
            <Divider/>
                <List>
                {[
                    {label: 'Users', link: "/admin/users", icon: <GroupIcon/>},
                    {label: 'Roles', link: "/admin/roles", icon: <AdbIcon/>},
                    {label: 'Films', link: "/admin/films", icon: <MovieIcon/>},
                    {label: 'Genres', link: "/admin/genres", icon: <Filter6Icon/>},
                    {label: 'Characters', link: "/admin/characters", icon: <SupervisedUserCircleIcon/>},
                    {label: 'Episodes', link: "/admin/episodes", icon: <TheatersIcon/>},
                ].map((obj, index) => (
                    <Link to={obj.link} className="link">
                        <ListItem button key={obj.label}>
                            <ListItemIcon>{obj.icon}</ListItemIcon>
                            <ListItemText primary={obj.label}/>
                        </ListItem>
                    </Link>
                ))}
                </List>
        </Drawer>
    );
}