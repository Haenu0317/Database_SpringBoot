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
            <el-col :span="5">
              <el-input placeholder="仓库ID" v-model="form.storeId"></el-input>
            </el-col>
            <el-col :span="5">
              <el-input placeholder="货代公司ID" v-model="form.storeBelongcompany"></el-input>
            </el-col>
            <el-col :span="5">
              <el-input placeholder="所属港口" v-model="form.storeBelongport"></el-input>
            </el-col>
            <el-button type="success" round @click="insstore">添加</el-button>
            <el-button type="primary" round @click="updatastoredata">修改</el-button>

          </el-row>
        </el-card>
        <el-row :gutter="20" class="el_row">
          <el-col :span="6">
            <el-input placeholder="请输入要删除或查找的仓库ID" v-model="delform.storeId"></el-input>
          </el-col>
          <el-button type="danger" :icon="Delete" @click="delstoredata" circle/>
          <el-button type="primary" :icon="Search" @click="findonestore">查找</el-button>
        </el-row>
        <el-table :data="table" stripe style="width: 100%">
          <!--          <el-table-column :prop="item.prop" :label="item.label" v-for="(item, index) in options" :key="index"/>-->
          <el-table-column prop="storeId" label="仓库ID"></el-table-column>
          <el-table-column prop="storeTunnage" label="剩余吨数"/>
          <el-table-column prop="storeCasenum" label="剩余件数"/>
          <el-table-column prop="storeBelongport" label="所属港口"/>
          <el-table-column prop="storeBelongcompany" label="货代公司名称"/>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonAside from '@/componesnt/CommonAside'
import CommonHead from '@/componesnt/CommonHead'
import {onMounted, ref} from "vue";
import {options} from './options'
import {insstoredata} from '@/api/storeIns'
import {Findallstoredata} from "@/api/storeFindall"
import {Delete, Search} from "@element-plus/icons-vue"
import {Delstore} from '@/api/storeDel'
import {findonestoredata} from '@/api/storeFindone'
import {updatastore} from '@/api/storeUpdata'
import {ElMessage} from "element-plus";

export default {
  name: "store",
  components: {
    CommonAside,
    CommonHead
  },
  setup() {
    const form = ref({
      storeId: '',
      storeTunnage: 0,
      storeCasenum: 0,
      storeBelongport: '',
      storeBelongcompany: '',
    })
    const delform = ref({
      storeId: '',
    })
    onMounted(() => {
      findsotredata()
    })
    const find = ref([])
    const table = ref([])
    const insstore = () => {
      insstoredata(form.value).then(res => {
        if (res.data.success === true) {
          ElMessage.success('添加成功')
          findsotredata()
        } else {
          ElMessage.error(res.data)
        }
      })
    }

    const findonestore = async () => {
      if (delform.value.storeId===''){
        findsotredata()
      }else {
        const res = await findonestoredata(delform.value)
        table.value = res.data.message
      }

    }

    const updatastoredata = () => {
      updatastore(form.value).then(res => {
        if (res.data.success === true) {
          ElMessage.success('修改成功')
          findsotredata()
        } else {
          ElMessage.error('修改失败,请检查信息')
        }
      })
    }

    const findsotredata = async () => {
      const res = await Findallstoredata()
      table.value = res.data.message
    }



    const delstoredata = () => {
      Delstore(delform.value).then(res => {
        if (res.data.success === true) {
          ElMessage.success('删除成功')
          findsotredata()
        } else {
          ElMessage.error('删除失败,公司是否存在')
        }
      })
    }


    return {
      form,
      options,
      insstore,
      table,
      findsotredata,
      delform,
      Delete,
      delstoredata,
      Search,
      findonestore,
      find,
      updatastoredata
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
</style>