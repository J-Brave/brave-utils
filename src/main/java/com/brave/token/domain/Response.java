package com.brave.token.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Qiang.cao
 * Date: 2022/5/1
 * Time: 14:48
 * Description:
 */
@Data
public class Response implements Serializable {
    private int status;
    private String message;
    private boolean success;
    private long timestamp;
    private Object data;

    public Response(Object data, long timestamp, boolean success) {
        this.status = 200;
        this.message = "成功";
        this.success = success;
        this.timestamp = timestamp;
        this.data = data;
    }
}
