package com.herbmeta;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RSATest {

    @Test
    public void get() throws Exception{
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(2048);

        //生成对
        KeyPair keyPair = rsa.generateKeyPair();
        RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();

        log.info("======== {}", Base64.encode(aPublic.getEncoded()));
        log.info("======== {}", Base64.encode(aPrivate.getEncoded()));

    }

}
