

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro;

import cn.bigcore.micro.exception.FyyCodeInterface;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.bigcore.micro.outgoing.impl.FyyInputParamRe;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;

import java.util.HashMap;

/**
 * 出参基础类
 *
 * @param <T>
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public class FyyHttpUtils {

    public static JSONObject postException(String url, Object... params) {
        if (params == null || params.length % 2 != 0) {
            throw new FyyExceptionError("参数必须是2的倍数奇数列为key,偶数列为value");
        }
        FyyInputParamRe<HashMap<Object, Object>> inputre = new FyyInputParamRe<>();
        inputre.setInputData(new HashMap<Object, Object>());
        for (int i = 0; i < params.length; i += 2) {
            inputre.getInputData().put(params[i], params[i + 1]);
        }
        String json = HttpUtil.post(url, JSONUtil.toJsonStr(inputre));
        return getDataException(json);
    }

    public static JSONObject postException(String url, HashMap<Object, Object> param) {
        FyyInputParamRe<HashMap<Object, Object>> inputre = new FyyInputParamRe<>();
        inputre.setInputData(new HashMap<Object, Object>());
        inputre.getInputData().putAll(param);
        String json = HttpUtil.post(url, JSONUtil.toJsonStr(inputre));
        return getDataException(json);
    }

    public static JSONObject getDataException(String json) {
        FyyCodeInterface coded = getCode(json);
        if (coded.getType().equals("false")) {
            throw new FyyExceptionError(coded);
        }
        return getData(json);
    }

    public static JSONObject getData(String json) {
        return JSONUtil.parseObj(JSONUtil.parseObj(json).get("data"));
    }

    public static FyyCodeInterface getCode(String json) {
        if (JSONUtil.parseObj(json).containsKey("codeBody")) {
            JSONObject code = JSONUtil.parseObj(JSONUtil.parseObj(json).get("codeBody"));
            String className = code.getByPath("className").toString();
            try {
                if (StrUtil.isNotBlank(className)) {
                    FyyCodeInterface coded = (FyyCodeInterface) ClassUtil.loadClass(className,false).newInstance();
                    BeanUtil.copyProperties(code, coded);
                    return coded;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
