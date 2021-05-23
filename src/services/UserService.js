import LocalStorageService from "./LocalStorageService";


class UserService {
    userHasRole(role){
        const user = LocalStorageService.getUser();

        for (const r of user.roles){
            if (r.name === role) {
                console.log("ok")
                return true;
            }
        } return false;
    }
}

export default UserService.prototype