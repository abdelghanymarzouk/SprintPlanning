
echo "starting run build docker";
docker build -t sprint-planning -f DockerFile .

echo "finished docker build";
echo "starting docker-compose";
docker-compose up;