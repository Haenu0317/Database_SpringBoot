<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;background-color: dodgerblue" >
      <el-image style="width: 100%;height: 100%" fit="cover"
                src="src/picture/背景图1.jpg"/>
    </div>
    <div class="welcome-title">
    <div style="font-size: 50px;font-weight: bold">港口堆存费后台管理系统</div>
    </div>
    <div style="width: 500px;background-color: white;z-index: 1">
      <router-view/>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.welcome-title{
  position: absolute;
  left: 30px;
  bottom: 30px;
  color: white;
  text-shadow: 0 0 10px black;
}
</style>