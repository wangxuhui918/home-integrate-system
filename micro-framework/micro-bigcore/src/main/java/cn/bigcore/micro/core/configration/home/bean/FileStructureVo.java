/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.home.bean;

import lombok.Data;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme 项目结构描述配置
 */
@Data
public class FileStructureVo {
    /**
     * 路径
     */
    private String path;
    /**
     * 文件结构类型
     */
    private ResourceType resourceType;
    /**
     * 文件内容
     */
    private String context;


}
