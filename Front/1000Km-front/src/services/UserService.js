class UserService {

    login(username, password) {
        const auth = window.btoa(`${username}:${password}`);
        console.log(auth);

        const requestOptions = {
            headers: {'Authorization' : 'Basic ' + auth}
        }

        return fetch('http://localhost:8080/')

    }

}

export default new UserService();