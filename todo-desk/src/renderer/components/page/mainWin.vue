<template>
<div>
    <head-vue></head-vue>
    <!-- <el-button @click="open()">open</el-button> -->
    <router-view></router-view>
</div>
</template>

<script>
import headMain from './util/head-main.vue'
export default {
    name: 'mainWin',
    data() {
        return {}
    },
    components: {
        "head-vue": headMain
    },
    methods: {
        open() {
            let win = this.$Win.createWin({
                width: 800,
                height: 600,
                movable :true,
                windowConfig: {
                    router: '/subWin', // 路由
                }
            })
            win.set
            win.on('closed', () => {
                win = null
            })
            win.show()
            // 关闭窗口（注：最好调用electron-vue-windows的关闭api）
            // this.$Win.closeWin()
            // win.close() // 不推荐
        }
    }
}
</script>
