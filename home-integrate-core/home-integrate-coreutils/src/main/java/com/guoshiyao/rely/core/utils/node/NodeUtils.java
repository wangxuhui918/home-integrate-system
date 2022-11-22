

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils.node;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeUtils {

    /**
     * 获取对应国际编码的消息码内容
     *
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     * @param readString
     * @param i18nCode
     * @param maps
     */
    public static void getI18nMessageContext(String readString, String i18nCode,
                                             HashMap<String, HashMap<String, List<String>>> maps) {
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
                        maps.get(i18nCode).putAll(NodeUtils.getCodeContext(child));
                    }
                }
            } else if (node.getNodeName().equals("member")) {
                maps.get(i18nCode).putAll(NodeUtils.getCodeContext(node));
            }
        }
    }

    /**
     * 第一列存放内容,第二列存放类型
     *
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     * @param node
     * @return
     */
    private static HashMap<String, List<String>> getCodeContext(Node node) {
        HashMap<String, List<String>> codeMess = new HashMap<>();
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
            List<String> listCon = new ArrayList<>();
            listCon.add(conmtext);
            listCon.add(type);
            codeMess.put(code, listCon);
        }
        return codeMess;
    }
}
