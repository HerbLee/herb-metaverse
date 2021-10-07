package com.herbmeta.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.herbmeta.constant.AuthorityConstant;
import com.herbmeta.constant.CommonConstant;
import com.herbmeta.entity.MetaTestUser;
import com.herbmeta.dto.MetaTestUserMapper;
import com.herbmeta.service.IMetaTestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herbmeta.vo.LoginUserInfo;
import com.herbmeta.vo.UsernameAndPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */
@Service
// 出现任意异常，回滚
@Transactional(rollbackFor = Exception.class)
public class MetaTestUserServiceImpl extends ServiceImpl<MetaTestUserMapper, MetaTestUser> implements IMetaTestUserService {


    @Override
    public String generateToken(String username, String password) throws Exception {
        return generateToken(username,password,0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {


        MetaTestUser me = getOne(Wrappers.<MetaTestUser>lambdaQuery()
                .eq(MetaTestUser::getUsername, username)
                .eq(MetaTestUser::getPassword,password));

        if(me == null){
            return null;
        }
        if(expire <= 0){
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS).atStartOfDay(ZoneId.systemDefault());
        java.util.Date from = Date.from(zdt.toInstant());

        LoginUserInfo loginUserInfo = new LoginUserInfo(me.getId(),me.getUsername());


        return Jwts.builder().claim(CommonConstant.JWT_USER_INFO_KEY,
                JSON.toJSON(loginUserInfo))
                .setId(UUID.randomUUID().toString())
                .setExpiration(from)
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception {

        MetaTestUser one = getOne(Wrappers.<MetaTestUser>lambdaQuery()
                .eq(MetaTestUser::getUsername, usernameAndPassword.getUsername()));

        if(one != null){
            return null;
        }

        MetaTestUser metaTestUser = new MetaTestUser();
        metaTestUser.setUsername(usernameAndPassword.getUsername());
        metaTestUser.setPassword(usernameAndPassword.getPassword());
        save(metaTestUser);

        return generateToken(usernameAndPassword.getUsername(),usernameAndPassword.getPassword());
    }



    private PrivateKey getPrivateKey() throws Exception{
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));

        KeyFactory rsa = KeyFactory.getInstance("RSA");
        return rsa.generatePrivate(pkcs8EncodedKeySpec);
    }
}
