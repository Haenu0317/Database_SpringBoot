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
              <el-input placeholder="入库单号" v-model.trim="form.inid"></el-input>
            </el-col>
            <el-col :span="5">
              <el-config-provider :locale="locale">
                <el-date-picker
                    v-model="form.intime"
                    tpye = "date"
                    placeholder="请选择入库日期"
                    :size="size"
                    value-format="YYYY-MM-DD"
                />
              </el-config-provider>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="进货车牌" v-model.trim="form.incarid"></el-input>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="入库吨数" v-model="form.inton" oninput="value=value.replace(/[^\d.]/g, '')"></el-input>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="入库件数" v-model="form.incasenum" oninput="value=value.replace(/\D/g,'')"></el-input>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="仓库ID" v-model.trim="form.instoreid"></el-input>
            </el-col>
            <el-button type="success" round @click="insseller">添加</el-button>
            <el-button type="warning" round @click="reset">重置</el-button>
          </el-row>
        </el-card>
        <el-row class="el_row"  :gutter="20" >
          <el-col :span="5">
            <el-input placeholder="请输入要删除或查找的入库单号"  v-model="delform.inid"></el-input>
          </el-col>
          <el-button type="danger" @click="delsellerdata" :icon="Delete" circle />
          <el-button type="primary" :icon="Search" @click="findoneseller">搜索</el-button>
        </el-row>
        <el-table :data="tableData" stripe style="width: 100%">
          <el-table-column :prop="item.prop" :label="item.label" v-for="(item, index) in options" :key="index">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import  CommonAside from '@/componesnt/CommonAside'
import  CommonHead from '@/componesnt/CommonHead'
import {onMounted, ref} from "vue";
import {options} from './options'
import {Findallsellerdata} from '@/api/sellerFindall'
import {inssellerdata} from '@/api/sellerins'
import {Delete,Search,Check} from "@element-plus/icons-vue"
import {Delseller} from "@/api/sellerDel"
import {findonesellerdata} from '@/api/sellerFindone'
import {ElConfigProvider, ElMessage} from "element-plus";
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {findonestoredata} from "@/api/storeFindone";

export default {
  name: "instore",
  components:{
    CommonAside,
    CommonHead,
    [ElConfigProvider.name]: ElConfigProvider,
  },
  setup(){
    const form = ref({
      inid:'',
      intime: '',
      incarid: '',
      inton: 0.0,
      incasenum: 0,
      instoreid: ''
    })
    const tableData = ref([])
    const delform = ref({
      inid:''
    })
    onMounted(()=>{
      findsellerdata()
    })
    const findsellerdata = async () =>{
      const res = await Findallsellerdata()
      tableData.value = res.data.message
    }
    const reset = () =>{
      form.value.inid = ''
      form.value.intime = ''
      form.value.incarid = ''
      form.value.inton = 0.0
      form.value.incasenum = 0
      form.value.instoreid = ''
    }
    const findoneseller = async () =>{
      if (delform.value.inid===''){
        await findsellerdata()
      }else {
        const res = await findonesellerdata(delform.value)
        tableData.value = res.data.message
      }

    }

    const insseller = () => {
      inssellerdata(form.value).then(res => {
        if (res.data.success === true) {
          ElMessage.success('添加成功')
          findsellerdata()
        } else {
          ElMessage.error(res.data)
        }
      })
    }

    const delsellerdata = () =>{
      Delseller(delform.value).then(res=>{
        if (res.data.success === true) {
          ElMessage.success('删除成功')
          findsellerdata()
        } else {
          ElMessage.error('删除失败,公司是否存在')
        }
      })
    }


    return {form,tableData,options,findsellerdata,insseller,Delete,delform,Search,delsellerdata,findoneseller,Check,reset}
  }
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
</style>