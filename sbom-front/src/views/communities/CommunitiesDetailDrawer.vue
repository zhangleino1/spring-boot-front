<template>
  <div class="communities-detail-drawer" v-loading="loading">
    <div v-if="community" class="community-detail">
      <div class="detail-header">
        <div class="community-title">
          <h2>{{ community.name }}</h2>
          <el-tag :type="getTypeTagType(community.type)" size="large">
            {{ getTypeLabel(community.type) }}
          </el-tag>        </div>
      </div><div class="detail-content">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="社区名称">
            {{ community.name }}
          </el-descriptions-item>
          <el-descriptions-item label="Gitee路径">
            {{ community.path }}
          </el-descriptions-item>
          <el-descriptions-item label="社区类型">
            <el-tag :type="getTypeTagType(community.type)">
              {{ getTypeLabel(community.type) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="描述" :span="2">
            {{ community.description || '暂无描述' }}
          </el-descriptions-item>
          <el-descriptions-item label="创建时间">
            {{ formatDateTime(community.createdAt) }}
          </el-descriptions-item>
          <el-descriptions-item label="更新时间">
            {{ formatDateTime(community.updatedAt) }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </div>
    
    <el-empty v-else-if="!loading" description="未找到社区信息" />
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { UserFilled } from '@element-plus/icons-vue'
import communitiesApi from '@/api/communities'

const props = defineProps({
  communityId: {
    type: Number,
    required: true
  }
})

const loading = ref(false)
const community = ref(null)

// 获取社区类型标签类型
const getTypeTagType = (type) => {
  const typeMap = {
    'org': 'success',
    'com': 'warning',
    'user': 'info'
  }
  return typeMap[type] || 'info'
}

// 获取社区类型标签文本
const getTypeLabel = (type) => {
  const typeMap = {
    'org': '组织/Group',
    'com': '公司/其他组织',
    'user': '个人/User'
  }
  return typeMap[type] || type
}

// 格式化日期时间
const formatDateTime = (dateTime) => {
  if (!dateTime) return '-'
  return new Date(dateTime).toLocaleString('zh-CN')
}

// 获取社区详情
const fetchCommunityDetail = async () => {
  if (!props.communityId) return
  
  loading.value = true
  try {
    const { data } = await communitiesApi.getCommunityDetail(props.communityId)
    community.value = data
  } catch (error) {
    console.error('获取社区详情失败', error)
    ElMessage.error('获取社区详情失败')
  } finally {
    loading.value = false
  }
}

// 监听社区ID变化
watch(
  () => props.communityId,
  () => {
    fetchCommunityDetail()
  },
  { immediate: true }
)

onMounted(() => {
  fetchCommunityDetail()
})
</script>

<style scoped>
.communities-detail-drawer {
  padding: 20px;
}

.detail-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 30px;
  padding-bottom: 20px;
  border-bottom: 1px solid #e4e7ed;
}

.community-title {
  display: flex;
  align-items: center;
  gap: 12px;
}

.community-title h2 {
  margin: 0;
  font-size: 24px;
  font-weight: 600;
  color: #303133;
}

.detail-content > div {
  margin-bottom: 30px;
}

:deep(.el-descriptions__label) {
  font-weight: 500;
}
</style>
