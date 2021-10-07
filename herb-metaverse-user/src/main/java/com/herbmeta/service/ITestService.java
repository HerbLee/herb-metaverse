package com.herbmeta.service;

import com.herbmeta.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */
public interface ITestService extends IService<Test> {

    void test();
}
