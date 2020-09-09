import axios from 'axios'
import store from '//plugins/store'
import router from "//plugins/router";

const instance = axios.create({
    timeout: 10000,
    headers: {'Content-type': 'application/json;charset=UTF-8'}
})

instance.interceptors.request.use(config => {
    if (store.state.token) {
        config.headers['Authorization'] = 'Bearer ' + store.state.token;
    }
    return config
}, error => {
    return Promise.reject(error)
})

instance.interceptors.response.use(
    response => {
        console.log('成功响应：', response)
        return response
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    // 返回 401 (未授权) 清除 token 并跳转到登录页面
                    store.setToken(null)
                    router.replace({
                        path: '/login',
                        query: {
                            redirect: router.currentRoute.fullPath
                        }
                    })
                    break
                case 400:
                    (new Vue()).$notification.error(error.response.data.message);
                    break
                case 403:
                    (new Vue()).$notification.error('没有权限！');
                    break;
                default:
                    alert('服务器出错，请稍后重试！')
            }
        }
        return Promise.reject(error.response) // 返回接口返回的错误信息
    }
)

export default instance