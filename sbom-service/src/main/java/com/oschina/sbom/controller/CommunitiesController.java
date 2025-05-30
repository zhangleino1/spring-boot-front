package com.oschina.sbom.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oschina.sbom.common.result.R;
import com.oschina.sbom.dto.CommunitiesDTO;
import com.oschina.sbom.entity.Communities;
import com.oschina.sbom.service.ICommunitiesService;
import com.oschina.sbom.vo.CommunitiesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 社区数据表 前端控制器
 * </p>
 *
 * @author zzzhanglei
 * @since 2025-05-30
 */
@RestController
@RequestMapping("/api/communities")
@Slf4j
public class CommunitiesController extends BaseController {

    @Autowired
    private ICommunitiesService communitiesService;

    /**
     * 分页查询社区数据
     * 
     * @param current 当前页
     * @param size 每页大小
     * @param communitiesDTO 查询条件
     * @return 分页结果
     */
    @GetMapping("/page")
    public R<IPage<CommunitiesVO>> page(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            CommunitiesDTO communitiesDTO) {
        log.info("分页查询社区数据, current: {}, size: {}, params: {}", current, size, communitiesDTO);
        Page<Communities> page = new Page<>(current, size);
        IPage<CommunitiesVO> result = communitiesService.pageList(page, communitiesDTO);
        return success(result, "查询成功");
    }

    /**
     * 根据社区类型查询社区数据
     * 
     * @param type 社区类型
     * @return 社区列表
     */
    @GetMapping("/type/{type}")
    public R<List<CommunitiesVO>> listByType(@PathVariable String type) {
        log.info("根据社区类型查询社区数据, type: {}", type);
        List<CommunitiesVO> list = communitiesService.listByType(type);
        return success(list, "查询成功");
    }

    /**
     * 创建社区数据
     * 
     * @param communitiesDTO 社区信息
     * @return 操作结果
     */
    @PostMapping
    public R<Boolean> create(@RequestBody CommunitiesDTO communitiesDTO) {
        log.info("创建社区数据, params: {}", communitiesDTO);
        boolean result = communitiesService.createCommunity(communitiesDTO);
        if (result) {
            return success(true, "创建成功");
        } else {
            return fail("创建失败");
        }
    }

    /**
     * 更新社区数据
     * 
     * @param id 社区ID
     * @param communitiesDTO 社区信息
     * @return 操作结果
     */
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Integer id, @RequestBody CommunitiesDTO communitiesDTO) {
        log.info("更新社区数据, id: {}, params: {}", id, communitiesDTO);
        boolean result = communitiesService.updateCommunity(id, communitiesDTO);
        if (result) {
            return success(true, "更新成功");
        } else {
            return fail("更新失败，社区不存在或操作异常");
        }
    }

    /**
     * 删除社区数据
     * 
     * @param id 社区ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        log.info("删除社区数据, id: {}", id);
        boolean result = communitiesService.deleteCommunity(id);
        if (result) {
            return success(true, "删除成功");
        } else {
            return fail("删除失败，社区不存在或操作异常");
        }
    }

    /**
     * 获取社区详情
     * 
     * @param id 社区ID
     * @return 社区详情
     */
    @GetMapping("/{id}")
    public R<CommunitiesVO> detail(@PathVariable Integer id) {
        log.info("查询社区详情, id: {}", id);
        CommunitiesVO community = communitiesService.getCommunityById(id);
        if (community != null) {
            return success(community, "查询成功");
        } else {
            return fail("查询失败，社区不存在");
        }
    }
}
