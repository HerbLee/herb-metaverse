package com.herbmeta.dto;

import com.herbmeta.entity.MetaTestUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */

@Mapper
public interface MetaTestUserMapper extends BaseMapper<MetaTestUser> {

}
