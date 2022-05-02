package com.brave.token.controller;

import com.alibaba.fastjson.JSON;
import com.brave.token.domain.Response;
import com.brave.token.domain.Token;
import com.brave.token.domain.User;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author: Qiang.cao
 * Date: 2022/5/1
 * Time: 14:42
 * Description:
 */
@RestController
@RequestMapping("/api")
public class TokenController {

    @GetMapping("/token")
    public String getToken(@RequestHeader String username, @RequestHeader String password) {

        User user = getUser(username, password);
        if (user == null) {
            return "获取失败";
        }
        String token = "Basic " + Base64Util.encode(username + ":" + password);
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);
        long expireTime = date.getTime();
        Token token1 = new Token();
        token1.setToken(token);
        token1.setExpiredTimestamp(expireTime);
        return JSON.toJSONString(new Response(token1, new Date().getTime(), true)) ;
    }

    private User getUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
