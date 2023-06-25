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
          <el-col :span="4">
            <el-input placeholder="请输入要查找的公司ID" v-model="serchform.companyId"></el-input>
          </el-col>
          <el-row class="el_bu">
            <el-button type="primary" :icon="Search" @click="getcompanyfree">查找</el-button>
<!--            <dic style="padding-left: 650px"><el-button type="success" @click="getallfree">查看所有订单费用</el-button></dic>-->
          </el-row>

        </el-card>
        <el-col id="Charts" style="width: 1000px;height: 400px; padding-top: 80px; padding-left: 68px"></el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import  CommonAside from '@/componesnt/CommonAside'
import  CommonHead from '@/componesnt/CommonHead'
import {onBeforeMount, onMounted, ref} from "vue";
import * as echarts from 'echarts'
import {Findallfreedata} from "@/api/freeFindall";
import {Findallfreecompanydata} from "@/api/freeFindcompany";
import {Search} from "@element-plus/icons-vue";
import {getcompanyName} from "@/api/companyName";

export default {
  name: "free",
  components:{
    CommonAside,
    CommonHead
  },

  setup() {

    onMounted(()=>{
      getallfree()
    })
    const serchform =ref({
      companyId:''
    })
    let myChart = ref("Charts")

    const getcompanyfree = async () => {
      const res = await Findallfreecompanydata(serchform.value)
      // console.log(res.data.data)
      const datax = ref([])
      const datay = ref([])
      if (res.data.data != null){
        for (const i in res.data.data){
          datax.value.push(res.data.data[i].storeId)
          datay.value.push(res.data.data[i].StoreCost)
        }
      }

      const companyname = await getcompanyName(serchform.value)
      const title = ref([])
      title.value=companyname.data.companyName
      console.log(title.value)
      myChart = echarts.init(document.getElementById("Charts"));
      // 绘制图表
      myChart.setOption({
        title: { text: title.value+'堆存费 单位/元' },
        tooltip:{

        },
        xAxis: {
          data: datax.value
        },
        yAxis: {},
        series: [
          {
            name: "每单的堆存费",
            type: "bar",
            data: datay.value,
          },
        ],
      });
      window.onresize = function () {//自适应大小
        myChart.resize();
      };
    }

    const getallfree = async () => {
      const res = await Findallfreedata()
      // console.log(res.data.data)
      const datax = ref([])
      const datay = ref([])
      if (res.data.data != null){
        for (const i in res.data.data){
          datax.value.push(res.data.data[i].buyerId)
          datay.value.push(res.data.data[i].buyercoust)
        }
      }
      console.log(datax.value)
      myChart = echarts.init(document.getElementById("Charts"));
      // 绘制图表
      myChart.setOption({
        title: { text: "所有订单费用详情 单位/元" },
        tooltip:{

        },
        xAxis: {
          data: datax.value
        },
        yAxis: {},
        series: [
          {
            name: "每单所缴费用 单位/元",
            type: "bar",
            data: datay.value,
          },
        ],
      });
      window.onresize = function () {//自适应大小
        myChart.resize();
      };
    }

    return{getallfree,Search,serchform,getcompanyfree}
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
.el_bu{
  padding-left: 20px;
}

</style>