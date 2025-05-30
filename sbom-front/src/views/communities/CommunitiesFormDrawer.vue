<template>
  <div class="communities-form-drawer">
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="100px"
      v-loading="loading"
    >      <el-form-item label="社区名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入社区名称" />
      </el-form-item>

      <el-form-item label="Gitee路径" prop="path">
        <el-input v-model="form.path" placeholder="请输入Gitee路径（组织路径或用户路径）" />
      </el-form-item>

      <el-form-item label="社区类型" prop="type">
        <el-select v-model="form.type" placeholder="请选择社区类型" style="width: 100%">
          <el-option label="组织/Group" value="org" />
          <el-option label="公司/其他组织" value="com" />
          <el-option label="个人/User" value="user" />
        </el-select>
      </el-form-item>

      <el-form-item label="社区描述" prop="description">
        <el-input
          v-model="form.description"
          type="textarea"
          :rows="4"
          placeholder="请输入社区描述"
        />
      </el-form-item>
    </el-form>

    <div class="drawer-footer">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="submitting">
        {{ isEdit ? '更新' : '创建' }}
      </el-button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, watch, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import communitiesApi from '@/api/communities'

const props = defineProps({
  communityId: {
    type: Number,
    default: null
  },
  isEdit: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['success', 'cancel'])

const formRef = ref(null)
const loading = ref(false)
const submitting = ref(false)

// 表单数据
const form = reactive({
  name: '',
  path: '',
  type: '',
  description: ''
})

// 表单验证规则
const rules = reactive({
  name: [
    { required: true, message: '请输入社区名称', trigger: 'blur' },
    { min: 2, max: 100, message: '社区名称长度在 2 到 100 个字符', trigger: 'blur' }
  ],
  path: [
    { required: true, message: '请输入Gitee路径', trigger: 'blur' },
    { min: 2, max: 100, message: 'Gitee路径长度在 2 到 100 个字符', trigger: 'blur' }
  ],
  type: [
    { required: true, message: '请选择社区类型', trigger: 'change' }
  ],
  description: [
    { max: 1000, message: '社区描述最多 1000 个字符', trigger: 'blur' }
  ]
})

// 重置表单
const resetForm = () => {
  Object.keys(form).forEach(key => {
    form[key] = ''
  })
  
  nextTick(() => {
    formRef.value?.clearValidate()
  })
}

// 获取社区详情
const fetchCommunityDetail = async () => {
  if (!props.communityId) return
    loading.value = true
  try {
    const { data } = await communitiesApi.getCommunityDetail(props.communityId)
    if (data) {
      Object.keys(form).forEach(key => {
        if (data.hasOwnProperty(key)) {
          form[key] = data[key] || ''
        }
      })
    }
  } catch (error){
    console.error('获取社区详情失败', error)
    ElMessage.error('获取社区详情失败')
  } finally {
    loading.value = false
  }
}

// 提交表单
const handleSubmit = async () => {
  if (!formRef.value) return
  
  try {
    const valid = await formRef.value.validate()
    if (!valid) return
      submitting.value = true
    
    // 处理表单数据
    const submitData = { ...form }
    
    if (props.isEdit) {
      await communitiesApi.updateCommunity(props.communityId, submitData)
      ElMessage.success('更新社区成功')
    } else {
      await communitiesApi.createCommunity(submitData)
      ElMessage.success('创建社区成功')
    }
    
    emit('success')
  } catch (error) {
    console.error('提交失败', error)
    ElMessage.error(props.isEdit ? '更新社区失败' : '创建社区失败')
  } finally {
    submitting.value = false
  }
}

// 取消操作
const handleCancel = () => {
  emit('cancel')
}

// 监听属性变化
watch(
  () => [props.communityId, props.isEdit],
  () => {
    if (props.isEdit && props.communityId) {
      fetchCommunityDetail()
    } else {
      resetForm()
    }
  },
  { immediate: true }
)
</script>

<style scoped>
.communities-form-drawer {
  padding: 20px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.drawer-footer {
  margin-top: auto;
  padding-top: 20px;
  border-top: 1px solid #e4e7ed;
  text-align: right;
}

.drawer-footer .el-button {
  margin-left: 10px;
}

:deep(.el-form-item__label) {
  font-weight: 500;
}

:deep(.el-textarea__inner) {
  resize: none;
}
</style>
