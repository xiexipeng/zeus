1.启动redis
    docker start e5668c1cea24
    
2.连接redis
    redis-cli -h localhost -p 6379
    
    
遗留问题：
1.目前任务的拉取是用定时任务，每1秒执行一次，所以导致任务存在一定延迟，目前延迟在1秒以内