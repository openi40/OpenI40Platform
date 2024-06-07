export CURRENT_VERSION=`head -n 5 ./pom.xml | tail -n 1 | sed s/"<version>"// | sed s/"<\/version>"// | sed s/"\t"// | sed s/" "//`
echo "Current version = $CURRENT_VERSION"
export SPLITTED_VERSION=`echo $CURRENT_VERSION | tr "." " " | sed s/"-SNAPSHOT"//`
#echo "Splitted version = $SPLITTED_VERSION"
export IDX=0
export NEWVERSION="";
for I in $SPLITTED_VERSION
do
	export IDX=$(("$IDX + 1"))
	if test "$IDX" = "3" ; then
		I=$(("SI + 1"))
	fi
        if  test "$IDX" = "1"; then
		export NEWVERSION="$I"
	else
		export NEWVERSION="$NEWVERSION.$I"
	fi	
     #echo $IDX
done     
if expr "$CURRENT_VERSION" : '.*-SNAPSHOT$' > /dev/null ; then 
export NEWVERSION="$NEWVERSION-SNAPSHOT"
fi
echo "New version is = $NEWVERSION"
./change-version-branch.sh $CURRENT_VERSION $NEWVERSION


