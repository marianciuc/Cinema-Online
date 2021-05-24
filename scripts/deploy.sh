#!/usr/bin/env bash

mvn clean package

scp -i ~/.ssh/id_rsa_drucoder \
    target/sweater-1.0-SNAPSHOT.jar \
    root@45.141.76.252:root/git/

echo "Restart server..."

ssh -i ~/.ssh/id_rsa_drucoder root@45.141.76.252

pgrep java | xargs kill -9
nohup java -jar sweater-1.0-SNAPSHOT.jar > log.txt