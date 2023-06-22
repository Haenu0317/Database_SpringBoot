<template>
  <el-container style="height: 100%">
    <el-aside width="auto">
      <CommonAside/>
    </el-aside>
    <el-container>
      <el-header>
        <CommonHead/>
      </el-header>

      <el-main>
        <el-card shadow="never">
          <el-row :gutter="20">
            <el-col :span="3">
              <el-input placeholder="公司ID" v-model="form.companyId"></el-input>
            </el-col>
            <el-col :span="4">
              <el-input placeholder="公司名称" v-model="form.companyName"></el-input>
            </el-col>
            <el-col :span="4">
              <el-input placeholder="所属港口" v-model="form.companyBelongPort"></el-input>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="负责人" v-model="form.companyPrincipal"></el-input>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="电话" v-model="form.companyPhnumber"></el-input>
            </el-col>
          </el-row>
        </el-card>
        <el-row :gutter="20" class="el_row">
          <el-col :span="4">
            <el-input placeholder="请输入要删除的公司ID" v-model="del.companyId"></el-input>
          </el-col>
          <el-button type="danger" :icon="Delete" @click="delcompany" circle />
          <el-row class="el_bu">
            <el-button type="success" round @click="inscompany">添加</el-button>
            <el-button type="primary" round @click="updatacompany">修改</el-button>
            <el-button type="info" round @click="initcompanylist">查看</el-button>
          </el-row>
        </el-row >
        <el-table :data="tableData" stripe style="width: 100%">
          <el-table-column :prop="item.prop" :label="item.label" v-for="(item, index) in options" :key="index"/>
        </el-table>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>
import {Delete} from "@element-plus/icons-vue";
import CommonAside from "@/componesnt/CommonAside"
import CommonHead from "@/componesnt/CommonHead"
import {onMounted, ref} from "vue";
import {inscompanydata} from  "@/api/inscompanydata";
import {getcompany} from "@/api/companydata"
import {Updatacompany} from "@/api/companyupdata"
import {Delcompany} from "@/api/companyDel";
import {options} from './options'

export default {
  name: "company",
  components:{
    CommonAside,
    CommonHead
  },

  setup(){
    const form = ref({
      companyId:'',
      companyName: '',
      companyBelongPort: '',
      companyPrincipal: '',
      companyPhnumber: '',
      companyFreedays:'14',
      companyRate:'O.5'
    })

    const del = ref({
      companyId:'',
    })

    const tableData = ref([
     ])

    onMounted(()=>{
      initcompanylist()
    })
    const tmp = []

    const initcompanylist = async () => {
      const res = await getcompany()
      console.log(res.data.data)
      tableData.value = res.data.data
    }

    const inscompany = () =>{
        inscompanydata(form.value).then(res => {
        const {flag} = res.data
        if (flag === true){
          alert('添加成功')
        } else {
          alert('添加失败,请检查信息')
        }
     })
    }

    const updatacompany = ()=>{
      Updatacompany(form.value).then(res => {
          const {flag} = res.data
          if (flag === true){
            alert('修改成功')
          }else {
            alert('修改失败,请检查信息')
          }
     })
    }

    const delcompany = () => {
      Delcompany(del.value).then(res => {
        const {flag} = res.data
        if (flag === true){
          alert('删除成功')
        }else {
          alert('删除失败,公司是否存在')
        }
      })
    }
    return{form,initcompanylist,options,tmp,tableData,inscompany,updatacompany,Delete,del,delcompany}
  },

}
</script>

<style lang="less" scoped>
.el-header{
  background-color: black;
}
.el_main{
  padding-top: 0;
}
.el_row{
  padding-top: 10px;
  padding-left: 7px;
}
.el_bu{
  padding-left: 650px;
}
</style>