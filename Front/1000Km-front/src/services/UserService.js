class UserService {

    login(pseudo, motDePasse) {
        const auth = window.btoa(`${pseudo}:${motDePasse}`);
        console.log(auth);

        const requestOptions = {
            headers: {'Authorization' : 'Basic ' + auth}
        }

        return fetch('http://localhost:8080/utilisateur/nom' + pseudo, requestOptions)
            .then(response => this.#handleResponse(response))
            .then(user => {
                if (user) {
                    user.authData = window.btoa(pseudo + ':' + motDePasse);
                    localStorage.setItem('user', JSON.stringify(user));
                }
            })
    }

    logout() {
        localStorage.removeItem('user');
    }

    currentuser() {
        const json = localStorage.getItem('user');
        return JSON.parse(json);
    }

    #handleResponse(response) {
        return response.text().then(text => {
            const data = text && JSON.parse(text);
            if (!response.ok) {
                if (response.status === 401) {
                    // auto logout if 401 response returned from api
                    this.logout();
                    location.reload(true);
                }

                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }

            return data;
        });
    }

}

export default new UserService();