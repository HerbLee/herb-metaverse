package com.herbmeta.dto;

import com.herbmeta.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author herb
 * @since 2021-10-07
 */

@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
