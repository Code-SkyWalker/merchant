#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# OS specific support.  $var _must_ be set to either true or false.
cygwin=false
mingw=false
msys=false
darwin=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  MINGW*)  mingw=true ;;
  MSYS*)   msys=true ;;
  Darwin*) darwin=true
    # Use /usr/libexec/java_home if available, otherwise fall back to /System/Library/Frameworks/JavaVM.framework
    if [ -x "/usr/libexec/java_home" ]; then
      JAVA_HOME=$(/usr/libexec/java_home)
    else
      JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Home
    fi
    ;;
esac

if [ -z "$JAVA_HOME" ] && [ -e /usr/lib/jvm/default-java ]; then
  JAVA_HOME=/usr/lib/jvm/default-java
fi

if [ -z "$JAVA_HOME" ]; then
  if [ -x /usr/libexec/java_home ]; then
    # For platform MacOS, use built-in java_home to get the java path
    # https://stackoverflow.com/questions/20923946/what-is-the-right-way-to-get-the-java-home-path-in-bash
    JAVA_HOME=$(/usr/libexec/java_home)
  else
    # For platforms without java_home, use system PATH
    # https://github.com/moby/moby/issues/35644
    JAVA_HOME=$(which java >/dev/null 2>&1 ; echo $?)
    if [ $JAVA_HOME -ne 0 ]; then
      echo "The JAVA_HOME environment variable is not defined correctly" >&2
      echo "This environment variable is needed to run this program" >&2
      echo "NB: JAVA_HOME should point to a JDK not a JRE" >&2
      exit 1
    fi
  fi
fi

# For Cygwin or MSYS, switch paths to Windows format before running java
if $cygwin || $msys ; then
  JAVA_HOME=$(cygpath --absolute --windows "$JAVA_HOME")
  MAVEN_PROJECTBASEDIR=$(cygpath --absolute --windows "$MAVEN_PROJECTBASEDIR")
  MAVEN_CMD_LINE_ARGS=$(cygpath --absolute --windows "$MAVEN_CMD_LINE_ARGS")
fi

# Provide a "standardized" way to retrieve the CLI args that will
# work with both Windows and non-Windows executions.
MAVEN_CMD_LINE_ARGS="$MAVEN_CONFIG $*"

MAVEN_PROJECTBASEDIR_RELATIVE="$(dirname "$0")"
MAVEN_PROJECTBASEDIR=$(cd "$MAVEN_PROJECTBASEDIR_RELATIVE/."; pwd)

# Reset the value of MAVEN_HOME to that of MAVEN_PROJECTBASEDIR
# MAVEN_HOME is deprecated and will be removed in a future version
MAVEN_HOME=$MAVEN_PROJECTBASEDIR

if [ -n "$JAVA_HOME" ]; then
  if [ ! -x "$JAVA_HOME/bin/java" ]; then
    echo "The JAVA_HOME environment variable is not defined correctly" >&2
    echo "This environment variable is needed to run this program" >&2
    echo "NB: JAVA_HOME should point to a JDK not a JRE" >&2
    exit 1
  fi
fi

if [ -z "$MVNW_USERNAME" ] && [ -n "$MVNW_PASSWORD" ]; then
  echo "The MVNW_USERNAME environment variable is not defined correctly" >&2
  echo "This environment variable is needed to run this program" >&2
  echo "NB: MVNW_USERNAME and MVNW_PASSWORD must both be defined" >&2
  exit 1
fi

if [ -n "$MVNW_USERNAME" ] && [ -z "$MVNW_PASSWORD" ]; then
  echo "The MVNW_PASSWORD environment variable is not defined correctly" >&2
  echo "This environment variable is needed to run this program" >&2
  echo "NB: MVNW_USERNAME and MVNW_PASSWORD must both be defined" >&2
  exit 1
fi

if [ -z "$JAVA_HOME" ]; then
  JAVACMD="java"
else
  JAVACMD="$JAVA_HOME/bin/java"
fi

EXEC_DIR=$(pwd)
cd "$MAVEN_PROJECTBASEDIR"

WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

# If the maven wrapper jar is not present download it
if [ ! -e "$WRAPPER_JAR" ]; then
  # Download the maven wrapper jar
  echo "Downloading maven wrapper..."
  DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
  
  # Use MVNW_REPOURL if set
  if [ -n "$MVNW_REPOURL" ]; then
    DOWNLOAD_URL="$MVNW_REPOURL/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
  fi

  # If no curl, try wget
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$WRAPPER_JAR" "$DOWNLOAD_URL"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O "$WRAPPER_JAR" "$DOWNLOAD_URL"
  else
    echo "Either curl or wget is required to download maven wrapper" >&2
    exit 1
  fi
fi

# Execute the maven wrapper
$JAVACMD \
  -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR" \
  $MAVEN_OPTS \
  -classpath "$WRAPPER_JAR" \
  "$WRAPPER_LAUNCHER" \
  $MAVEN_CMD_LINE_ARGS

# Change back to the original directory
cd "$EXEC_DIR"