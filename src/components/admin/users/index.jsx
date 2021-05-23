import {connect} from "react-redux";
import UsersTable from './components/table'
import {getUsers as getUsersAction} from "../../../redux/modules/users";
import {useEffect} from "react";

function UsersComponent ({users, getUsers}) {
    useEffect(()=> {
        getUsers();
    }, [])

        return (
            <div>
                <UsersTable users={users} />
            </div>
        )
}

export default connect(
    ({users}) => ({users}),
    {
        getUsers: getUsersAction
    }
)(UsersComponent)