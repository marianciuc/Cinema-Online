import React, {useEffect} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import EditIcon from '@material-ui/icons/Edit';
import DeleteOutlineIcon from '@material-ui/icons/DeleteOutline';
import {InputBase} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import SearchIcon from "@material-ui/icons/Search";
import Typography from "@material-ui/core/Typography";

const columns = [
    {id: 'id', label: 'Id', minWidth: 30},
    {id: 'name', label: 'Name', minWidth: 100},
    {
        id: 'episodes',
        label: 'Episodes',
        minWidth: 30,
        align: 'left',
    },
    {
        id: 'status',
        label: 'Status',
        minWidth: 50,
        align: 'left',
    },
    {
        id: 'created',
        label: 'Created on',
        minWidth: 100,
        align: 'left',
    },
    {
        id: 'updated',
        label: 'Updated on',
        minWidth: 100,
        align: 'left',
    },
    {
        id: 'actions',
        label: 'Actions',
        minWidth: 100,
        align: 'left',
    },
];

const useStyles = makeStyles({
    root: {
        width: '100%',
    },
    container: {
        maxHeight: "80vh",
    },
    title: {
        flex: '1 1 100%',
        display: "flex",
        paddingTop: 15,
        paddingLeft: 40,
        paddingRight: 40,
        alignItems: "center",
        marginBottom: 15
    },
    input: {
        width: "100%",
        border: "1px solid #E0E0E0",
        borderRadius: "6px",
        padding: "10px"
    },
    iconButton: {
        padding: 10,
    },
});

export default function FilmsAdminComponent({films}) {
    const classes = useStyles();
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);
    const [input, setInput] = React.useState('');
    const [filmsRows, setFilmsRows] = React.useState([]);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    useEffect(() => {
        if (input.length <= 0) {
            setFilmsRows(films.films)
        }
    })

    const handleChangeInput = (e) => {
        setInput(e.target.value)
        const result =  films.films.filter(value => value.name.toLowerCase().search(e.target.value.toLowerCase()) != -1 && value);
        setFilmsRows(result || [])
    }

    const handleSearch = () => {
     const result =  films.films.filter(value => value.name.toLowerCase().search(input.toLowerCase()) != -1 && value);
     setFilmsRows(result || [])
    }

    return (
        <Paper className={classes.root}>
            <Typography className={classes.title} variant="h6" id="tableTitle" component="div">
                <InputBase
                    className={classes.input}
                    placeholder="Search films by name"
                    inputProps={{ 'aria-label': 'search users' }}
                    onChange={handleChangeInput}
                />
            </Typography>
            <TableContainer className={classes.container}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{minWidth: column.minWidth}}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {filmsRows.map(row =>
                            <TableRow>
                                <TableCell>
                                    {row.id}
                                </TableCell>
                                <TableCell>
                                    {row.name}
                                </TableCell>
                                <TableCell>
                                    {row.episodes}
                                </TableCell>
                                <TableCell>
                                    {row.status}
                                </TableCell>
                                <TableCell>
                                    {row.created}
                                </TableCell>
                                <TableCell>
                                    {row.updated}
                                </TableCell>
                                <TableCell style={{display: "flex"}}>
                                    <div className="actions">
                                        <EditIcon className="table_icon" />
                                    </div>
                                    <div className="actions">
                                        <DeleteOutlineIcon className="table_icon" />
                                    </div>
                                </TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </Paper>
    );
}