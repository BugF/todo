<template>
<div>
  <el-button size="mini" type="info" @click="goBack">返回</el-button>
  <el-button size="mini" type="primary"  @click="modify">保存</el-button>

   <el-input size="small" style="margin-top:10px" placeholder="请输入内容" v-model="title">
    <template slot="prepend">标题</template>
  </el-input>
<textarea id="test">
</textarea>



<el-dialog
  title="提示"
  :visible.sync="overViewShow"
  width="100%"
  >
 <div v-html="html">
    {{html}}
 </div>
  
</el-dialog>
</div>
</template>

<script>


 import Simditor from 'simditor'
  import 'simditor/styles/simditor.css'
export default {
  name: "blog",
  data() {
            return {
                id:this.$route.params.id,
                title:'',
                html:'',
                overViewShow:false,
                value :'',
                textnames: new Date().getTime(),//这里防止多个富文本发生冲突
                editor:'',//保存simditor对象
                toolbar: ['bold', 'italic', 'underline', 'strikethrough',
                    'color', '|', 'ol', 'ul', 'blockquote', 'code', '|',
                    'link', 'image', '|', 'indent', 'outdent'
                ]//自定义工具栏
            }
        },
        mounted() {
          
            this.createEditor()
            // this.create();
            // alert($('#test').text())
        },
        methods: {
         
          getBlog(){
            this.$post("/api/blog/getById",this.id).then(response => {
                let data=response;
                if(data.status==='true'&&data.datas.length===1){
                  this.title=data.datas[0].title
                  this.content=data.datas[0].content
                  this.editor.setValue(this.content)
                }
            });
          },
          modify(){
            var a={
              id:this.id,
              title:this.title,
              content:this.editor.getValue(),
            }
            this.$post("/api/blog/modify",a).then(response => {
                console.log(response);
            });
          },
          overView(){
              this.html=this.editor.getValue();
              this.overViewShow=true;
          },
          showTxt(){
            alert(this.editor.getValue())
          },
          goBack(){
            this.$router.push({
              path:'/blogs'
            })
          },
            createEditor() {
                var _this = this
                this.editor = new Simditor({
                    textarea: $('#test' ),
                    toolbar: _this.toolbar,
                    pasteImage: true,//是否允许粘贴上传图片，依赖 upload 选项，仅支持 Firefox 和 Chrome 浏览器。
                    tabIndent: true,//是否在编辑器中使用 tab 键来缩进
                });
                this.getBlog();
                this.editor.on("valuechanged", (e, src)=> {
                    _this.value = _this.editor.getValue()
                })//valuechanged是simditor自带获取值得方法
            }
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
