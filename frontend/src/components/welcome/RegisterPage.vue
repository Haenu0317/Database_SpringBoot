<template>
  <div style="text-align: center;margin: 0 20px">
    <div style="margin-top: 150px">
      <div style="font-size: 25px;font-weight: bold">注册新用户</div>
      <div style="font-size: 14px;color: grey">请先输入新的用户名和密码进行注册</div>
    </div>
    <div style="margin-top: 50px">
      <el-form :model="form" :rules="rules" ref="formRef" @validate="onValidate">
        <el-form-item prop="username">
          <el-input type="text" v-model="form.username" :maxlength="10" placeholder="用户名">
            <template #prepend>
              <el-icon slot="prefix">
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input type="password" v-model="form.password" :maxlength="20" placeholder="密码">
            <template #prepend>
              <el-icon slot="prefix">
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="password_repeat">
          <el-input type="password" v-model="form.password_repeat" :maxlength="20" placeholder="重复密码">
            <template #prepend>
              <el-icon slot="prefix">
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="email">
          <el-input type="email" v-model="form.email" placeholder="邮箱">
            <template #prepend>
              <el-icon slot="prefix">
                <Message/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
      </el-form>

      <el-form-item prop="code">
        <el-row :gutter="10" style="width: 100%">
          <el-col :span="19">
            <el-input type="text" v-model="form.code" :maxlength="6" placeholder="请输入验证码">
              <template #prepend>
                <el-icon slot="prefix">
                  <EditPen/>
                </el-icon>
              </template>
            </el-input>
          </el-col>
          <el-col :span="5">
            <el-button @click="validateEmail" type="success" :disabled="!isEmailValid">获取验证码</el-button>
          </el-col>
        </el-row>
      </el-form-item>


    </div>
    <div style="margin-top: 40px">
      <el-button @click="register" style="width: 270px" type="success">提交</el-button>
    </div>
    <div style="font-size: 14px;line-height: 15px;margin-top: 20px">
      <span style="color: grey">已有账号?</span>
      <el-link type="primary" style="translate: 0 -2px" @click="router.push('/')"> 立即登录</el-link>
    </div>
  </div>
</template>

<script setup>
import {EditPen, Lock, Message, User} from '@element-plus/icons-vue'
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";


const formRef = ref()
const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: ''
})

const register = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('/api/auth/register', {
        username: form.username,
        password: form.password,
        email: form.email,
        code: form.code
      }, (message) => {
        ElMessage.success(message)
        router.push('/')
      })
    } else {
      ElMessage.warning("请完整提交表单内容!")
    }
  });
}
const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if (!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)) {
    callback(new Error('不能包含特殊符号'))
  } else {
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
    {validator: validateUsername, trigger: ['blur', 'change']},
    {min: 2, max: 10, message: '名字长度在2-10个字符', trigger: ['blur', 'change']},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: ['blur', 'change']},
    {min: 6, max: 20, message: '密码长度在6-20个字符', trigger: ['blur', 'change']},
  ],
  password_repeat: [
    {validator: validatePass2, trigger: ['blur', 'change']},
    {min: 6, max: 20, message: '密码长度在6-20个字符', trigger: ['blur', 'change']},
  ],
  email: [
    {required: true, message: '请输入邮件地址', trigger: ['blur', 'change']},
    {type: 'email', message: '请输入合法邮件地址', trigger: ['blur', 'change']},
  ]
})

const isEmailValid = ref(false)

const onValidate = (prop, isValid) => {
  if (prop === 'email') {
    isEmailValid.value = isValid
  }
}

const validateEmail = () => {
  post("/api/auth/valid-email", {
    email: form.email
  }, (message) => {
    ElMessage.success(message)
  }, (message) => {
    ElMessage.error(message)
  })
}

</script>

<style scoped>

</style>