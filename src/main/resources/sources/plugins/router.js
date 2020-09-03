import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const Projects = () => import('root/pages/Projects.vue')
const Baz = {template: "<div>baz</div>"};

const routes = [
    {path: '/', component: Projects, name: "index"},
    {path: '/baz', component: Baz, name: "baz"}
];
export default new VueRouter({
    routes
})
