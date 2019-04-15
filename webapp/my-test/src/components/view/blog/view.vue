<template>
<div style="width: 100%;">
  <el-button size="mini" type="primary" @click="goBack">返回</el-button>
  <div style="padding: 0 11px;">
  <h3>{{blog.title}}</h3>
  </div>
 <div v-html="blog.content" style="padding: 0 11px;">
    {{blog.content}}
 </div>
  

</div>
</template>

<script>
export default {
  name: "blog",
  data() {
            return {
                id:this.$route.params.id,
                blog:null,
            }
        },
        mounted() {
          this.getBlog()
        },
        methods: {
         
          getBlog(){
            this.$post("/api/blog/getById",this.id).then(response => {
                let data=response;
                if(data.status==='true'&&data.datas.length===1){
                  this.blog=data.datas[0];
                }
            });
          },
         
          goBack(){
            this.$router.push({
              path:'/blogs'
            })
          },
          goEdit(){
                  this.$router.push({
                    path:'/blog/edit/'+this.id
                  })
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
