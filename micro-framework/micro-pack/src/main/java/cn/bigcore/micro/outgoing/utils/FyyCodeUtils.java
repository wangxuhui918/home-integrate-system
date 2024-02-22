

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.outgoing.utils;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.exception.FyyCodeInterface;
import cn.bigcore.micro.outgoing.FyyOutputParamAbstract;
import cn.hutool.json.JSON;
import cn.bigcore.micro.outgoing.impl.FyyOutputParamRe;

public class FyyCodeUtils {

    public static FyyOutputParamRe go(FyyCodeInterface code) {
        FyyOutputParamAbstract<FyyCodeInterface, JSON> result = FyyInitEnv.ProjectInformation.fyyMessageDataOutInterface.go(null, code, null, null);
        FyyOutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static FyyOutputParamRe go(FyyCodeInterface code, cn.hutool.json.JSON data) {
        FyyOutputParamAbstract<FyyCodeInterface, JSON> result = FyyInitEnv.ProjectInformation.fyyMessageDataOutInterface.go(null, code, data, null);
        FyyOutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static FyyOutputParamRe go(Exception exception, cn.hutool.json.JSON data) {
        FyyOutputParamAbstract<FyyCodeInterface, JSON> result = FyyInitEnv.ProjectInformation.fyyMessageDataOutInterface.go(null, null, data, exception);
        FyyOutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static FyyOutputParamRe go(Exception exception) {
        FyyOutputParamAbstract<FyyCodeInterface, JSON> result = FyyInitEnv.ProjectInformation.fyyMessageDataOutInterface.go(null, null, null, exception);
        FyyOutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    /**
     * 原始出参类转换为自定义出参类
     *
     * @param result
     * @return
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     */
    private static FyyOutputParamRe extractedConvertToRe(FyyOutputParamAbstract<FyyCodeInterface, JSON> result) {
        FyyOutputParamRe re = new FyyOutputParamRe();
        re.setCodeBody(result.getCodeBody());
        re.setData(result.getData());
        re.setI18n(result.getI18n());
        return re;
    }

}
