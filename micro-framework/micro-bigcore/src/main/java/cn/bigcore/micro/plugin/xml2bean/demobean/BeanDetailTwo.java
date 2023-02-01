package cn.bigcore.micro.plugin.xml2bean.demobean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class BeanDetailTwo implements Serializable {

    @JSONField(name = "BAR_CODE")  // 血液条码号
    private String barCode;

    @JSONField(name = "BLOOD_ITEM_TYPE_CODE")  // 输血项目类型代码
    private String bloodItemTypeCode;

    @JSONField(name = "BLOOD_ITEM_TYPE_NAME")  // 输血项目类型名称
    private String bloodItemTypeName;

    @JSONField(name = "APPLY_ABO_TYPE_CODE")  // 输血ABO血型代码
    private String applyAboTypeCode;

    @JSONField(name = "APPLY_ABO_TYPE_NAME")  // 输血ABO血型名称
    private String applyAboTypeName;

    @JSONField(name = "APPLY_RH_TYPE_CODE")  // 输血RH血型代码
    private String applyRhTypeCode;

    @JSONField(name = "APPLY_RH_TYPE_NAME")  // 输血RH血型名称
    private String applyRhTypeName;

    @JSONField(name = "BLOOD_AMOUNT")  // 输血数量
    private String bloodAmount;

    @JSONField(name = "BLOOD_UNIT")  // 输血单位
    private String bloodUnit;

    @JSONField(name = "BLOOD_ITEM_CODE")  //输血项目代码
    private String bloodItemCode;

    @JSONField(name = "BLOOD_ITEM_NAME")  //输血项目名称
    private String bloodItemName;

}
