

function Settings(){
    return (
        <div className="__profile_data_component">
            <div className="__search_component _change_password">
                <p className="profile_label">
                    Change password
                </p>
                <input type="text" placeholder="Old password" className="input_label"/>
                <input type="text" placeholder="New password" />
                <p className="hint">
                    Make sure it's at least 15 characters OR at least 8 characters including a number and a lowercase letter.
                </p>
            </div>
        </div>
    )
}

export default Settings