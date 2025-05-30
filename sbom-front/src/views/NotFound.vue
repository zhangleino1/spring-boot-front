<template>
  <div class="not-found-container">
    <div class="not-found">
      <div class="not-found-img">
        <img src="@/assets/images/404.png" alt="404" />
      </div>
      <div class="not-found-info">
        <h1>404</h1>
        <h2>抱歉，您访问的页面不存在</h2>
        <div class="action">
          <el-button type="primary" @click="goHome">返回首页</el-button>
          <el-button @click="goBack">返回上一页</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.push('/')
}

const goBack = () => {
  router.go(-1)
}
</script>

<style scoped>
.not-found-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.not-found {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
}

.not-found-img {
  max-width: 400px;
  padding-right: 50px;
}

.not-found-img img {
  width: 100%;
}

.not-found-info {
  padding-left: 50px;
  border-left: 1px solid #eee;
}

.not-found-info h1 {
  font-size: 72px;
  color: #409eff;
  margin-bottom: 10px;
}

.not-found-info h2 {
  font-size: 24px;
  color: #606266;
  margin-bottom: 30px;
}

.action {
  margin-top: 30px;
}
</style>
