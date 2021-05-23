import {Pagination} from "@material-ui/lab";
import {useEffect, useState} from "react";
import Wrapper from "./wrapper";
import {connect} from "react-redux";
import {getFilms as getFilmsAction} from "../../../redux/modules/films";

function AllFilmsComponent({films, getFilms}) {

    const [page, setPage] = useState(1);
    const [count, setCount] = useState(1);

    useEffect(()=> {
        getFilms();
    }, []);

    return (
        <div className="__container_1200">
            <Wrapper films={films.films}/>
            <Pagination count={10} variant="outlined" shape="rounded" className="pagination" onChange={(v)=> setPage(v.target.innerText)}/>
        </div>
    )
}

export default connect(({films})=> ({films}), {
    getFilms: getFilmsAction
})(AllFilmsComponent)