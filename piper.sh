#!/usr/bin/env bash

JAR_BIN=./bin/build.jar
JAR_SRC=./target/build.jar

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
