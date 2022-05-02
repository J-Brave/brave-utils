package com.brave.token.domain;

import lombok.Data;

/**
 * @author: Qiang.cao
 * Date: 2022/5/1
 * Time: 15:05
 * Description:
 */
@Data
public class Token {
    private String token;
    private long expiredTimestamp;
}
