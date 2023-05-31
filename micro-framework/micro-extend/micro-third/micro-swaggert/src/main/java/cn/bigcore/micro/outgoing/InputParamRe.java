

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.outgoing;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.bigcore.micro.plugin.exception.ExceptionMessageAbstract;
import cn.bigcore.micro.plugin.outgoing.InputParamAb;

import java.util.List;
import java.util.Map;

public class InputParamRe<G> extends InputParamAb<JSONObject> {

    private G inputData;

    public G getInputData() {
        return inputData;
    }

    public void setInputData(G inputData) {
        super.setData(JSONUtil.parseObj(inputData));
        this.inputData = inputData;
    }

    /**
     * 获取到的目标数据为空则抛出异常
     *
     * @param exab
     * @author 汪旭辉
     * @date 2021年12月8日
     * @readme
     */
    public void throwNull(String expression, ExceptionMessageAbstract exab) {
        java.lang.Object object = getData().getByPath(expression);
        if (object == null) {
            throw exab;
        }
    }

    /**
     * 获取到的目标数据为空则抛出异常
     * 空对象(包括null和所有属性为空)，空字符串，空数组，空集合，基本类型为默认值(不判断)
     *
     * @param expression
     * @param exab
     */
    public void throwEmpty(String expression, ExceptionMessageAbstract exab) {
        java.lang.Object object = getData().getByPath(expression);
        if (checkObjectNull(object)) {
            throw exab;
        }
    }

    /**
     * 校验是否空对象
     *
     * @param object
     * @return
     */
    public static boolean checkObjectNull(Object object) {
        boolean empty = false;
        if (object == null) {
            empty = true;
        } else if (object instanceof List) {
            //空集合
            empty = ((List<?>) object).isEmpty();
        } else if (object instanceof String) {
            //空字符串
            empty = ((String) object).trim().equals("");
        } else if (object instanceof JSONObject) {
            //空对象
            empty = checkJSONObjectNull(object);
        }
//        else {
//            //基本类型
//            empty = checkBasicTypeNull(object);
//        }
        return empty;
    }


    /**
     * 校验是否空JSONObject
     *
     * @param object
     * @return
     */
    public static boolean checkJSONObjectNull(Object object) {
        if (((JSONObject) object).isEmpty()) {
            return true;
        }
        //存在一个属性不为空则返回false
        JSONObject jsonObject = (JSONObject) object;
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            Object o = entry.getValue();
            if (!checkObjectNull(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验基本类型是否默认值
     *
     * @param object
     * @return
     */
    public static boolean checkBasicTypeNull(Object object) {
        //基本类型默认值
        //基本类型经过Object一转，就会成包装类。
        Class<?> className = object.getClass();
        boolean defaultValue = false;
        if (className.equals(java.lang.Integer.class)) {
            defaultValue = (int) object == 0;
        } else if (className.equals(java.lang.Byte.class)) {
            defaultValue = (byte) object == 0;
        } else if (className.equals(java.lang.Long.class)) {
            defaultValue = (long) object == 0L;
        } else if (className.equals(java.lang.Double.class)) {
            defaultValue = (double) object == 0.0d;
        } else if (className.equals(java.lang.Float.class)) {
            defaultValue = (float) object == 0.0f;
        } else if (className.equals(java.lang.Character.class)) {
            defaultValue = (char) object == '\u0000';
        } else if (className.equals(java.lang.Short.class)) {
            defaultValue = (short) object == 0;
        } else if (className.equals(java.lang.Boolean.class)) {
            defaultValue = (boolean) object == false;
        }
        return defaultValue;
    }

}
