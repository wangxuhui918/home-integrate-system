

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.message.utils;

import cn.bigcore.micro.exception.code.bean.FyyMessageCode;
import cn.bigcore.micro.exception.code.bean.FyyMessageEnum;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class FyyNodeUtils {

    /**
     * 获取对应国际编码的消息码内容
     *
     * @param readString
     * @param i18nCode
     * @param maps
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     */
    public static void getI18nMessageContext(String readString, String i18nCode,
                                             HashMap<String, HashMap<String, FyyMessageCode>> maps) {
        maps.put(i18nCode, new HashMap<>());

        Node root = XmlUtil.parseXml(readString).getElementsByTagName("message").item(0);
        NodeList groups = root.getChildNodes();
        for (int i = 0; i < groups.getLength(); i++) {
            Node node = groups.item(i);
            if (node.getNodeName().equals("group")) {
                NodeList groupChirds = node.getChildNodes();
                for (int j = 0; j < groupChirds.getLength(); j++) {
                    Node child = groupChirds.item(j);
                    if (child.getNodeName().equals("member")) {
                        maps.get(i18nCode).putAll(FyyNodeUtils.getCodeContext(child));
                    }
                }
            } else if (node.getNodeName().equals("member")) {
                maps.get(i18nCode).putAll(FyyNodeUtils.getCodeContext(node));
            }
        }
    }

    /**
     * 第一列存放内容,第二列存放类型
     *
     * @param node
     * @return
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     */
    private static HashMap<String, FyyMessageCode> getCodeContext(Node node) {
        HashMap<String, FyyMessageCode> codeMess = new HashMap<>();
        if (node.getNodeName().equals("member")) {
            String code = "";
            String conmtext = "";
            String type = "";
            try {
                code = node.getAttributes().getNamedItem("code").getNodeValue();
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                conmtext = node.getAttributes().getNamedItem("conmtext").getNodeValue();
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                type = node.getAttributes().getNamedItem("type").getNodeValue();
            } catch (Exception e) {
                // TODO: handle exception
            }
            FyyMessageCode messageCodeVo = new FyyMessageCode();
            messageCodeVo.setContext(conmtext);
            messageCodeVo.setStateType(FyyMessageEnum.getByBooleanStrMark(type).getMessageTypeVo());
            messageCodeVo.setStateCode(code);
            codeMess.put(code, messageCodeVo);
        }
        return codeMess;
    }
}
