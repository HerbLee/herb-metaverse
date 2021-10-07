package com.herbmeta.service.impl;

import com.herbmeta.entity.MetaTestUser;
import com.herbmeta.dto.MetaTestUserMapper;
import com.herbmeta.service.IMetaTestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */
@Service
public class MetaTestUserServiceImpl extends ServiceImpl<MetaTestUserMapper, MetaTestUser> implements IMetaTestUserService {

}
