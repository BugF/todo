<template>
<div>

<el-button size="mini" type="primary" @click="create">新建</el-button>
<el-input v-model="search" style="width:300px;display:block;margin:5px 0" size="mini" placeholder="输入搜索关键字"></el-input>
<el-table
    size="mini"
    ref="filterTable"
    :data="blogs.filter(data => !search || data.title.includes(search))"
    style="width: 100%">
    <!-- <el-table-column
      prop="date"
      label="日期"
      sortable
      width="180"
      column-key="date"
      :filters="[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]"
      :filter-method="filterHandler"
    >
    </el-table-column> -->
      <el-table-column
      sortable
      prop="title"
      label="标题"
    >
    </el-table-column>
    <el-table-column
    sortable
      prop="modifyTime"
      label="最近修改时间"
      :formatter="timeFatter"
      width="180">
    </el-table-column>
    <el-table-column
    sortable
      prop="createTime"
      label="创建时间"
      :formatter="timeFatter"
      width="180">
    </el-table-column>
    
    
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="goEdit(scope.row)" type="text" size="small">编辑</el-button>
        <el-button @click="goView(scope.row)" type="text" size="small">查看</el-button>

        <!-- <el-button type="text" size="small">编辑</el-button> -->
      </template>
    </el-table-column>
    <!-- <el-table-column
      prop="tag"
      label="标签"
      width="100"
      :filters="[{ text: '家', value: '家' }, { text: '公司', value: '公司' }]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          :type="scope.row.tag === '家' ? 'primary' : 'success'"
          disable-transitions>{{scope.row.tag}}</el-tag>
      </template>
    </el-table-column> -->
  </el-table>
</div>
</template>

<script>
// import login from "./../util/login";
export default {
  name: "blog",
  data() {
            return {
              search:'',
               blogs:[]
            }
        },
        mounted() {
           this.listAll();
            // this.create();
            // alert($('#test').text())
        },
        methods: {
          goView(obj){
                  this.$router.push({
                    path:'/blog/view/'+obj.id
                  })
          },
          goEdit(obj){
                  this.$router.push({
                    path:'/blog/edit/'+obj.id
                  })
          },
          create(){
              this.$post("/api/blog/create").then(response => {
                let data=response;
                if(data.status==='true'){
                  this.$router.push({
                    path:'/blog/create/'+data.datas.id
                  })
                }
                
            });
          },
          timeFatter(row, column){
            console.info(column.property)
            // return 12;
                  let time=row[column.property];               
                  var re = /-?\d+/;
                  var m = re.exec(time);
                  var d = new Date(parseInt(m[0]));
                  var o = {
                      "M+": d.getMonth() + 1, //month
                     "d+": d.getDate(),    //day
                     "h+": d.getHours(),   //hour
                     "m+": d.getMinutes(), //minute
                     "s+": d.getSeconds(), //second
                     "q+": Math.floor((d.getMonth() + 3) / 3),  //quarter
                     "S": d.getMilliseconds() //millisecond
                 }
                 var format = "yyyy-MM-dd hh:mm:ss";
                 if (/(y+)/.test(format)) {
                     format = format.replace(RegExp.$1,(d.getFullYear() + "").substr(4 - RegExp.$1.length));
                 }
                 for (var k in o) {
                     if (new RegExp("(" + k + ")").test(format)) {
                         format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                     }
                 }
                 return format;
          },
          listAll(){
            this.$post("/api/blog/list").then(response => {
                let data=response;
                if(data.status==='true'){
                  this.blogs= data.datas.length>this.blogs.length?this.blogs.slice(0,data.datas.length):this.blogs;
                  $.each(data.datas,(i,o)=>{
                    this.$set(this.blogs,i,o);
                  })
                }else{
                  this.blogs=[];
                }
            });
          },
         
        }

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
