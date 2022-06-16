

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

/**
 * 汪旭辉个人所有
 */
package com.guoshiyao.rely.certificate;

/**
 * 系统合法性校验
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public interface CertificateAb {

    void readCert(String path);

    void validate(String context);

    void writeCert();

}
