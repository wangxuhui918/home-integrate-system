package cn.bigcore.example;

import cn.bigcore.micro.config.FyyConfigProjectPropertiesImpl;
import cn.bigcore.micro.config.FyyConfigPropertiesInterface;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryVo;

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
