package cn.bigcore.micro.plugin.xml2bean;


import cn.bigcore.micro.plugin.xml2bean.demobean.BeanDemo;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class XmlBeanConvertUtils {


    public static void testmain(String[] args) {
        String xmlContext = ResourceUtil.readUtf8Str("xmldemo.xml");
        List<BeanDemo> beandemo = EsbXmlToBean(xmlContext, BeanDemo.class);
        System.out.println(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(beandemo)));
        String xmlStr = EsbBeanToXml(beandemo.toArray());
        System.out.println(xmlStr);
        //样例包含
        //1. BeanDemo 复杂表单包含 BeanDetailTwo+BeanDetailThrid
        //1. BeanDetailTwo 一个简单对象
        //1. BeanDetailThrid 一个复杂对象(嵌套List+字段)
        //原理,通过fastjson+@JSONField 注解进行 bean<-->json转换

    }

    /**
     * //XML 转 JavaBean
     *
     * @param xmlContext
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> EsbXmlToBean(String xmlContext, Class<T> t) {
        String tag = "Msg";
        JSONObject json = JSONUtil.xmlToJson(xmlContext).getJSONObject("ESBEntry").getJSONObject("MsgInfo");
        String msgContext = json.getStr(tag);
        boolean isArray = JSONUtil.isJsonArray(msgContext);
        if (!isArray) {//非数组
            //XML 转 JavaBean
            List<T> list = new ArrayList<>();
            T bean = JSON.parseObject(msgContext, t);
            list.add(bean);
            return list;
        } else {//数组
            List<T> beanList = JSON.parseArray(msgContext, t);
            return beanList;
        }
    }

    /**
     * JavaBean 转 XML
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> String EsbBeanToXml(T... bean) {
        String tag = "Msg";
        String xmlStr1 = XML.toXml(JSONUtil.parse(JSON.toJSON(bean)), tag);
        return xmlStr1;
    }
}
