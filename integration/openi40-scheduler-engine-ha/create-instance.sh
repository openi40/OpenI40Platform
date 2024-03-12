!/bin/bash
docker rm $1
docker run -d -p 8083:8080 --link openi40-persistence-server-app-001:persistence-server --network openi40network --name $1 -t openi40-scheduler-engine-ha
