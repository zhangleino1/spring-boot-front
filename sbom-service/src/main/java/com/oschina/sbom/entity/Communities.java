package com.oschina.sbom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 社区数据表
 * </p>
 *
 * @author zzzhanglei
 * @since 2025-05-30
 */

@TableName("communities")
@Data
public class Communities implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private Date createdAt;

    private Date updatedAt;
}
