

export default function ServerException(message, code){
    if (code == 500){
        alert("Your token's has expired. Log in to your account again.")
        localStorage.removeItem('user');
        window.location.reload()
    }
}