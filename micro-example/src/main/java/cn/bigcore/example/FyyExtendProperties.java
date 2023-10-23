package cn.bigcore.example;

import cn.bigcore.micro.config.project.FyyConfigPropertiesInterface;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.base.frame.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.base.frame.FyyConfigEntryVo;

import java.util.Arrays;
import java.util.List;
@FyyRuleInjection
public class FyyExtendProperties implements FyyConfigPropertiesInterface {
    @Override
    public FyyConfigEntryVo getFyyConfigEntry() {
        return new FyyConfigEntryVo("extends.ini", "");
    }

    @Override
    public List<FyyConfigEntryDetailsVo> getFyyConfigEntryDetails() {
        return Arrays.asList(new FyyConfigEntryDetailsVo[]{new FyyConfigEntryDetailsVo("user.name", "", "=", "wangxuhui", "# 姓名", "备注")});
    }
}
