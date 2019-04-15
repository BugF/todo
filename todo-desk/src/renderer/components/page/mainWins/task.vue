<template>
  <el-tabs size="mini" style="padding:0px 5px" v-model="activeName" @tab-click="handleClick">
    <el-tab-pane size="mini" label="今日" name="today">
      <el-button-group size="mini">
        <el-button size="mini" plain icon="el-icon-edit-outline"></el-button>
        <el-button size="mini" plain icon="el-icon-share"></el-button>
        <el-button size="mini" plain icon="el-icon-delete"></el-button>
      </el-button-group>
      <el-table size="mini" :show-header="false" :data="tableData" stripe style="width: 100%">
        <el-table-column prop="date" label="日期" width="80">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="80">
        </el-table-column>
        <el-table-column prop="address" label="地址">
        </el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane size="mini" label="所有" name="all">配置管理</el-tab-pane>
    <el-tab-pane size="mini" label="项目" name="project">角色管理</el-tab-pane>
    <el-tab-pane size="mini" label="大厅" name="fourth">定时任务补偿</el-tab-pane>
  </el-tabs>
</template>
<script>
  export default {
    data() {
      return {
        activeName: 'today',
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      }
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  }
</script>
