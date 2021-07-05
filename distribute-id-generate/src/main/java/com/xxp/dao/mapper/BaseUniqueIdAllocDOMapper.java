package com.xxp.dao.mapper;

import com.xxp.dao.entity.BaseUniqueIdAllocDO;
import com.xxp.dao.entity.BaseUniqueIdAllocDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BaseUniqueIdAllocDOMapper {
    long countByExample(BaseUniqueIdAllocDOCriteria example);

    int deleteByExample(BaseUniqueIdAllocDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseUniqueIdAllocDO record);

    int insertSelective(BaseUniqueIdAllocDO record);

    List<BaseUniqueIdAllocDO> selectByExampleWithRowbounds(BaseUniqueIdAllocDOCriteria example, RowBounds rowBounds);

    List<BaseUniqueIdAllocDO> selectByExample(BaseUniqueIdAllocDOCriteria example);

    BaseUniqueIdAllocDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseUniqueIdAllocDO record, @Param("example") BaseUniqueIdAllocDOCriteria example);

    int updateByExample(@Param("record") BaseUniqueIdAllocDO record, @Param("example") BaseUniqueIdAllocDOCriteria example);

    int updateByPrimaryKeySelective(BaseUniqueIdAllocDO record);

    int updateByPrimaryKey(BaseUniqueIdAllocDO record);
}