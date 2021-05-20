package com.xxp.core.controller;

import com.xxp.core.dto.Job;
import com.xxp.core.service.DelayQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/09 10:27:09
 * @description:
 * @Version
 **/
@RestController
@RequestMapping(value = "/manager", produces = "application/json")
public class DelayQueueController {

    @Autowired
    private DelayQueueService delayQueueService;

    @RequestMapping("/add")
    public Boolean addJob(@RequestBody Job job) {
        return delayQueueService.addJob(job);
    }

    @RequestMapping("/delete")
    public Boolean deleteJob(@RequestParam String topic, @RequestParam Long jobId) {
        return delayQueueService.deleteJob(topic, jobId);
    }
}
