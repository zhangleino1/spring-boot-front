<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item v-for="(item, index) in breadcrumbs" :key="index" :to="item.path">
      {{ item.title }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const breadcrumbs = ref([])

// 面包屑映射
const pathMap = {
  '/': { title: '首页', path: '/' },
  '/communities/list': { title: '社区列表', path: '/communities/list' }
}

// 根据路由生成面包屑
const generateBreadcrumbs = (path) => {
  const result = []
  result.push(pathMap['/'])
  
  if (path === '/') return result
  
  const segments = path.split('/')
  let currentPath = ''
  
  for (let i = 1; i < segments.length; i++) {
    currentPath += '/' + segments[i]
    if (pathMap[currentPath]) {
      result.push(pathMap[currentPath])
    }
  }
  
  return result
}

watch(
  () => route.path,
  (path) => {
    breadcrumbs.value = generateBreadcrumbs(path)
  },
  { immediate: true }
)
</script>

<style scoped>
.el-breadcrumb {
  margin-left: 10px;
  font-size: 14px;
}
</style>
