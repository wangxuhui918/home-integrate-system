

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
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
