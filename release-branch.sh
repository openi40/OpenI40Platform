export CURRENT_VERSION=`head -n 5 ./pom.xml | tail -n 1 | sed s/"<version>"// | sed s/"<\/version>"// | sed s/"\t"// | sed s/" "//`
echo "Current version = $CURRENT_VERSION"
export NEWVERSION=`echo $CURRENT_VERSION | sed s/"-SNAPSHOT"//`
echo "New version is = $NEWVERSION"
./change-version-branch.sh $CURRENT_VERSION $NEWVERSION


