
echo "starting run build docker";
docker build -t messaging-service -f DockerFile .

echo "finished docker build";
echo "starting docker-compose";
docker-compose up;