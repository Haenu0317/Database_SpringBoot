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
              <el-input placeholder="出库单号" v-model.trim="form.outid"></el-input>
            </el-col>
            <el-col :span="5">
              <el-config-provider :locale="locale">
                <el-date-picker
                    v-model.trim="form.outdate"
                    tpye="date"
                    placeholder="请选择出库日期"
                    :size="size"
                    value-format="YYYY-MM-DD"
                />
              </el-config-provider>
              <!--              <el-input placeholder="出库日期" v-model="form.buyerDate"></el-input>-->
            </el-col>
            <el-col :span="4">
              <el-input placeholder="出库吨数" v-model.trim="form.outton"
                        oninput="value=value.replace(/[^\d.]/g, '')"></el-input>
            </el-col>
            <el-col :span="4">
              <el-input placeholder="出库件数" v-model="form.outcasenum"
                        oninput="value=value.replace(/\D/g,'')"></el-input>
            </el-col>
            <el-col :span="4">
              <el-input placeholder="取货仓库ID" v-model.trim="form.outstoreid"></el-input>
            </el-col>
            <el-button type="success" @click="insbuyer" round>出库</el-button>
            <el-button type="warning" round @click="reset">重置</el-button>
          </el-row>
        </el-card>
        <el-row :gutter="20" class="el_row">
          <el-col :span="6">
            <el-input placeholder="请输入要查找的出库单号" v-model.trim="delform.outid"></el-input>
          </el-col>
          <el-button type="primary" :icon="Search" @click="findselleronedata">查找</el-button>
        </el-row>
        <el-table :data="table" stripe style="width: 100%">
          <el-table-column :prop="item.prop" :label="item.label" v-for="(item, index) in options" :key="index">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import CommonAside from '@/componesnt/CommonAside'
import CommonHead from '@/componesnt/CommonHead'
import {onMounted, ref} from "vue";
import {Delete, Search, Check} from "@element-plus/icons-vue"
import {options} from './options'
import {insbuyerdata} from "@/api/buyerIns";
import {Findallbuyerdata} from '@/api/buyerFindall'
import {findonebuyerdata} from "@/api/buyerFindone";
import {delonebuyerdata} from "@/api/buyerDel";
import {ElConfigProvider, ElMessage} from "element-plus";
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {findonesellerdata} from "@/api/sellerFindone";

export default {
  name: "outstore",
  components: {
    CommonAside,
    CommonHead,
    [ElConfigProvider.name]: ElConfigProvider,
  },
  setup() {
    const form = ref({
      outid: '',
      outdate: '',
      outcasenum: '',
      outton: '',
      outstoreid: ''
    })
    const reset = () => {
      form.value.outid = ''
      form.value.outdate = ''
      form.value.outcasenum = ''
      form.value.outton = ''
      form.value.outstoreid = ''
    }
    const formtable = ref({
      outid: '',
      outdate: '',
      outcasenum: '',
      outton: '',
      outstoreid: '',
      outcost: ''
    })


    let locale = zhCn
    const delform = ref({
      outid: ''
    })
    const table = ref([])
    onMounted(() => {
      findOutStoreData()
    })


    const insbuyer = async () => {
      const res = await insbuyerdata(form.value)

      if (res.data.success === true) {
        ElMessage.success('出库成功')
        await findOutStoreData()
      } else {
        ElMessage.error(res.data)
      }
    }

    const deloutstore = async () => {
      const res = await delonebuyerdata(delform.value)
      if (res.data.success === true) {
        ElMessage.success('删除成功')
        await findOutStoreData();
      } else {
        ElMessage.error('删除失败,出库单号是否存在')
      }
    }

    const findOutStoreData = async () => {
      const res = await Findallbuyerdata()
      table.value = res.data.message
    }

    const findselleronedata = async () => {
      if (delform.value.outid === '') {
        await findOutStoreData();
      } else {
        const res = await findonebuyerdata(delform.value)
        table.value = res.data.message
      }
    }


    return {
      form,
      Delete,
      Search,
      table,
      options,
      insbuyer,
      findsellerdata: findOutStoreData,
      findselleronedata,
      delform,
      deloutstore,
      Check,
      locale,
      formtable,
      reset
    }
  }
}
</script>

<style lang="less" scoped>
.el-header {
  background-color: black;
}

.el_main {
  padding-top: 0;
}

.el_row {
  padding-top: 10px;
  padding-left: 7px;
}

.el_time {
  padding-right: 100px;
}

.el-des {
  padding-left: 80px;
}
</style>