#!/usr/bin/env bash

JAR_BIN=./bin/jcf.jar
JAR_SRC=./target/build.jar
FORCE_COPY=0

if [ "$1" == "-f" ]; then
  FORCE_COPY=1
  shift 1
fi

if [[ $FORCE_COPY -gt 0 || ! -f $JAR_BIN ]]; then
  echo "FORCE_COPY: '${FORCE_COPY}'"
  if [ -f $JAR_SRC ]; then
    echo "COPY '${JAR_SRC}' TO '${JAR_BIN}'"
    mv $JAR_SRC $JAR_BIN
  else
    echo "BUILD '${JAR_SRC}' BEFORE"
    exit 1
  fi
fi

java -jar ./bin/jcf.jar jcf-cli "$@"