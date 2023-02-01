package cn.bigcore.micro.plugin.xml2bean.demobean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BeanDetailThrid implements Serializable {

    @JSONField(name = "DETAIL_X")
    private List<BeanDetailOne> detail;

    @JSONField(name = "VL")
    private String vlx;

}
