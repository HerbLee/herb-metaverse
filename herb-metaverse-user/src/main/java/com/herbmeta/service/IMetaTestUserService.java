package com.herbmeta.service;

import com.herbmeta.entity.MetaTestUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.herbmeta.vo.UsernameAndPassword;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */
public interface IMetaTestUserService extends IService<MetaTestUser> {

    String generateToken(String username, String password) throws Exception;
    String generateToken(String username, String password, int expire) throws Exception;
    String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception;

}
