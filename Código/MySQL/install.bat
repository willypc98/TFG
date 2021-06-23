@ECHO OFF
ECHO Descarga, lanzamiento y configuracion de mysql

docker pull mysql:5.7

docker run --name db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root  --net=bridge -d mysql:5.7

TIMEOUT 5 