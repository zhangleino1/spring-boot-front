<template>
  <div class="communities-list-container page-container">
    <div class="search-container">
      <el-form :inline="true" :model="searchForm" class="search-form">        <el-form-item label="社区名称">
          <el-input v-model="searchForm.name" placeholder="请输入社区名称" clearable />
        </el-form-item>
        <el-form-item label="社区类型">
          <el-select v-model="searchForm.type" placeholder="请选择社区类型" clearable style="width: 200px">
            <el-option label="组织/Group" value="org" />
            <el-option label="公司/其他组织" value="com" />
            <el-option label="个人/User" value="user" />
          </el-select>
        </el-form-item>
        <el-form-item label="路径">
          <el-input v-model="searchForm.path" placeholder="请输入Gitee路径" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">
            <el-icon><Search /></el-icon>搜索
          </el-button>
          <el-button @click="resetSearch">
            <el-icon><RefreshRight /></el-icon>重置
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card shadow="never" class="table-card">
      <template #header>
        <div class="card-header">
          <span class="header-title">社区列表</span>
          <el-button type="primary" @click="handleCreate">
            <el-icon><Plus /></el-icon>创建社区
          </el-button>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%"
        border
      >        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="社区名称" min-width="150" />
        <el-table-column prop="path" label="Gitee路径" min-width="150" />
        <el-table-column prop="type" label="社区类型" width="120">
          <template #default="{ row }">
            <el-tag :type="getTypeTagType(row.type)">
              {{ getTypeLabel(row.type) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="description" label="描述" min-width="200" show-overflow-tooltip />
        <el-table-column prop="createdAt" label="创建时间" width="180" />
        <el-table-column prop="updatedAt" label="更新时间" width="180" />
        <el-table-column label="操作" width="230" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" @click="handleView(row)">
              <el-icon><View /></el-icon>详情
            </el-button>
            <el-button link type="primary" @click="handleEdit(row)">
              <el-icon><Edit /></el-icon>编辑
            </el-button>
            <el-popconfirm
              title="确定要删除此社区吗？"
              @confirm="handleDelete(row.id)"
            >
              <template #reference>
                <el-button link type="danger">
                  <el-icon><Delete /></el-icon>删除
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>

    <!-- 社区表单抽屉 -->
    <el-drawer
      v-model="drawerVisible"
      :title="drawerTitle"
      :size="600"
      :before-close="handleDrawerClose"
      direction="rtl"
    >
      <CommunitiesFormDrawer
        v-if="drawerVisible"
        :community-id="editCommunityId"
        :is-edit="isEdit"
        @success="handleFormSuccess"
        @cancel="handleFormCancel"
      />
    </el-drawer>

    <!-- 社区详情抽屉 -->
    <el-drawer
      v-model="detailDrawerVisible"
      title="社区详情"
      :size="800"
      direction="rtl"
    >
      <CommunitiesDetailDrawer
        v-if="detailDrawerVisible"
        :community-id="viewCommunityId"
      />
    </el-drawer>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, RefreshRight, Plus, View, Edit, Delete } from '@element-plus/icons-vue'
import communitiesApi from '@/api/communities'
import CommunitiesFormDrawer from './CommunitiesFormDrawer.vue'
import CommunitiesDetailDrawer from './CommunitiesDetailDrawer.vue'

const router = useRouter()
const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)

// 抽屉相关状态
const drawerVisible = ref(false)
const editCommunityId = ref(null)
const isEdit = ref(false)
const drawerTitle = ref('创建社区')

// 详情抽屉相关状态
const detailDrawerVisible = ref(false)
const viewCommunityId = ref(null)

// 搜索表单
const searchForm = reactive({
  name: '',
  type: '',
  path: ''
})

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

// 获取社区列表
const fetchCommunitiesList = async () => {
  loading.value = true
  try {
    const params = {
      current: currentPage.value,
      size: pageSize.value,
      ...searchForm
    }
    
    const { data } = await communitiesApi.getCommunitiesPage(params)
    
    if (data) {
      tableData.value = data.records || []
      total.value = data.total || 0
    }
  } catch (error) {
    console.error('获取社区列表失败', error)
    ElMessage.error('获取社区列表失败')
  } finally {
    loading.value = false
  }
}

// 搜索
const handleSearch = () => {
  currentPage.value = 1
  fetchCommunitiesList()
}

// 重置搜索
const resetSearch = () => {
  Object.keys(searchForm).forEach(key => {
    searchForm[key] = ''
  })
  currentPage.value = 1
  fetchCommunitiesList()
}

// 页码变化
const handleCurrentChange = (val) => {
  currentPage.value = val
  fetchCommunitiesList()
}

// 每页条数变化
const handleSizeChange = (val) => {
  pageSize.value = val
  currentPage.value = 1
  fetchCommunitiesList()
}

// 查看详情
const handleView = (row) => {
  viewCommunityId.value = row.id
  detailDrawerVisible.value = true
}

// 创建社区
const handleCreate = () => {
  isEdit.value = false
  editCommunityId.value = null
  drawerTitle.value = '创建社区'
  drawerVisible.value = true
}

// 编辑社区
const handleEdit = (row) => {
  isEdit.value = true
  editCommunityId.value = row.id
  drawerTitle.value = '编辑社区'
  drawerVisible.value = true
}

// 处理抽屉关闭
const handleDrawerClose = (done) => {
  ElMessageBox.confirm('确认关闭？未保存的数据将会丢失')
    .then(() => {
      done()
    })
    .catch(() => {
      // 取消关闭
    })
}

// 表单提交成功
const handleFormSuccess = () => {
  drawerVisible.value = false
  fetchCommunitiesList() // 重新加载列表数据
}

// 表单取消
const handleFormCancel = () => {
  drawerVisible.value = false
}

// 删除社区
const handleDelete = async (id) => {
  try {
    const { data } = await communitiesApi.deleteCommunity(id)
    if (data) {
      ElMessage({
        type: 'success',
        message: '删除成功'
      })
      
      // 重新加载数据
      fetchCommunitiesList()
    }
  } catch (error) {
    console.error('删除社区失败', error)
    ElMessage.error('删除社区失败')
  }
}

onMounted(() => {
  fetchCommunitiesList()
})
</script>

<style scoped>
.communities-list-container {
  padding: 20px;
}

.search-container {
  background-color: #fff;
  padding: 20px;
  border-radius: 4px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-title {
  font-size: 18px;
  font-weight: 500;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
