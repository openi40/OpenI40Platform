#!/bin/bash
export CURRENT_BRANCH=`git branch --show-current`
if [ "$CURRENT_BRANCH" = "develop" ]; then
echo "We are correctly in develop branch"
else
echo "We are in the $CURRENT_BRANCH but this script can be run only from develop branch"
exit -1
fi
if [ -n "$1"  ] 
then
	echo "Old version: $1"
else
	echo "Required actual version as 1st parameter"
	exit 1
fi
if [ -n "$2" ] 
then
	echo "New version: $2"
else 
	echo "Required New version as 2nd parameter"
	exit 1
fi
echo "Changing from version $1 to $2"
export NEW_VERSION_BRANCH="feature/change-version-$2"
echo "Create new version branch $NEW_VERSION_BRANCH"
git checkout -b $NEW_VERSION_BRANCH
find . -name pom.xml | xargs sed -i s/"$1"/"$2"/ 
find . -name pom.xml | xargs git stage
git commit -m"Changed version from $1 to $2"

