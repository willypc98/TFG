@ECHO OFF
ECHO Lanzamiento y configuracion del docker de la aplicacion

docker build . -t laboratorio

docker run --name laboratorios -p 9000:9000  --net=bridge -d laboratorio

TIMEOUT 5 