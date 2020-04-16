docker rm srvtests-db --force
docker rm srvtests-app --force
rm -rf mysql-data
mvn clean install &&
docker-compose build &&
docker-compose up