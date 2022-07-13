

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.tools.system;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * 网络工具
 */
public class NetUtils {

    /**
     * 获取本机   MAC 地址
     */
    public static Set<String> getMacNet() {
        try {
            Set<String> ips = new HashSet<>();
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                StringBuffer stringBuffer = new StringBuffer();
                NetworkInterface networkInterface = enumeration.nextElement();
                if (networkInterface != null) {
                    byte[] bytes = networkInterface.getHardwareAddress();
                    if (bytes != null) {
                        for (int i = 0; i < bytes.length; i++) {
                            if (i != 0) {
                            }
                            int tmp = bytes[i] & 0xff; // 字节转换为整数
                            String str = Integer.toHexString(tmp);
                            if (str.length() == 1) {
                                stringBuffer.append("0" + str);
                            } else {
                                stringBuffer.append(str);
                            }
                        }
                        String mac = networkInterface.getName().toUpperCase() + "-"
                                + stringBuffer.toString().toUpperCase();
                        if (mac.contains("0000")) {
                        } else {
                            ips.add(mac);
                        }
                    }
                }
            }
            return ips;
        } catch (Exception e) {
        }
        return new HashSet<>();
    }
}