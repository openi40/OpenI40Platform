#/bin/bash
echo "Docker images before script:"
docker images
docker network rm openi40network --force
docker create network openi40network
cd openi40-postgres-database
./create-image.sh
cd ..
rm *.jar
export BOOTABLES=`find ../ -name *.bootable.jar -print`;
for B in $BOOTABLES 
do
cp -r $B .
done
export LOCAL_SPRING_BOOTS=`ls *.bootable.jar`
for F in $LOCAL_SPRING_BOOTS
do
export APPNAME=`echo $F|sed s/"com."// | sed s/".bootable.jar"// | sed s/'\.'/'\-'/g`
echo "Managing $F with docker name $APPNAME";
mkdir $APPNAME
mv $F $APPNAME
docker image rm $APPNAME --force
docker build --build-arg JAVA_EXTRA_SECURITY_DIR=/app -t $APPNAME $APPNAME/
done
echo "Docker images after script:"
docker images
