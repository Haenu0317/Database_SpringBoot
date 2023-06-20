<template>
  <div style="text-align: center;margin: 0 20px">
    <div style="margin-top: 150px">
      <div style="font-size: 25px;font-weight: bold">注册新用户</div>
      <div style="font-size: 14px;color: grey">请先输入新的用户名和密码进行注册</div>
    </div>
    <div style="margin-top: 50px">
      <el-form :model="form" :rules="rules">
        <el-form-item  prop="username">
          <el-input type="text" v-model="form.username" placeholder="用户名">
            <template #prepend>
              <el-icon slot="prefix">
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input type="password" v-model="form.password" placeholder="密码">
            <template #prepend>
              <el-icon slot="prefix">
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="password_repeat">
          <el-input type="password" v-model="form.password_repeat"  placeholder="重复密码">
            <template #prepend>
              <el-icon slot="prefix">
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
      </el-form>


    </div>
    <div style="margin-top: 40px">
      <el-button  style="width: 270px" type="success">提交</el-button>
    </div>
    <div style="font-size: 14px;line-height: 15px;margin-top: 20px">
      <span style="color: grey">已有账号?</span>
       <el-link type="primary" style="translate: 0 -2px" @click = "router.push('/')"> 立即登录</el-link>
    </div>
  </div>
</template>

<script setup>
import {Lock, User} from '@element-plus/icons-vue'
import router from "@/router";
import {reactive} from "vue";


const form = reactive({
  username: '',
  password: '',
  password_repeat: ''
})

const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  }else if (!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
    callback(new Error('不能包含特殊符号'))
  }else{
    callback()
  }
}

const validatePass2 = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error("两次输入密码不一致"))
  } else {
    callback()
  }
}

const rules = ({
  username: [
    { validator: validateUsername, trigger:['blur','change'] },
    { min: 2, max: 10, message: '名字长度在2-10个字符', trigger: ['blur','change'] },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: ['blur','change'] },
    { min: 6, max: 20, message: '密码长度在6-20个字符', trigger: ['blur','change'] },
  ],
  password_repeat: [
    {validator:validatePass2, trigger: ['blur','change']},
    { min: 6, max: 20, message: '密码长度在6-20个字符', trigger: ['blur','change'] },
  ]
})





</script>

<style scoped>

</style>