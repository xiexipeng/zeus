package com.xxp.dao;

import com.xxp.dao.entity.BaseUniqueIdAllocDO;
import com.xxp.dao.entity.BaseUniqueIdAllocDOCriteria;
import com.xxp.dao.mapper.BaseUniqueIdAllocDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: xiexipeng
 * @create: 2021/07/05 16:28:07
 * @description:
 * @Version
 **/
@Repository
public class BaseUniqueIdAllocDAO {

    @Autowired
    private BaseUniqueIdAllocDOMapper mapper;

    public BaseUniqueIdAllocDO queryByKey(String bizKey) {
        BaseUniqueIdAllocDOCriteria criteria = new BaseUniqueIdAllocDOCriteria();
        criteria.createCriteria().andBizKeyEqualTo(bizKey);
        List<BaseUniqueIdAllocDO> baseUniqueIdAllocDOS = mapper.selectByExample(criteria);
        if (CollectionUtils.isEmpty(baseUniqueIdAllocDOS)) {
            return null;
        }
        return baseUniqueIdAllocDOS.get(0);
    }
}
