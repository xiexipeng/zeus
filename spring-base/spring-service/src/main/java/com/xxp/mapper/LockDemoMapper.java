package com.xxp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxp.domin.LockDemo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/16 下午8:08
 * @Version
 */
@Mapper
public interface LockDemoMapper extends BaseMapper<LockDemo> {

    LockDemo queryById(Long id);

    Integer updateById(Long id);

}
