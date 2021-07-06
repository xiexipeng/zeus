package com.xxp.controller;

import com.xxp.dao.entity.BaseUniqueIdAllocDO;
import com.xxp.service.UniqueIdAllocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiexipeng
 * @create: 2021/07/05 16:55:44
 * @description:
 * @Version
 **/
@RestController
@RequestMapping("/uniqueId")
public class UniqueIdAllocController {

    @Autowired
    private UniqueIdAllocServiceImpl uniqueIdAllocService;

    @RequestMapping(value = "/alloc", method = RequestMethod.POST)
    public BaseUniqueIdAllocDO alloc(@RequestParam String key) {
        return uniqueIdAllocService.queryByKey(key);
    }
}
