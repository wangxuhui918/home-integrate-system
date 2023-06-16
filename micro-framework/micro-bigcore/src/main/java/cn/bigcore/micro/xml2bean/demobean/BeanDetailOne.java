package cn.bigcore.micro.xml2bean.demobean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class BeanDetailOne implements Serializable {

    private String amount = "1";

    private String price = "";

    @JSONField(name = "TEST_ITEM_CODE")  // 检验子项目代码
    private String code;

    @JSONField(name = "TEST_ITEM_NAME")  // 检验子项目名称
    private String name;

    @JSONField(name = "TEST_CHARGE_ITEM_CODE")  // 检验子项目收费代码
    private String feeItemCode;

}
