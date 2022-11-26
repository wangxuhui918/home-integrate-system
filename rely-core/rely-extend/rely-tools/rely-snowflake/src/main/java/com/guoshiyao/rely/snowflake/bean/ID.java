

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.snowflake.bean;


import java.io.Serializable;


public class ID implements Serializable {
    private long timeStamp;
    private long worker;
    private long sequence;

    public ID(long timeStamp, long worker, long sequence) {
        this.setTimeStamp(timeStamp);
        this.setWorker(worker);
        this.setSequence(sequence);
    }

    public ID() {
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getWorker() {
        return worker;
    }

    public void setWorker(long worker) {
        this.worker = worker;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
}
