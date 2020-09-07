import Vue from 'vue'
import store from 'root/plugins/store'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const Login = () => import('root/pages/Login.vue')
const Projects = () => import('root/pages/Projects.vue')
const Baz = {template: "<div>baz</div>"};

const routes = [
    {
        path: '/',
        component: Projects,
        name: "index",
        meta: {
            requireLogin: true
        }
    }, {
        path: '/baz',
        component: Baz,
        name: "baz",
        meta: {}
    }, {
        path: '/login',
        component: Login,
        name: "login",
        meta: {}
    }
];
const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    if (to.meta.requireLogin) {
        console.log(store.state.token)
        store.setToken('qwerty')
        next({
            path: "/login",
            query: {redirect: to.fullPath}
        })
    } else {
        next()
    }
})

export default router