#!/usr/bin/env bash
docker run  --name mysql_tx1 --env MYSQL_ROOT_HOST=172.17.%.% --env MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d -v /Users/xiexipeng/learning/mysql_data/mysql_tx1_data:/var/lib/mysql mysql/mysql-server:5.7

docker run --name mysql_tx2 --env MYSQL_ROOT_HOST=172.17.%.% --env MYSQL_ROOT_PASSWORD=123456 -p 3307:3306 -d -v /Users/xiexipeng/learning/mysql_data/mysql_tx2_data:/var/lib/mysql mysql/mysql-server:5.7
