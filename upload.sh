#!/usr/bin/env bash

curl -X POST https://api.sixlab.cn/shutdown

scp ./sixlab-web-server/target/app.jar root@114.215.89.74:/root/server/sixlab-server/app.jar

ssh -t -p 22 root@114.215.89.74 '/root/server/sixlab-server/run.sh'