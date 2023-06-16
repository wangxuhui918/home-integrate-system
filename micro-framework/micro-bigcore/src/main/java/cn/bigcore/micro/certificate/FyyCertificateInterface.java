

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.certificate;

/**
 * 系统合法性校验
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public interface FyyCertificateInterface {

    void readCert(String path);

    void validate(String context);

    void writeCert();

}
