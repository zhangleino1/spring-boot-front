<template>
  <el-container class="app-wrapper">
    <el-aside width="220px" class="sidebar-container">      <div class="logo-container">
        <h1 class="title">SBOM汇总统计</h1>
      </div><el-menu
        router
        :default-active="$route.path"
        class="el-menu-vertical"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409eff">
        <el-menu-item index="/communities/list">
          <el-icon><OfficeBuilding /></el-icon>
          <span>社区管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    
    <el-container>
      <el-header class="app-header">
        <div class="header-left">
          <el-icon class="fold-btn" @click="toggleSidebar">
            <Fold v-if="!sidebarCollapsed" />
            <Expand v-else />
          </el-icon>
          <breadcrumb />
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-dropdown">
              <el-avatar size="small">管理员</el-avatar>
              <span class="username">管理员</span>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item divided>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      
      <el-main>
        <router-view v-slot="{ Component }">
          <transition name="fade-transform" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref } from 'vue'
import Breadcrumb from './components/Breadcrumb.vue'

const sidebarCollapsed = ref(false)

const toggleSidebar = () => {
  sidebarCollapsed.value = !sidebarCollapsed.value
}
</script>

<style scoped>
.app-wrapper {
  height: 100%;
}

.sidebar-container {
  height: 100%;
  background-color: #304156;
  transition: width 0.3s;
  overflow-y: auto;
}

.logo-container {
  height: 60px;
  line-height: 60px;
  background: #2b3649;
  text-align: center;
  overflow: hidden;
}

.logo {
  height: 32px;
  vertical-align: middle;
  margin-right: 12px;
}

.title {
  display: inline-block;
  color: #fff;
  font-size: 16px;
  font-weight: 600;
  vertical-align: middle;
}

.app-header {
  height: 60px;
  line-height: 60px;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.header-left {
  display: flex;
  align-items: center;
}

.header-right {
  display: flex;
  align-items: center;
}

.fold-btn {
  padding: 0 12px;
  cursor: pointer;
  font-size: 20px;
}

.user-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.username {
  margin-left: 10px;
  font-size: 14px;
}

.el-menu-vertical:not(.el-menu--collapse) {
  width: 220px;
}

.el-main {
  background-color: #f0f2f5;
  padding: 20px;
  overflow-y: auto;
}

/* 过渡动画 */
.fade-transform-enter-active,
.fade-transform-leave-active {
  transition: all 0.3s;
}

.fade-transform-enter-from {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>
