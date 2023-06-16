<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
      <el-form-item label="登录地址" prop="tokenId">
        <el-input
          v-model="queryParams.ipaddr"
          placeholder="请输入登录地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="list.slice((pageNum-1)*pageSize,pageNum*pageSize)"
      style="width: 100%;"
    >
      <el-table-column label="编号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="患者病历号" align="center" prop="tokenId" :show-overflow-tooltip="true"/>
      <el-table-column label="患者姓名" align="center" prop="userName" :show-overflow-tooltip="true"/>
      <el-table-column label="性别" align="center" prop="deptName"/>
      <el-table-column label="身份证号" align="center" prop="ipaddr" :show-overflow-tooltip="true"/>
      <el-table-column label="付款方式" align="center" prop="loginLocation" :show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleForceLogout(scope.row)"
            v-hasPermi="['monitor:online:forceLogout']"
          >强退
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize"/>
  </div>
</template>

<script>
// import { list, forceLogout } from "@/api/monitor/online";
import axios from 'axios';

export default {
  data() {
    return {
// 遮罩层
      loading: true,
// 总条数
      total: 0,
// 表格数据
      list: [],
      pageNum: 1,
      pageSize: 10,
// 查询参数
      queryParams: {
        ipaddr: undefined,
        userName: undefined
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

     getList() {
this.loading = true;
axios.post('http://localhost:8080/api/tuihao', {params: this.queryParams}).then(response => {
this.list = response.data.rows;
this.total = response.data.total;
this.loading = false;
});
},

     handleQuery() {
this.pageNum = 1;
this.getList();
},

     resetQuery() {
this.resetForm("queryForm");
this.handleQuery();
},
     /* 强退按钮操作 */
    handleForceLogout(row) {
      this.modal.confirm('是否确认强退名称为"' + row.userName + '"的用户？').then(function () {
        return axios.delete('/api/monitor/online/' + row.tokenId);
      }).then(() => {
        this.getList();
        this.modal.msgSuccess("强退成功");
      }).catch(() => {
      });
    }
  }
};
</script>
