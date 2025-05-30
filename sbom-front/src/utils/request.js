import axios from 'axios'
import { ElMessage } from 'element-plus'

const service = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    console.error('请求错误', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 0) {
      ElMessage({
        message: res.message || '服务器错误',
        type: 'error',
        duration: 5000
      })
      return Promise.reject(new Error(res.message || '服务器错误'))
    } else {
      return res
    }
  },
  error => {
    console.error('响应错误', error)
    ElMessage({
      message: error.message || '请求失败',
      type: 'error',
      duration: 5000
    })
    return Promise.reject(error)
  }
)

export default service
