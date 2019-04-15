// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'




import {
  Button,
  Select,
  Container,
  Aside,
  Header,
  Main,
  Row,
  Col,
  MenuItemGroup,
  MenuItem,
  Submenu,
  RadioGroup,
  RadioButton,
  Menu,
  Message,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Tooltip,
  Card,
  Input,
  Dialog,
  Table,
  TableColumn,
  ColorPicker,
  Form,
  FormItem,
  Switch
} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import $ from 'jquery'
Vue.use(Button)
Vue.use(Select)
Vue.use(Container)
Vue.use(Aside)
Vue.use(Header)
Vue.use(Main)
Vue.use(Row)
Vue.use(Col)
Vue.use(MenuItemGroup)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(RadioGroup)
Vue.use(RadioButton)
Vue.use(Menu)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Tooltip)
Vue.use(Card)
Vue.use(Input)
Vue.use(Dialog)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(ColorPicker)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Switch)





import axios from 'axios'
import {post,fetch,patch,put} from './util/http'
import storage from 'good-storage'
//定义全局变量
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;
Vue.prototype.$message = Message
Vue.prototype.$storage = storage



Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {
    App
  },
  template: '<App/>'
})
