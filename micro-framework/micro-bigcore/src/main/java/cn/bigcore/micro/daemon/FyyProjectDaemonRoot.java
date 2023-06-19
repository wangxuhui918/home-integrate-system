package cn.bigcore.micro.daemon;

import lombok.Data;

import java.util.HashMap;

/**
 * 主配置位置
 */
@Data
public class FyyProjectDaemonRoot {
    /**
     *
     */
    private String develop_user_id = "";

    private HashMap<String, FyyProjectConfigRoot> project_config = new HashMap<>();


}
