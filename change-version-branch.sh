#!/bin/bash
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
export NEW_VERSION_BRANCH="change-version-$2"
echo "Create new version branch $NEW_VERSION_BRANCH"
git checkout -b $NEW_VERSION_BRANCH
find . -name pom.xml | xargs sed -i s/"$1"/"$2"/ 
find . -name pom.xml | xargs git stage
git commit -m"Changed version from $1 to $2"

