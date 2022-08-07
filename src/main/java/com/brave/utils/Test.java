package com.brave.utils;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @author: Qiang.cao
 * Date: 2022/7/11
 * Time: 14:40
 * Description:
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Integer a = 12;
        Integer b = 12;
        System.out.println(Objects.equals(a, b));
        System.out.println(a == b);
    }
}
