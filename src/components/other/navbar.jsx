import React, {Component, useEffect, useState} from 'react';
import {Avatar, Badge, Button, Container, Fade, makeStyles, Menu, MenuItem, withStyles} from "@material-ui/core";
import LocalStorageService from "../../services/LocalStorageService";
import {connect} from "react-redux";
import {Link} from "react-router-dom";
import {getFilms as getFilmsAction} from "../../redux/modules/films";
import SearchIcon from '@material-ui/icons/Search';
import UserService from "../../services/UserService";
import GroupWorkIcon from '@material-ui/icons/GroupWork';


const StyledBadge = withStyles((theme) => ({
    badge: {
        backgroundColor: '#44b700',
        color: '#44b700',
        boxShadow: `0 0 0 2px ${theme.palette.background.paper}`,
        '&::after': {
            position: 'absolute',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            borderRadius: '50%',
            animation: '$ripple 1.2s infinite ease-in-out',
            border: '1px solid currentColor',
            content: '""',
        },
    },
    '@keyframes ripple': {
        '0%': {
            transform: 'scale(.8)',
            opacity: 1,
        },
        '100%': {
            transform: 'scale(2.4)',
            opacity: 0,
        },
    },
}))(Badge);

function Navbar({films, getFilms, isUser, user}) {

    const [search, setSearch] = useState('');
    const [loading, setLoading] = useState(false);
    const [filtered, setFiltered] = useState([]);

    const logOut = () => {
        LocalStorageService.logOut();
    }

    useEffect(()=> {
        getFilms()
    }, [])

    const handleInput = (e) => {
        let temp = [];

        films.films.map((_f)=> {
            if (_f.name.toLowerCase().search(e.target.value.replace(/[!@#$%^&*]/g, "").toLowerCase()) != -1) temp.push(_f);
        } )

        setSearch(e.target.value || "");
        setFiltered(temp);
    }


    const filmCollector = () => {
        if (filtered.length > 10) {
            return filtered.slice(0, 10).map(f => <a>{f.name}</a>)
        } else if (filtered.length > 0) {
            return filtered.map(f => <Link to={`/film/${f.id}`}
                                                   onClick={() => setTimeout(() => window.location.reload(), 1000)}>{f.name}</Link>)
        }
    }


    return (<div className="navigation-bar shadow-lg">
        <div className="navigation-bar-container">
            <Link to="/" className="navbar-logo" onClick={() => {
                setTimeout(() => {
                    window.location.reload()
                }, 200)
            }}><GroupWorkIcon/></Link>
            {<div className="dropdown">
                <div className="search-form">
                    <input className="search-bar" type="text" placeholder="Search by title..."
                           onChange={handleInput}/>
                    <Link to={`/films`}>
                        <div className="search-button"><SearchIcon/></div>
                    </Link>
                </div>
                <div className="dropdown-content" style={{width: "100%", color: "white"}}>
                    {filtered.length > 0 ? filmCollector(filtered) : <a>No movies found</a>}
                </div>
            </div>}
            <div className="dropdown">
                <StyledBadge
                    overlap="circle"
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'right',
                    }}
                    variant="dot"
                >
                    <Avatar className="navbar-image" alt="Remy Sharp"
                            src={`${isUser == true ? user.mainPictureUrl :
                                "http://tinygraphs.com/isogrids/tinygraphs?theme=sugarsweets&numcolors=4&size=220&fmt=svg"}`}/>
                </StyledBadge>
                {isUser == true ?
                    <div className="dropdown-content">
                        <div><a className="d-block" href="/user/">Signed in
                            as <strong>{user.username}</strong></a></div>
                        <div role="none" className="dropdown-divider-d"></div>
                        {UserService.userHasRole("ROLE_ADMIN") == false ||
                        <div>
                            <div><a className="d-block" href="/admin">Admin Panel</a></div>
                            <div role="none" className="dropdown-divider-d"></div>
                        </div>
                        }
                        <a href="#" onClick={logOut}>Logout</a>
                    </div> : <div className="dropdown-content">
                        <a href="#">You are not logged in</a>
                    </div>}
            </div>
        </div>
    </div>)
}

export default connect(({films}) => ({films}), {getFilms: getFilmsAction})(Navbar)