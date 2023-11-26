#/bin/bash
docker rm  openi40-postgres-database  --force
docker image rm openi40-postgres-database --force
mkdir openi40-sql
mkdir openi40mes-sql
cp ../../openi40.platform/Persistence.Input.Channel/src/main/resources/*.sql ./openi40-sql
cp ../../openi40.mes/openi40-mes-shared-model/src/main/resources/*.sql ./openi40mes-sql
docker build  -t openi40-postgres-database .
docker run -d --name openi40-postgres-database -t openi40-postgres-database 
sleep 20
docker exec  openi40-postgres-database createdb --user openi40user openi40
docker exec  openi40-postgres-database createdb --user openi40user openi40mes
docker exec -it openi40-postgres-database bash  -c "cat /mnt/openi40-sql/create.sql | sed s/CLOB/TEXT/I | psql --user openi40user openi40 "
docker exec -it openi40-postgres-database bash -c "cat /mnt/openi40-sql/populate.sql | psql --user openi40user openi40"
docker exec -it openi40-postgres-database bash -c "cat /mnt/openi40-sql/alter.sql | psql --user openi40user openi40"
docker exec -it openi40-postgres-database bash  -c "cat /mnt/openi40mes-sql/create.sql | sed s/CLOB/TEXT/I | psql --user openi40user openi40mes "
docker exec -it openi40-postgres-database bash -c "cat /mnt/openi40mes-sql/populate-localtests.sql | psql --user openi40user openi40mes "
docker exec -it openi40-postgres-database bash -c "cat /mnt/openi40mes-sql/alter.sql | psql --user openi40user openi40mes "
docker commit openi40-postgres-database
