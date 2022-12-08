/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.plugin.thread.bean;

/**
 * @author 汪旭辉
 */
public enum KeyBase {
    I18N("I18N"),//
    USERRE("USERRE"),//

    ;//

    private String keyName;

    KeyBase(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
