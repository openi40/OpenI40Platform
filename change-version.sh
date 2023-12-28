#!/bin/bash
echo Changing from version $1 to $2
find . -name pom.xml | xargs sed -i s/"$1"/"$2/ 
find . -name pom.xml | xargs git stage 
git commit -m"Changed version from $1 to $2"

