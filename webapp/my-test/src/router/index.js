import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/view/HelloWorld'
import task from '@/components/view/task'
import blog from '@/components/view/blog'
import { resolve } from 'path';



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'blog',
      component: resolve=>require(['./../View'],resolve),
      children:[
        {
          path: '/blogs',
          name: 'blog',
          component: resolve=>require(['@/components/view/blog'],resolve)
          // component: blog
        },
        {
          path: '/blog/view/:id',
          name: 'blog',
          component: resolve=>require(['@/components/view/blog/view'],resolve)
          // component: blog
        },
        {
          path: '/blog/create/:id',
          name: 'blog',
          component: resolve=>require(['@/components/view/blog/edit'],resolve)
          // component: blog
        },
        {
          path: '/setting',
          name: 'setting',
          component: resolve=>require(['@/components/view/setting'],resolve)
          // component: blog
        },
        {
          path: '/blog/edit/:id',
          name: 'blog',
          component: resolve=>require(['@/components/view/blog/edit'],resolve)
          // component: blog
        }
      ]
    },
    
  ]
})
