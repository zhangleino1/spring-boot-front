-- ---------------------------------
-- Table structure for communities
-- ---------------------------------
CREATE TABLE `communities` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `name` VARCHAR(255) NOT NULL UNIQUE COMMENT '社区名称, 唯一',
                               `path` VARCHAR(255) NOT NULL UNIQUE COMMENT 'Gitee路径 (组织路径或用户路径)',
                               `type` ENUM('org', 'com', 'user') NOT NULL COMMENT '社区类型: org (组织/Group), com (公司/其他类型组织), user (个人/User)',
                               `description` varchar(1024) COMMENT '社区描述',
                               `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社区数据表';



