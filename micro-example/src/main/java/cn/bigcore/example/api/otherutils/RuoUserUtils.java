/**
 * 医惠科技版权所有
 */
package cn.bigcore.example.api.otherutils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RuoUserUtils {
    public static String userServerUrl = "http://localhost:8081/getuserinfo?sessionId=";

    public static String getUserName() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        String loginName = null;
        try {
            for (Cookie cok : req.getCookies()) {
                if (StrUtil.equals(cok.getName(), "JSESSIONID")) {
                    String sessionId = cok.getValue();
                    String userString = HttpUtil.get(userServerUrl + sessionId);
                    loginName = JSONUtil.parseObj(userString).getByPath("loginName").toString();
                }
            }
        } catch (Exception e) {
            loginName = null;
        }
        if (StrUtil.isBlank(loginName)) {
            //	throw new GogException(new CodeRe("BUS01"));
        }

        return loginName;
    }

}
