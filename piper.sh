#!/usr/bin/env bash

SOURCE=${BASH_SOURCE[0]}

while [ -L "$SOURCE" ]; do
  TARGET=$(readlink "$SOURCE")
  if [[ $TARGET == /* ]]; then
    SOURCE=$TARGET
  else
    DIR=$( dirname "$SOURCE" )
    SOURCE=$DIR/$TARGET
  fi
done

R_DIR=$( dirname "$SOURCE" )
DIR=$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )
JAR_BIN=${DIR}/bin/build.jar
JAR_SRC=${DIR}/target/build.jar

maven_build() {
  echo "START BUILD!"
  mvn clean package -Pmaven-shade
}

move_build() {
  echo "MOVE '${JAR_SRC}' TO '${JAR_BIN}'"
  mv $JAR_SRC $JAR_BIN
}

if [[ "$1" == "-b" || ! -f $JAR_BIN ]]; then

  if [[ "$1" == "-b" ]]; then
    echo "FORCE BUILD!"
    shift 1
  fi

  maven_build
  move_build

else
  java -jar $JAR_BIN "$@"
fi
