

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.bean;

import cn.hutool.core.io.IoUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//@Component
public class FyyBean implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (FyyBean.applicationContext == null) {
            FyyBean.applicationContext = applicationContext;
        }
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


    /**
     * 拷贝each个输入流(inputstream 会被关闭)
     * each 最大值允许为5
     *
     * @param inputstream
     * @param each
     * @author 汪旭辉
     * @date 2020-9-8
     * @readme TODO
     */
    public static List<InputStream> getEachInputStream(InputStream inputstream, int each) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IoUtil.copy(inputstream, out);
        List<InputStream> inputs = new ArrayList<>();
        for (int i = 0; i < each && i < 5; i++) {
            inputs.add(new ByteArrayInputStream(out.toByteArray()));
        }
        IoUtil.close(inputstream);
        IoUtil.close(out);
        return inputs;
    }

    /**
     * 关闭文件流
     *
     * @param list
     * @author 汪旭辉
     * @date 2020年9月8日
     * @readme TODO
     */
    public static void closeInputs(List<InputStream> list) {
        if (list != null && list.size() > 0) {
            for (InputStream inputStream : list) {
                IoUtil.close(inputStream);
            }
        }
    }

}
