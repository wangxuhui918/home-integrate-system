

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.line.base;


/**
 * 配置写入基类
 *
 * @param <T>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme 链式接口管理器接口
 */
public interface FyyLineManagerInterface extends FyyLineInterface {
    //<T extends Line>
    void before();

    void start();

    void after();

}
