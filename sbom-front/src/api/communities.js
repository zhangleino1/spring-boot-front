import request from '@/utils/request'

export default {
  /**
   * 分页查询社区数据
   * @param {Object} params 查询参数
   * @returns {Promise<Object>}
   */
  getCommunitiesPage(params) {
    return request({
      url: '/communities/page',
      method: 'get',
      params
    })
  },

  /**
   * 根据社区类型查询社区数据
   * @param {String} type 社区类型
   * @returns {Promise<Object>}
   */
  getCommunitiesByType(type) {
    return request({
      url: `/communities/type/${type}`,
      method: 'get'
    })
  },

  /**
   * 获取社区详情
   * @param {Number} id 社区ID
   * @returns {Promise<Object>}
   */
  getCommunityDetail(id) {
    return request({
      url: `/communities/${id}`,
      method: 'get'
    })
  },

  /**
   * 创建社区
   * @param {Object} data 社区数据
   * @returns {Promise<Object>}
   */
  createCommunity(data) {
    return request({
      url: '/communities',
      method: 'post',
      data
    })
  },

  /**
   * 更新社区
   * @param {Number} id 社区ID
   * @param {Object} data 社区数据
   * @returns {Promise<Object>}
   */
  updateCommunity(id, data) {
    return request({
      url: `/communities/${id}`,
      method: 'put',
      data
    })
  },

  /**
   * 删除社区
   * @param {Number} id 社区ID
   * @returns {Promise<Object>}
   */
  deleteCommunity(id) {
    return request({
      url: `/communities/${id}`,
      method: 'delete'
    })
  }
}
