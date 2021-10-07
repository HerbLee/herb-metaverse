package com.herbmeta.controller;


import com.herbmeta.annotation.IgnoreResponseAdvice;
import com.herbmeta.service.IMetaTestUserService;
import com.herbmeta.util.TokenParseUtil;
import com.herbmeta.vo.JwtToken;
import com.herbmeta.vo.LoginUserInfo;
import com.herbmeta.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */
@Slf4j
@RestController
@RequestMapping("/user/authority")
public class MetaTestUserController {


    @Autowired
    IMetaTestUserService iMetaTestUserService;


    @IgnoreResponseAdvice
    @PostMapping("/token")
    public JwtToken token(@RequestBody UsernameAndPassword up){

        log.info("user {} login",up.getUsername());
        try {
            return new JwtToken(iMetaTestUserService.generateToken(up.getUsername(), up.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @IgnoreResponseAdvice
    @PostMapping("/register")
    public JwtToken registerToken(@RequestBody UsernameAndPassword up){

        log.info("user {} register",up.getUsername());

        try {
            return new JwtToken(iMetaTestUserService.registerUserAndGenerateToken(up));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @IgnoreResponseAdvice
    @GetMapping("/get")
    public String getResult(@RequestHeader("token") String token){

        try {
            LoginUserInfo loginUserInfo = TokenParseUtil.parseUserInfoFromToken(token);
            return loginUserInfo.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }



}
