/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.base.thread;

/**
 * @author 汪旭辉
 */
public enum FyyKeyBase {
    I18N("I18N"),//
    PAGE_SIZE("PAGE_SIZE"),//
    PAGE_NUM("PAGE_NUM"),//
    TOTAL("TOTAL"),//
    USERRE("USERRE"),//

    ;//

    private String keyName;

    FyyKeyBase(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
