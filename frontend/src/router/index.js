import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from "@/stores";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'Welcome',
            component: () => import('@/views/Welcome.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/components/welcome/LoginPage.vue')
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/components/welcome/RegisterPage.vue')
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import('@/views/indexview.vue')
        }
    ]
})
//路由守卫
router.beforeEach((to,from,next) =>{
const store = useStore()
    if (store.auth.user != null && to.name.startsWith('welcome-')) {
        next('/index')
    }else if (store.auth.user == null && to.name.startsWith('/index')) {
        next('/')
    }else if (to.matched.length === 0){
        next('/index')
    }else {
        next()
    }
})
export default router
