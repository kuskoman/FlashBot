#! /usr/bin/env bash

set -eo pipefail

newVersion=$1
currentVersion=$(./gradlew properties -q | grep "version:" | cut -d " " -f 2)
echo "Current version is $currentVersion"

if [ -z "$newVersion" ]; then
    newVersion=$(echo $currentVersion | awk -F. '{$NF = $NF + 1;} 1' | sed 's/ /./g')
fi

echo "Releasing version $newVersion"

awk -v version="$newVersion" '{gsub("'"$currentVersion"'", version)}1' build.gradle > build.gradle.tmp && mv build.gradle.tmp build.gradle

yq w -i chart/Chart.yaml appVersion "$newVersion"

# increment version in Chart.yaml by 1 in the patch field
yq w -i chart/Chart.yaml version "$(yq r chart/Chart.yaml version | awk -F. '{$NF = $NF + 1;} 1' | sed 's/ /./g')"
yq w -i chart/values.yaml image.tag "$newVersion"

