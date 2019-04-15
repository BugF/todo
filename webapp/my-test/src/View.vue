<template>
<el-container style=" border: 1px solid #eee;height:100%;" >
   <template>
     
  
  <el-aside  style="background-color: rgb(238, 241, 246);transition:width 0.2s;" :style="{width:fontMenu?'200px':'50px'}">
    <el-container>
        <el-header style="text-align: right; font-size: 12px">
      <i class="el-icon-menu" @click="fontMenu=!fontMenu"></i>
    </el-header>
   
    <div style="position: absolute;
    top: 67px;
    width: 198px;
    bottom: 63px;
    overflow-y: auto;transition:width 0.2s;" v-if="fontMenu">
     
      <div class="active_menu my_menu " >
        首页
     </div>
      <div class="my_menu" @click="goTo('/blogs')">
        广场
     </div>
     <div class="my_menu">
        今日
     </div>
      </div>
    
      <div style="bottom: 10px!important;
  position: absolute!important;transition:width 0.1s;text-align:center;" :style="{width:fontMenu?'200px':'50px'}">
  <el-tooltip class="icon menu" v-show="!fontMenu" effect="dark" content="Top Left 提示文字" placement="top-start">
        <el-button  type="primary" icon="el-icon-edit" circle></el-button>
    </el-tooltip>
    <el-tooltip class="icon menu" effect="dark" content="编辑" placement="top-start">
        <el-button  type="primary" icon="el-icon-edit" circle></el-button>
    </el-tooltip>
   <el-tooltip class="icon menu" effect="dark" content="问题反馈" placement="top-start">
        <el-button type="success" icon="el-icon-question" circle></el-button>
    </el-tooltip>
  <el-tooltip class="icon menu" effect="dark" content="消息" placement="top-start">
        <el-button type="info" icon="el-icon-message" @click="destroyS" circle></el-button>
    </el-tooltip>
   <el-tooltip class="icon menu" effect="dark" content="设置" placement="top-start">
        <el-button  type="warning" icon="el-icon-setting" @click="goToSetting" circle></el-button>
    </el-tooltip>
      </div>
    </el-container>
  </el-aside>
   </template>
  <el-container>   
    <el-main>
      <router-view></router-view>
    </el-main>
   <el-dialog
  title="设置"
  :visible.sync="settingShow"
  width="30%"
  :before-close="settingClose">
 
<div class="block">
  <span class="demonstration">背景雪花颜色</span>
  <el-color-picker v-model="snowFlakesColor"></el-color-picker>
</div>
  
  <span slot="footer" class="dialog-footer">
    <el-button size="mini" @click="settingShow = false">取 消</el-button>
    <el-button size="mini" type="primary" @click="saveSetting" >确定(刷新生效)</el-button>

  </span>
</el-dialog>

  </el-container>
</el-container>

</template>

<script>
const Snowflakes = require('magic-snowflakes');
export default {
  name: "App",
  data() {
    return {
      snowFlakesColor:'',
      snowFlakes:null,
      settingShow:false,
      projectNames:[],
      name:null,
      fontMenu: true,
      bodyHeight: 100,
      isCollapse: false
    };
  },
  mounted() {
    // color: '#f00', // Default: "#5ECDEF"
    //         container: document.querySelector('#snowflakes-container'), // Default: document.body
    //         count: 100, // 100 snowflakes. Default: 50
    //         minOpacity: 0.1, // From 0 to 1. Default: 0.6
    //         maxOpacity: 0.95, // From 0 to 1. Default: 1
    //         minSize: 20, // Default: 8
    //         maxSize: 50, // Default: 18
    //         rotation: true, // Default: true
    //         speed: 2, // The property affects the speed of falling. Default: 1
    //         wind: false, // Without wind. Default: true
    //         width: 500, // Default: width of container
    //         height: 250, // Default: height of container
    //         zIndex: 100 // Default: 9999
   
    this.$post("/api/wx/test", {}).then(response => {
      console.log(response);
    });
    // this.loadSetting()
  },
  created(){
    this.loadSetting()

  },
  updated(){
    // this.snowFlakes.destroy();
    // this.loadSetting()

  },
  methods: {
    saveSetting(){
       this.$storage.set('snowFlakesColor',this.snowFlakesColor);
       this.settingShow=false;
    },
    loadSetting(){
        let snowFlakesColor=this.$storage.get('snowFlakesColor')
        let fontMenu=this.$storage.get('fontMenu')
        
        if(null==fontMenu){
          fontMenu=false;
          this.$storage.set('fontMenu',fontMenu)
        }
         if(null==snowFlakesColor){
          snowFlakesColor='red';
          this.$storage.set('snowFlakesColor',snowFlakesColor)
        }
        this.snowFlakesColor=snowFlakesColor;
        this.fontMenu=fontMenu;
       
        this.snowFlakes=new Snowflakes({
        color:snowFlakesColor,
        wind:true,
        count: 60,
      });
        
    },
    destroyS(){
 this.snowFlakes.destroy();
    },
    createSnowflakes(){
      this.snowFlakes=new Snowflakes({
        color:'red',
        wind:true,
        count: 60,
      });
    },
    deleteSnowFlakes(){
      this.snowFlakes=null;
    },
    settingClose(){

    },
    addProjectNames(){
      this.projectNames.push(this.name)
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    goToSetting(){
      this.$router.push({
                    path:'/setting'
      })
    },
    goTo(path){
      this.$router.push({
                    path:path
      })
    },
  }
};
</script>

<style>
#app .my_menu{
  border: 1px solid #ebeef5;padding: 5px;box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);cursor: pointer;
}
#app .active_menu{
  background-color:grey;
  font-weight: 400;
  color: whitesmoke;
  border-right: 2px black solid;
}
#app .my_menu:hover{
  background-color:dimgray;
  font-weight: 400;
  color: whitesmoke;
}
#app .active_menu:hover{
  background-color:dimgray;
  font-weight: 400;
  color: whitesmoke;
  border-right: 2px black solid;
}
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.icon.menus {
  bottom: 0px !important;
  position: absolute !important;
}
.icon.menu {
     text-align: center;
    display: inline;
}
.el-button + .el-button {
  margin-left: 0px;
}
</style>

