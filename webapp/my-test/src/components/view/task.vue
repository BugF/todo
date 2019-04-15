<template>
<div>
<el-card class="box-card">
  <div v-for="o in 4" :key="o" class="text item">
    {{'列表内容 ' + o }}
  </div>
</el-card>
<div style="    border: 1px solid #ebeef5;
    background-color: #fff;
    color: #303133;
    -webkit-transition: .3s;
    transition: .3s;position: absolute;
    bottom: 10px;
    border-radius: 4px;
    padding: 5px;
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);">
   <el-input placeholder="请输入内容" >
   
  </el-input>
</div>
</div>
</template>

<script>
import login from "./../util/login";
export default {
  name: "task",
  components: {
    login
  },
  data() {
    return {
      msg: "Welcome to Your Vue.js App"
    };
  },
  mounted() {},
  methods: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .text {
    font-size: 16px;
  }

  .item {
    padding: 5px 0;
  }

  .box-card {
    width: 100%;
  }
</style>
