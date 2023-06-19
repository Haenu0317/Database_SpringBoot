<script setup>
import {Lock, User} from '@element-plus/icons-vue'
</script>

<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;background-color: dodgerblue" src>
      <el-image style="width: 100%;height: 100%" fit="cover"
                src="src/picture/背景图1.jpg"/>
    </div>
    <div style="width: 500px;background-color: white">
      <div style="text-align: center;margin:0 20px">
        <div style="margin-top: 150px">
          <div style="font-size: 25px;font-weight: bold">登录</div>
          <div style="font-size: 14px;color: grey">请先输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 50px">
          <el-input type="text" placeholder="用户名/邮箱">
            <template #prepend>
              <el-icon slot="prefix"><User/></el-icon>
            </template>
          </el-input>
          <el-input type="password" style="margin-top: 10px" placeholder="密码">
            <template #prepend>
              <el-icon slot="prefix"><Lock/></el-icon>
            </template>
          </el-input>
        </div>
        <el-row style="margin-top: 5px">
          <el-col :span="12" style="text-align: left">
            <el-checkbox v-model="checked1" label="记住我"/>
          </el-col>
          <el-col :span="12" style="text-align: right">
            <el-link>忘记密码?</el-link>
          </el-col>

        </el-row>
        <div style="margin-top: 40px">
          <el-button style="width: 270px" type="success">登录</el-button>
        </div>
        <el-divider>
          <span style="color: grey;font-size: 10px">没有帐号</span>
        </el-divider>
        <div>
          <el-button style="width: 270px" type="warning">注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
