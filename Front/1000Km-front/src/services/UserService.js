class UserService {

    login(myPseudo, myMotDePasse) {
        // const auth = window.btoa(`${pseudo}:${motDePasse}`);

        // const requestOptions = {
        //     headers: {'Authorization' : 'Basic ' + auth}
        // }

        return fetch('http://localhost:8080/utilisateur/connexion', {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({pseudo: myPseudo, motDePasse: myMotDePasse})
        })
            // .then(response => this.#handleResponse(response))
            .then(response => response.json())
            .then(user => {
                if (user) {
                    user.authData = window.btoa(myPseudo + ':' + myMotDePasse);
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