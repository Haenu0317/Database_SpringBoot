import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import './style/headtap.css'
import {Axios} from "axios"
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import * as echarts from 'echarts'
import {createPinia} from 'pinia'
import axios from "axios";

const app = createApp(App)

app.config.globalProperties.$echarts = echarts
//注入axios
app.config.globalProperties.$axios = Axios
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(ElementPlus, {locale: zhCn})



axios.defaults.baseURL = 'http://localhost:8080'
app.use(createPinia())
app.use(router)
app.mount('#app')
