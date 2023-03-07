import http from '../main'

class CarteService {
    getAll() {
        return http.get("/carte")
    }
}

export default new CarteService();