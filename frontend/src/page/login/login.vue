<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="width: 500px;background-color: white">
      <div style="text-align: center;margin:0 20px">
        <div style="margin-top: 150px">
          <div style="font-size: 25px;font-weight: bold">登录</div>
          <div style="font-size: 14px;color: grey">请先输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 50px">
          <el-input v-model="form.username" type="text" placeholder="用户名/邮箱">
            <template #prepend>
              <el-icon slot="prefix">
                <User/>
              </el-icon>
            </template>
          </el-input>
          <el-input v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码">
            <template #prepend>
              <el-icon slot="prefix">
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </div>
        <el-row style="margin-top: 5px">
          <el-col :span="12" style="text-align: left">
            <el-checkbox v-model="form.remember" label="记住我"/>
          </el-col>
        </el-row>
        <div style="margin-top: 40px">
          <el-button @click="login()" style="width: 270px" type="success" color="#626aef">登录</el-button>
        </div>
        <el-divider>
          <span style="color: grey;font-size: 10px">没有帐号</span>
        </el-divider>
        <div>
          <el-button @click="router.push('/register')" style="width: 270px" type="warning">注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {Lock, User} from '@element-plus/icons-vue'
import {reactive} from "vue";
import {get, post} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";

const form = reactive({
  username: '',
  password: '',
  remember: false,
})
const store = useStore()
const login = () => {
  if (!form.username||!form.password){
    ElMessage.warning('请输入用户名或密码')
  }else{
    post('/api/auth/login',{
      username:form.username,
      password:form.password,
      remember:form.remember
    },(message)=>{
      ElMessage.success(message)
      console.log(form)
      get('/api/user/me', (message) => {
        store.auth.user = message
        router.push("/company")
      }, () => {
        store.auth.user = null
      })
    })
  }

}

</script>

<style scoped>

</style>
