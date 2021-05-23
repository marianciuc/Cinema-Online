

class LocalStorageService {
    isActive(){
        return localStorage.getItem("user") ? true : false;
    }
    getUser(){
        if (localStorage.getItem("user")){
            return JSON.parse(localStorage.getItem("user")).user;
        } return null;
    }
    logOut(){
        localStorage.removeItem("user");
        document.location.reload();
    }
}

export default LocalStorageService.prototype