import Vue from 'vue'
import axios from 'axios'

import App from './App'
import router from './router'
import store from './store'
import Win from 'electron-vue-windows'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

if (!process.env.IS_WEB) Vue.use(require('vue-electron'))
Vue.http = Vue.prototype.$http = axios
Vue.config.productionTip = false
Win.init(router, {
  freeWindowNum: 2, // 初始空闲窗口数量（选填：默认为1）
  port: 8848 // 端口号（选填：默认8848）
})
Vue.prototype.$Win = Win
Vue.use(ElementUI);
/* eslint-disable no-new */
new Vue({
  components: { App },
  router,
  store,
  template: '<App/>'
}).$mount('#app')
