import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    // {
    // path: '/',
    // name: 'main',
    // component:resolve=> require(['@/components/page/mainWin'],resolve),
    // },
    {
      path: '/mainWin',
      name: 'main',
      component:resolve=> require(['@/components/page/mainWin'],resolve),
      children:[
        {
          path: '',
          name:"task",
          component:resolve=> require(['@/components/page/mainWins/task'],resolve)
        },
        {
          path: '/task',
          name:"task",
          component:resolve=> require(['@/components/page/mainWins/task'],resolve)
        }
      ]
    },
    {
      path: '/subWin',
      name: 'subWin',
      component: resolve=>require(['@/components/page/subWin'],resolve)
    },
    {
      path: '*',
      redirect: '/mainWin'
    }
  ]
})
