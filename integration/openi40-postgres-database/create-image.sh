#/bin/bash

docker image rm openi40-postgres-database --force
mkdir openi40-sql
mkdir openi40mes-sql
cp ../../openi40.platform/Persistence.Input.Channel/src/main/resources/*.sql ./openi40-sql
cp ../../openi40.mes/openi40-mes-shared-model/src/main/resources/*.sql ./openi40mes-sql
docker build  -t openi40-postgres-database .
