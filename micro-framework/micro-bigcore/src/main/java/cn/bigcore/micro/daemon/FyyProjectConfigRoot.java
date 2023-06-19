package cn.bigcore.micro.daemon;

import lombok.Data;

/**
 * 主配置位置
 */
@Data
public class FyyProjectConfigRoot {
    private String project_name_en = "";
    private String project_name_cn = "";
    private String prject_application_properties_path = "";

}
