package com.xxp.service;

import com.xxp.domin.LockDemo;
import com.xxp.mapper.LockDemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> 数据库死锁服务 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/16 下午8:09
 * @Version V1.0
 */
@Service
public class LockDemoService {

    @Autowired
    private LockDemoMapper lockDemoMapper;

    public LockDemo getLockDemoById(Long id){
        LockDemo lockDemo = lockDemoMapper.queryById(id);
        return lockDemo;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Boolean getLockDemoList(List<Long> idList){
        for (Long id: idList){
            lockDemoMapper.queryById(id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Boolean.TRUE;
    }

    public Integer updateById(Long id){
        Integer integer = lockDemoMapper.updateById(id);
        return integer;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Boolean updateLockDemoList(List<Long> idList){
        for (Long id: idList){
            lockDemoMapper.updateById(id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Boolean.TRUE;
    }
}
