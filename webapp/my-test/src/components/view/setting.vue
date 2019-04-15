<template>
<el-form ref="form" label-width="80px" size="mini">
  <el-form-item label="雪花背景">
      <el-switch
    v-model="snowBackground"
    active-text="开"
    inactive-text="关">
    </el-switch>
  </el-form-item>
  <el-form-item label="雪花背景颜色">
    <el-color-picker v-model="snowFlakesColor"></el-color-picker>
  </el-form-item>
 
  <el-form-item size="mini">
    <el-button type="primary" @click="saveSetting">保存</el-button>
    <!-- <el-button>取消</el-button> -->
  </el-form-item>
</el-form>
    

</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      snowBackground:null,
      snowFlakesColor:'',
      fontMenu:'',
    }
  },
  mounted(){
    this.loadSetting()
  },
  methods:{
    saveSetting(){
          this.$storage.set('fontMenu',this.fontMenu);
          this.$storage.set('snowFlakesColor',this.snowFlakesColor)
          this.$storage.set('snowBackground',this.snowBackground)
          this.$router.push({
            path:"/"
          })
    },
    loadSetting(){
        this.snowBackground=this.$storage.get('snowBackground')
        this.snowFlakesColor=this.$storage.get('snowFlakesColor')
        this.fontMenu=this.$storage.get('fontMenu')
         if(null==this.snowBackground){
          this.snowBackground=true;
        }
        if(null==this.fontMenu){
          this.fontMenu=false;
        }
        if(null==this.snowFlakesColor){
          this.snowFlakesColor='red';
        }      
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
