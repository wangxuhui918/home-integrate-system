package cn.bigcore.micro;

import cn.hutool.setting.Setting;
import cn.hutool.system.SystemUtil;

public class FyyProperties {
    public static final Setting setting = new Setting("fyy.properties");

    public static void main() {

    }

    static {
        try {
            Setting fyyextends = new Setting("fyy-extends.properties");
            setting.addSetting(fyyextends);
        } catch (Exception e) {
        }
        try {
            for (String key : setting.keySet()) {
                if (SystemUtil.get(key) != null) {
                    setting.put(key, SystemUtil.get(key));
                }
            }
        } catch (Exception e) {
        }
    }

}
