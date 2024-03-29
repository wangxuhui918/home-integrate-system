/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.thread;

import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.log.FyyLogBaseUtils;


/**
 * 仅对set,append方法进行限制(校验大小)
 */
public class FyyThreadVar extends JSONObject {

    /**
     * 限制最大大小为10KB
     */
    private final int max = 10;//10KB


//    @Override
//    public JSONObject append(String key, Object value) throws JSONException {
//        return super.append(key, value);
//    }

    @Override
    public JSONObject set(String key, Object value) throws JSONException {
        try {
            if ((this.toString().getBytes().length / 1024) > max) {
                FyyLogBaseUtils.debug(FyyThreadVar.class, "当前对象内容为{}:", this.toString());
                throw new FyyExceptionError("当前线程对象过大,最大为{}KB,当前大小{}KB", max, this.toString().getBytes().length / 1024);
            }
        } catch (Exception e) {
            throw e;
        }
        return super.set(key, value);
    }


}
