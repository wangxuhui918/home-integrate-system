package cn.bigcore.micro;

import cn.hutool.setting.Setting;

import java.util.HashMap;

public class FyyProperties {
    public static final Setting setting = new Setting("fyy.properties");

    static {
        try {
            Setting fyyextends = new Setting("fyy-extends.properties");
            setting.addSetting(fyyextends);
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        String string = "";
        System.out.println("string = " + FyyProperties.setting.getStr("fyy.project.core.i18n"));
    }

}
