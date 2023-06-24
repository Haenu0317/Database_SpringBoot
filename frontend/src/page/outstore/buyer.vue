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
              <el-input placeholder="出库单号" v-model="form.buyerId"></el-input>
            </el-col>
            <el-col :span="5">
               <el-config-provider :locale="locale">
                 <el-date-picker
                     v-model="form.buyerDate"
                     tpye = "date"
                     placeholder="请选择出库日期"
                     :size="size"
                     value-format="YYYY-MM-DD"
                 />
               </el-config-provider>
<!--              <el-input placeholder="出库日期" v-model="form.buyerDate"></el-input>-->
            </el-col>
            <el-col :span="4">
              <el-input placeholder="出库吨数" v-model="form.buyerTunnage" oninput="value=value.replace(/\D/g,'')" ></el-input>
            </el-col>
<!--            <el-col :span="4">-->
<!--              <el-input placeholder="出库件数" v-model="form.buyerCasenum" oninput="value=value.replace(/\D/g,'')"></el-input>-->
<!--            </el-col>-->
            <el-col :span="4">
              <el-input placeholder="取货仓库ID" v-model="form.buyerStoreid"></el-input>
            </el-col>
            <el-button type="success" @click="insbuyer" round >添加</el-button>
            <el-button type="info" round @click="findsellerdata">查看</el-button>
            <el-button type="warning" round @click="reset">重置</el-button>
          </el-row>
        </el-card>
        <el-row :gutter="20" class="el_row">
          <el-col :span="6">
            <el-input placeholder="请输入要删除或查找的订单号" v-model="delform.buyerId"></el-input>
          </el-col>
          <el-button type="danger" :icon="Delete" @click="delbuyer" circle />
          <el-button type="primary" :icon="Search" @click="findselleronedata">查找</el-button>
        </el-row>
        <el-table :data="table" stripe style="width: 100%">
          <el-table-column :prop="item.prop" :label="item.label" v-for="(item, index) in options" :key="index">
          <template v-if="item.prop === 'action'" v-slot="scope">

              <el-button type="success" :icon="Check" @click="dialogVisible = true;printf(scope.row)" circle />
          </template>
            </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>

  <el-dialog
      v-model="dialogVisible"
      title="结汇凭证"
      width="30%"
  >
    <span>
      <el-descriptions :column="1" class="el-des">
      <el-descriptions-item label="出库单号:" >{{formtable.buyerId}}</el-descriptions-item>
      <el-descriptions-item label="出库日期:">{{formtable.buyerDate}}</el-descriptions-item>
      <el-descriptions-item label="出库件数:">{{formtable.buyerCasenum}}</el-descriptions-item>
      <el-descriptions-item label="出库吨数:">{{formtable.buyerTunnage}}</el-descriptions-item>
      <el-descriptions-item label="堆存费:">{{formtable.buyerCost}}</el-descriptions-item>
      <el-descriptions-item label="取货仓库:">{{formtable.buyerStoreid}}</el-descriptions-item>
     </el-descriptions>
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false;printfsuccess()">确定打印</el-button>
        <el-button type="primary" @click="dialogVisible = false">退出</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import  CommonAside from '@/componesnt/CommonAside'
import  CommonHead from '@/componesnt/CommonHead'
import {onMounted, ref} from "vue";
import {Delete,Search,Check} from "@element-plus/icons-vue"
import {options} from './options'
import {insbuyerdata} from "@/api/buyerIns";
import {Findallbuyerdata} from '@/api/buyerFindall'
import {findonebuyerdata} from "@/api/buyerFindone";
import {delonebuyerdata} from "@/api/buyerDel";
import { ElConfigProvider } from "element-plus";
import zhCn from "element-plus/lib/locale/lang/zh-cn";

export default {
  name: "buyer",
  components:{
    CommonAside,
    CommonHead,
    [ElConfigProvider.name]: ElConfigProvider,
  },
  setup(){
    const form = ref({
      buyerId:'',
      buyerDate:'',
      buyerCasenum:'',
      buyerTunnage:'',
      buyerStoreid:''
    })
    const reset = () => {
      form.value.buyerId = ''
      form.value.buyerDate = ''
      form.value.buyerCasenum = ''
      form.value.buyerTunnage = ''
      form.value.buyerStoreid = ''
    }
    const formtable = ref({
      buyerId:'',
      buyerDate:'',
      buyerCasenum:'',
      buyerTunnage:'',
      buyerStoreid:'',
      buyerCost:''
    })

    let dialogVisible = ref()

    let locale = zhCn
    const delform = ref({
      buyerId:''
    })
    const table = ref([])
    onMounted(()=>{
      findsellerdata()
    })
    const printf = (res) =>{
      console.log(res.buyerId)
      formtable.value.buyerId = res.buyerId
      formtable.value.buyerDate  =res.buyerDate
      formtable.value.buyerCasenum = res.buyerCasenum
      formtable.value.buyerTunnage = res.buyerTunnage
      formtable.value.buyerCost = res.buyerCost
      formtable.value.buyerStoreid = res.buyerStoreid
    }
    const printfsuccess = () => {
      alert('打印成功')
    }

    const insbuyer = async () => {
      const res = await insbuyerdata(form.value)
      const {flag} = res.data
      console.log(res)
      if (flag === true){
        alert('添加成功')
      } else {
        alert('添加失败,请检查信息')
      }
    }

    const delbuyer = async () => {
      const res = await delonebuyerdata(delform.value)
      const {flag} = res.data
      if (flag === true){
        alert('删除成功')
      } else {
        alert('删除失败,检查订单是否存在')
      }
    }

    const findsellerdata = async () =>{
      const res = await Findallbuyerdata()
      table.value = res.data.data
    }

    const findselleronedata = async () =>{
      const res = await findonebuyerdata(delform.value)
      table.value = res.data.data
    }



    return {form,Delete,Search,table,options,insbuyer,findsellerdata,findselleronedata,delform,delbuyer,Check,printf,locale,dialogVisible,formtable,printfsuccess,reset}
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
.el_time{
  padding-right: 100px;
}
.el-des{
  padding-left: 80px;
}
</style>