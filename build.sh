#!/bin/bash

if [ "$JAVA_HOME" = "" ] ; then
  echo "ERROR: JAVA_HOME not found in your environment."
  echo
  echo "Please, set the JAVA_HOME variable in your environment to match the"
  echo "location of the Java Virtual Machine you want to use."
  exit 1
fi

if [ -z "$EXIST_HOME" ]; then
    P=$(dirname $0)

    if test "$P" = "." 
    then
        EXIST_HOME="`pwd`"
    else
        EXIST_HOME="$P"
    fi
fi

LOCALCLASSPATH=$CLASSPATH:$EXIST_HOME/lib/core/ant-launcher.jar:$EXIST_HOME/lib/core/junit.jar:$EXIST_HOME/lib/core/jakarta-oro-2.0.6.jar

JAVA_ENDORSED_DIRS="$EXIST_HOME"/lib/endorsed

JAVA_OPTS="-Dant.home=$EXIST_HOME -Djava.endorsed.dirs=$JAVA_ENDORSED_DIRS -Dexist.home=$EXIST_HOME"

echo Starting Ant...
echo

$JAVA_HOME/bin/java -Xms64000K $JAVA_OPTS -classpath $LOCALCLASSPATH org.apache.tools.ant.launch.Launcher $*
