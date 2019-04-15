<template>

</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
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


    },
    loadSetting(){
        this.snowFlakesColor=this.$storage.get('snowFlakesColor')
        this.fontMenu=this.$storage.get('fontMenu')
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
