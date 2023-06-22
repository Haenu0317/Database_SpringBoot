import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from "@/stores";
import welcome from '@/views/Welcome.vue'
import loginPage from '@/page/login/login.vue'
import registerPage from '@/components/welcome/RegisterPage.vue'
import store from '../page/store/store.vue'
import company from '../page/company/company.vue'
import seller from '../page/seller/seller.vue'
import buyer from '../page/buyer/buyer.vue'
import free from '../page/free/free.vue'

const routes = [
    {
        path: '/',
        name: 'Welcome',
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
        path:'/seller',
        name:'seller',
        component: seller
    },
    {
        //出口订单管理
        path:'/buyer',
        name:'buyer',
        component: buyer
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
        next('/index')
    }else if (store.auth.user == null && to.fullPath.startsWith('/company')) {
        next('/')
    }else if (to.matched.length === 0){
        next('/index')
    }else {
        next()
    }
})

export default router
