<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
     router
  >
    <el-menu-item index="/company">
      <el-icon><Avatar/></el-icon>
      <template #title>货代公司管理</template>
    </el-menu-item>

    <el-menu-item index="/store">
      <el-icon><House /></el-icon>
      <template #title>港口公司场地管理</template>
    </el-menu-item>

    <el-menu-item index="/instore">
      <el-icon><Coin /></el-icon>
      <template #title>进口订单管理</template>
    </el-menu-item>

    <el-menu-item index="/outstore">
      <el-icon><Document /></el-icon>
      <template #title>出口订单管理</template>
    </el-menu-item>

    <el-menu-item index="/free">
      <el-icon><View /></el-icon>
      <template #title>费用详情</template>
    </el-menu-item>

    <el-menu-item @click="logout" index="/" >
      <el-icon><HomeFilled /></el-icon>
      <template #title>退出</template>
    </el-menu-item>

  </el-menu>
</template>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>

<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";

const store = useStore()
const logout = () => {
  get('/api/auth/logout', (message) => {
    store.auth.user = null
  })
}
</script>