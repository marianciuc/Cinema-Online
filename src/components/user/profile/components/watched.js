import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import {TableCell} from "@material-ui/core";
import TableBody from "@material-ui/core/TableBody";
import DeleteOutlineIcon from '@material-ui/icons/DeleteOutline';
import {useState} from "react";


function WatchedComponent({films, deleteAction}) {
    const [filter, setFilter] = useState('');
    const [data, setData] = useState(films);


    const handleChange = (e) => {
        setFilter(e.target.value);

        let temp = [];

        films.map((f) => {
            if (f.name.toLowerCase().search(e.target.value.replace(/[!@#$%^&*]/g, "").toLowerCase()) != -1) {
                temp.push(f)
            }
        })
        console.log(temp)
        setData(temp);
    }

    const collector = (rows) => {
        return rows.map((row) => (
            <TableRow key={row.name}>
                <TableCell className="__table_cell" component="th" scope="row">
                    <a className="__table_cell" href={`/film/${row.id}`}>{row.name}</a>
                </TableCell>
                <TableCell className="__table_cell" align="center">{row.episodes}</TableCell>
                <TableCell className="__table_cell" align="center">
                    {`${new Date(row.created).getHours()}:${new Date(row.created).getUTCMinutes()}  ${new Date(row.created).getDate()}.${new Date(row.created).getMonth()}.${new Date(row.created).getUTCFullYear()}`}
                </TableCell>
                <TableCell className="__table_cell" align="right">
                    <div className="__action_button" onClick={() => {deleteAction(row)}}>
                        <DeleteOutlineIcon className="__action_icon"/>
                    </div>
                </TableCell>
            </TableRow>
        ))
    }


    return (<div className="__profile_data_component">
        <div className="__search_component">
            <input type="text" placeholder="Search" onChange={handleChange}/>
        </div>
        <TableContainer className="__table_container">
            <Table size="small" aria-label="a dense table">
                <TableHead className="__table_header">
                    <TableRow className="__table_row">
                        <TableCell className="__table_cell" style={{width: "55%"}}>Film name</TableCell>
                        <TableCell className="__table_cell" align="center">Episodes</TableCell>
                        <TableCell className="__table_cell" align="center">Date added to the list</TableCell>
                        <TableCell className="__table_cell" align="left">Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.length ? collector(data) : collector(films)}
                </TableBody>
            </Table>
        </TableContainer>
    </div>)
}

export default WatchedComponent