package com.herbmeta.service.impl;

import com.herbmeta.entity.Test;
import com.herbmeta.dto.TestMapper;
import com.herbmeta.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    @Override
    public void test() {

    }
}
