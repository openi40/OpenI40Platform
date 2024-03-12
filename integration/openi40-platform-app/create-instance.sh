!/bin/bash
docker rm $1
docker run -d -p 8081:8080 --link openi40-postgres-database:database --network openi40network --name $1 -t openi40-platform-app
