

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.snowflake.utils;

import cn.bigcore.micro.snowflake.bean.FyyId;
import cn.bigcore.micro.snowflake.bean.FyyIdMeta;

public class FyyIdConverterUtils {
    public static long convert(FyyId id) {
        long ret = 0;

        ret |= id.getSequence();

        ret |= id.getWorker() << FyyIdMeta.SEQUENCE_BITS;

        ret |= id.getTimeStamp() << FyyIdMeta.TIMESTAMP_LEFT_SHIFT_BITS;

        return ret;
    }

    public static FyyId convert(long id) {
        FyyId ret = new FyyId();

        ret.setSequence(id & FyyIdMeta.SEQUENCE_MASK);

        ret.setWorker((id >>> FyyIdMeta.SEQUENCE_BITS) & FyyIdMeta.ID_MASK);

        ret.setTimeStamp((id >>> FyyIdMeta.TIMESTAMP_LEFT_SHIFT_BITS) & FyyIdMeta.TIMESTAMP_MASK);

        return ret;
    }


}
