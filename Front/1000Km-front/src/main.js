import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import router from '@/router'

import authHeader from "@/services/AuthHeader";


import http from 'axios'

export default http.create({
    baseURL: "http://localhost:8080/",
    headers: {
        'Content-type': 'application/json',
        'Authorization': authHeader()
    }
})
const app = createApp(App);
app.use(router)
app.mount('#app');

