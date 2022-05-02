package com.brave.token.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: Qiang.cao
 * Date: 2022/5/1
 * Time: 14:44
 * Description:
 */
@Data
@EqualsAndHashCode
public class User implements Serializable {
    private String username;
    private String password;
}
