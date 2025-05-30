package com.oschina.sbom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oschina.sbom.dto.CommunitiesDTO;
import com.oschina.sbom.entity.Communities;
import com.oschina.sbom.vo.CommunitiesVO;

import java.util.List;

/**
 * <p>
 * 社区数据表 服务类
 * </p>
 *
 * @author zzzhanglei
 * @since 2025-05-30
 */
public interface ICommunitiesService extends IService<Communities> {

    /**
     * 分页查询社区数据
     *
     * @param page           分页参数
     * @param communitiesDTO 查询条件
     * @return 分页结果
     */
    IPage<CommunitiesVO> pageList(Page<Communities> page, CommunitiesDTO communitiesDTO);

    /**
     * 根据社区类型查询社区数据
     *
     * @param type 社区类型
     * @return 社区列表
     */
    List<CommunitiesVO> listByType(String type);

    /**
     * 创建社区数据
     *
     * @param communitiesDTO 社区信息
     * @return 是否成功
     */
    boolean createCommunity(CommunitiesDTO communitiesDTO);

    /**
     * 更新社区数据
     *
     * @param id             社区ID
     * @param communitiesDTO 社区信息
     * @return 是否成功
     */
    boolean updateCommunity(Integer id, CommunitiesDTO communitiesDTO);

    /**
     * 删除社区数据
     *
     * @param id 社区ID
     * @return 是否成功
     */
    boolean deleteCommunity(Integer id);

    /**
     * 根据ID查询社区详情
     *
     * @param id 社区ID
     * @return 社区详情
     */
    CommunitiesVO getCommunityById(Integer id);
}
