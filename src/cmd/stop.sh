pid=`ps -ef|grep java |grep CsClientApplication | awk '{print $2}'`
if [ -z ${pid} ];
then
  echo not started
else
  echo "stop process ${pid}"
  kill -9 ${pid}
  echo "${pid} stopped"
fi
