package com.oschina.sbom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oschina.sbom.dto.CommunitiesDTO;
import com.oschina.sbom.entity.Communities;
import com.oschina.sbom.mapper.CommunitiesMapper;
import com.oschina.sbom.service.ICommunitiesService;
import com.oschina.sbom.vo.CommunitiesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 社区数据表 服务实现类
 * </p>
 *
 * @author zzzhanglei
 * @since 2025-05-30
 */
@Slf4j
@Service
public class CommunitiesServiceImpl extends ServiceImpl<CommunitiesMapper, Communities> implements ICommunitiesService {

    @Override
    public IPage<CommunitiesVO> pageList(Page<Communities> page, CommunitiesDTO communitiesDTO) {
        log.info("分页查询社区数据, 参数: {}", communitiesDTO);
        
        LambdaQueryWrapper<Communities> queryWrapper = new LambdaQueryWrapper<>();
        
        // 构建查询条件
        if (communitiesDTO != null) {
            if (StringUtils.hasText(communitiesDTO.getName())) {
                queryWrapper.like(Communities::getName, communitiesDTO.getName());
            }
            if (StringUtils.hasText(communitiesDTO.getPath())) {
                queryWrapper.like(Communities::getPath, communitiesDTO.getPath());
            }
            if (StringUtils.hasText(communitiesDTO.getType())) {
                queryWrapper.eq(Communities::getType, communitiesDTO.getType());
            }
            if (StringUtils.hasText(communitiesDTO.getDescription())) {
                queryWrapper.like(Communities::getDescription, communitiesDTO.getDescription());
            }
        }
        
        // 添加排序
        queryWrapper.orderByDesc(Communities::getCreatedAt);
        
        // 查询数据
        IPage<Communities> result = this.page(page, queryWrapper);
        
        // 转换为VO
        IPage<CommunitiesVO> voPage = result.convert(community -> {
            CommunitiesVO vo = new CommunitiesVO();
            BeanUtils.copyProperties(community, vo);
            return vo;
        });
        
        return voPage;
    }

    @Override
    public List<CommunitiesVO> listByType(String type) {
        log.info("根据社区类型查询社区数据, 类型: {}", type);
        
        LambdaQueryWrapper<Communities> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Communities::getType, type);
        queryWrapper.orderByDesc(Communities::getCreatedAt);
        
        List<Communities> list = this.list(queryWrapper);
        
        return list.stream().map(community -> {
            CommunitiesVO vo = new CommunitiesVO();
            BeanUtils.copyProperties(community, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createCommunity(CommunitiesDTO communitiesDTO) {
        log.info("创建社区数据, 参数: {}", communitiesDTO);
        
        Communities community = new Communities();
        BeanUtils.copyProperties(communitiesDTO, community);
        
        // 设置默认值
        community.setCreatedAt(new Date());
        community.setUpdatedAt(new Date());
        
        return this.save(community);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCommunity(Integer id, CommunitiesDTO communitiesDTO) {
        log.info("更新社区数据, ID: {}, 参数: {}", id, communitiesDTO);
        
        Communities community = this.getById(id);
        if (community == null) {
            log.warn("更新社区数据失败, 未找到ID为{}的社区", id);
            return false;
        }
        
        BeanUtils.copyProperties(communitiesDTO, community);
        community.setId(id); // 确保ID不被覆盖
        community.setUpdatedAt(new Date());
        
        return this.updateById(community);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCommunity(Integer id) {
        log.info("删除社区数据, ID: {}", id);
        
        Communities community = this.getById(id);
        if (community == null) {
            log.warn("删除社区数据失败, 未找到ID为{}的社区", id);
            return false;
        }
        
        return this.removeById(id);
    }

    @Override
    public CommunitiesVO getCommunityById(Integer id) {
        log.info("根据ID查询社区数据, ID: {}", id);
        
        Communities community = this.getById(id);
        if (community == null) {
            log.warn("查询社区数据失败, 未找到ID为{}的社区", id);
            return null;
        }
        
        CommunitiesVO vo = new CommunitiesVO();
        BeanUtils.copyProperties(community, vo);
        
        return vo;
    }
}
