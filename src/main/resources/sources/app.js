import Vue from 'vue'
import App from './App.vue'
import vuetify from "./plugins/vuetify";
import router from  './plugins/router'
import axios from './plugins/axios'
import alertify from 'alertifyjs'
import 'alertifyjs/build/css/alertify.min.css'

alertify.set('notifier', 'position', 'top-center')
Vue.prototype.$notification = alertify
Vue.prototype.$http = axios

import './app.css'

const app = new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount("#app")
