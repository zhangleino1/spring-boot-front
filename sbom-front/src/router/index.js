import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/communities/list'
  },
  {
    path: '/communities/list',
    name: 'CommunitiesList',
    component: () => import('../views/communities/CommunitiesList.vue'),
    meta: { title: '社区列表' }
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('../views/NotFound.vue'),
    meta: { title: '404' }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 设置页面标题
router.beforeEach((to, from, next) => {
  document.title = to.meta.title ? `${to.meta.title} - 社区管理系统` : '社区管理系统'
  next()
})

export default router
