#/bin/bash
docker rm $1 --force
docker run -p 5432:5432 -d --name $1 --network openi40network -t openi40-postgres-database
sleep 20
docker exec  $1 createdb --user openi40user openi40
docker exec  $1 createdb --user openi40user openi40mes
docker exec -it $1 bash  -c "cat /mnt/openi40-sql/create.sql | sed s/CLOB/TEXT/I | psql --user openi40user openi40 "
docker exec -it $1 bash -c "cat /mnt/openi40-sql/populate.sql | psql --user openi40user openi40"
docker exec -it $1 bash -c "cat /mnt/openi40-sql/alter.sql | psql --user openi40user openi40"
docker exec -it $1 bash  -c "cat /mnt/openi40mes-sql/create.sql | sed s/CLOB/TEXT/I | psql --user openi40user openi40mes "
docker exec -it $1 bash -c "cat /mnt/openi40mes-sql/populate-localtests.sql | psql --user openi40user openi40mes "
docker exec -it $1 bash -c "cat /mnt/openi40mes-sql/alter.sql | psql --user openi40user openi40mes "

