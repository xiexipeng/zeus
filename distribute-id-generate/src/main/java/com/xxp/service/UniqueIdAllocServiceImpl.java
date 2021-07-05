package com.xxp.service;

import com.xxp.dao.BaseUniqueIdAllocDAO;
import com.xxp.dao.entity.BaseUniqueIdAllocDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/07/05 16:54:01
 * @description:
 * @Version
 **/
@Service
public class UniqueIdAllocServiceImpl {

    @Autowired
    private BaseUniqueIdAllocDAO baseUniqueIdAllocDAO;

    public BaseUniqueIdAllocDO queryByKey(String key){
        return baseUniqueIdAllocDAO.queryByKey(key);
    }
}
