#!/usr/bin/env bash

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
JAR_BIN=${SCRIPT_DIR}/bin/build.jar
JAR_SRC=${SCRIPT_DIR}/target/build.jar

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
