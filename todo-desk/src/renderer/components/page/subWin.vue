<template>
<div>
    <head-vue></head-vue>
    asddas--
    <router-view></router-view>
</div>
</template>

<script>
import head from './util/head.vue'
export default {
    name: 'subWin',
    data() {
        return {}
    },
    components: {
        'head-vue': head
    }
}
</script>
