package com.brave.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: Qiang.cao
 * Date: 2022/7/17
 * Time: 11:28
 * Description:
 */
public class RegexUtils {

    /**
     * 验证URL地址
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    public static void main(String[] args) {
        Map<String, BigDecimal> simUsed = new HashMap<>();
        simUsed.put("1111", new BigDecimal(0));
        simUsed.put("2222", new BigDecimal(1000));

        BigDecimal total = new BigDecimal(0);
        total= total.add(new BigDecimal("1000"));
        System.out.println(total);

        BigDecimal bigDecimal = simUsed.get("1111");
        System.out.println(bigDecimal.toString());
        System.out.println(bigDecimal);
    }

}
