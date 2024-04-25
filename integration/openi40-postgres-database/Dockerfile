FROM postgres:12
RUN mkdir /mnt/openi40-sql
COPY  ./openi40-sql/*.sql /mnt/openi40-sql
RUN mkdir /mnt/openi40mes-sql
COPY ./openi40mes-sql/*.sql /mnt/openi40mes-sql
ENV POSTGRES_PASSWORD=openi40 
ENV POSTGRES_USER=openi40user 
ENV PGDATA=/var/lib/postgresql/data/pgdata
ENV POSTGRES_HOST_AUTH_METHOD=trust
#CMD su -l openi40user -c pg_ctl -D /var/lib/postgresql/data/pgdata -l logfile start
