import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'




const app = createApp(App);

import router from '@/router'
app.use(router)
app.mount('#app');

