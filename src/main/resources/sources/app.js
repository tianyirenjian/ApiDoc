import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
Vue.use(VueRouter)

import './app.css'

const Projects = () => import('./pages/Projects.vue')
const Bar = {template: "<div>bar</div>"};

const routes = [
    {path: '/', component: Projects},
    {path: '/bar', component: Bar}
];

const router = new VueRouter({
    routes
});

const app = new Vue({
    router,
    render: h => h(App)
}).$mount("#app")
