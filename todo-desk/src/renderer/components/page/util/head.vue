<template>
    <div style="height:40px;width:100%;background:green;-webkit-app-region: drag">
        title
        <div style="height:30px;width:100px;background:red; float: right;">
            <i class="el-icon-close" style="-webkit-app-region: no-drag" @click="closeWin"></i>
        </div>
    </div>
</template>
<script>
export default {
    methods:{
        closeWin(){
          this.$Win.exitWin()
        }
    }
    
}
</script>
