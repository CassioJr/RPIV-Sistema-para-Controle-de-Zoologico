#!/bin/sh
#
# Startup script for the Webswing Admin
#
# Use following variables to override default settings:
# WEBSWING_HOME
# WEBSWING_OPTS
# WEBSWING_JAVA_HOME
# WEBSWING_JAVA_OPTS
# WEBSWING_LOG_FILE
# WEBSWING_PID_FILE
#
# for example:
# WEBSWING_HOME=/home/webswing WEBSWING_JAVA_HOME=/var/share/jdk8 ./webswing.sh start

export HOME=`dirname $0`
export OPTS="-admin -h 0.0.0.0 -j $HOME/jetty.properties"
export JAVA_HOME=$JAVA_HOME
export JAVA_OPTS=-Xmx128m
export LOG=$HOME/admin.out
export PID_PATH_NAME=$HOME/admin.pid

if [ -n "$WEBSWING_HOME" ]; then
    HOME="$WEBSWING_HOME/admin"
fi
if [ -n "$WEBSWING_OPTS" ]; then
    OPTS=$WEBSWING_OPTS
fi
if [ -n "$WEBSWING_JAVA_HOME" ]; then
    JAVA_HOME=$WEBSWING_JAVA_HOME
fi
if [ -n "$WEBSWING_JAVA_OPTS" ]; then
    JAVA_OPTS=$WEBSWING_JAVA_OPTS
fi
if [ -n "$WEBSWING_LOG_FILE" ]; then
    LOG=$WEBSWING_LOG_FILE
fi
if [ -n "$WEBSWING_PID_FILE" ]; then
    PID_PATH_NAME=$WEBSWING_PID_FILE
fi


if [ -z `command -v $0` ]; then
    CURRENTDIR=`pwd`
    cd `dirname $0` > /dev/null
    SCRIPTPATH=`pwd`/
    cd $CURRENTDIR
else
    SCRIPTPATH=""
fi

if [ ! -f $HOME/webswing-admin-server.war ]; then
    echo "Webswing Admin executable not found in $HOME folder"
    exit 1
fi

if [ ! -f $JAVA_HOME/bin/java ]; then
    echo "Java installation not found in $JAVA_HOME folder"
    exit 1
fi

# See how we were called.
case "$1" in
    run)
        # Run Webswing admin
        if [ ! -f $PID_PATH_NAME ] || [ `ps -axo pid | grep "$(cat $PID_PATH_NAME)" | wc -l` -eq 0 ]; then
            $JAVA_HOME/bin/java $JAVA_OPTS -jar $HOME/webswing-admin-server.war $OPTS 2>> $LOG >> $LOG &
            echo $! > $PID_PATH_NAME
            wait $(cat $PID_PATH_NAME)
        else
            echo "Webswing Admin is already running with pid $(cat $PID_PATH_NAME)"
        fi
        ;;
    start)
        # Start daemon.
        if [ ! -f $PID_PATH_NAME ] || [ `ps -axo pid | grep "$(cat $PID_PATH_NAME)" | wc -l` -eq 0 ]; then
            echo "Starting Webswing... "
            echo "HOME:$HOME"
            echo "OPTS:$OPTS"
            echo "JAVA_HOME:$JAVA_HOME"
            echo "JAVA_OPTS:$JAVA_OPTS"
            echo "LOG:$LOG"
            echo "PID:$PID_PATH_NAME"
            $SCRIPTPATH$0 run  &
            echo "Webswing STARTED"
        else
            echo "Webswing Admin is already running with pid $(cat $PID_PATH_NAME)"
        fi
        ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            echo "Webswing stoping ..."
            kill -9 $(cat $PID_PATH_NAME);
            if [ `ps -axo pid | grep "$(cat $PID_PATH_NAME)" | wc -l` -eq 0 ]; then
                echo "Webswing Admin stopped ..."
                rm $PID_PATH_NAME
            else
                echo "Stopping Webswing Admin failed."
                exit 1
            fi
        else
            echo "Webswing Admin is not running ..."
        fi
    ;;
    status)
        if [ -f $PID_PATH_NAME ]; then
            if [ `ps axo pid | grep "^ *$(cat $PID_PATH_NAME)$" | wc -l` -eq 0 ]; then
                rm $PID_PATH_NAME
            else
                echo "Webswing Admin is running with pid $(cat $PID_PATH_NAME)."
            fi
        else
            echo "Webswing Admin is not running ..."
        fi
    ;;
    restart)
        $SCRIPTPATH$0 stop
        $SCRIPTPATH$0 start
    ;;
    *)
        echo "Usage: $0 {run|start|stop|restart|status}"
        $JAVA_HOME/bin/java $JAVA_OPTS -jar $HOME/webswing-admin-server.war $OPTS
esac

exit 0
