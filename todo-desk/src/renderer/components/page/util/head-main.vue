<template>
    <div style="background:darkgrey; height:40px;width:100%;-webkit-app-region: drag;border-bottom: 2px solid darkgrey;">
        <div style="height:40px;line-height:40px; font-size: xx-large;   padding: 0 20px; width:100px;float:left">
            ToDo
        </div>
        <div style="width:100px;height: 22px; line-height: 30px; padding-right: 8px; padding-top: 3px; float: right;">
            <i class="el-icon-close" style="-webkit-app-region: no-drag;float:right;margin-right:5px" @click="closeWin"></i>
            <i class="el-icon-setting" style="-webkit-app-region: no-drag;float:right;margin-right:5px"></i>
            
        </div>
    </div>
</template>
<script>
    export default {
        methods: {
            closeWin() {
                this.$Win.exitWin()
            }
        }
    }
</script>
