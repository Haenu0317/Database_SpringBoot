import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from "@/stores";
import welcome from '@/views/Welcome.vue'
import loginPage from '@/page/login/login.vue'
import registerPage from '@/components/welcome/RegisterPage.vue'
import store from '../page/store/store.vue'
import company from '../page/company/company.vue'
import instore from '../page/instore/instore.vue'
import free from '../page/free/free.vue'
import outstore from "@/page/outstore/outstore";

const routes = [
    {
        path: '/',
        name: 'welcome',
        component: welcome,
        children: [
            {
                path: '',
                name: 'welcome-login',
                component: loginPage
            }, {
                path: 'register',
                name: 'welcome-register',
                component: registerPage
            }
        ]
    },
    {
        //仓库管理
        path:'/store',
        name:'store',
        component: store
    },
    {
        //公司管理
        path:'/company',
        name:'company',
        component: company
    },
    {
        //进口订单管理
        path:'/instore',
        name:'instore',
        component: instore
    },
    {
        //出口订单管理
        path:'/outstore',
        name:'outstore',
        component: outstore
    },
    {
        //出口订单管理
        path:'/free',
        name:'free',
        component: free
    }
]

const router = createRouter({
    history: createWebHistory(""),
    routes,
});

router.beforeEach((to,from,next) =>{
    const store = useStore()
    if (store.auth.user != null && to.name.startsWith('welcome-')) {
        next('/company')
    }else if (store.auth.user == null && to.fullPath.startsWith('/company')) {
        next('/')
    }else if (to.matched.length === 0){
        next('/company')
    }else {
        next()
    }
})

export default router
