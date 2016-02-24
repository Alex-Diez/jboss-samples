#!/bin/sh

DIR=`pwd`
CLASS_PATH="${DIR}/lib/*"
MAIN_CLASS=org.jboss.tool.Main

java -cp ${CLASS_PATH} ${MAIN_CLASS}
