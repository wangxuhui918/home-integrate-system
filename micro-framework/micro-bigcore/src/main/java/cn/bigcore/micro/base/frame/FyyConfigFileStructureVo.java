/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.base.frame;

import cn.bigcore.micro.base.frame.impl.FyyConfigResourceType;
import lombok.Data;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme 配置文件结构
 */
@Data
public class FyyConfigFileStructureVo {
    /**
     * 路径
     */
    private String path;
    /**
     * 文件结构类型
     */
    private FyyConfigResourceType resourceType;
    /**
     * 文件内容
     */
    private String context;


}
