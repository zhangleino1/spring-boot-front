package com.oschina.sbom.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Communities DTO
 */
@Data
public class CommunitiesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 社区名称, 唯一
     */
    private String name;

    /**
     * Gitee路径 (组织路径或用户路径)
     */
    private String path;

    /**
     * 社区类型: org (组织/Group), com (公司/其他类型组织), user (个人/User)
     */
    private String type;

    /**
     * 社区描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
