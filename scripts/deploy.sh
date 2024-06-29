#!/bin/bash

IS_GREEN_EXIST=$(docker ps | grep green)
DEFAULT_CONF=" /etc/nginx/nginx.conf"
DOCKER_COMPOSE_PATH="/home/ubuntu/docker-compose"

# blue가 실행 중이면 green을 up합니다.
if [ -z $IS_GREEN_EXIST ];then
  echo "### BLUE => GREEN ####"
  echo ">>> green image를 pull합니다."
  docker pull letsintern/blue-green
  echo ">>> green container를 up합니다."
  docker run -d --name green -p 8082:8080 -e TZ=Asia/Seoul letsintern/blue-green
  while [ 1 = 1 ]; do
  echo ">>> green health check 중..."
  sleep 3
  REQUEST=$(curl http://127.0.0.1:8082)
    if [ -n "$REQUEST" ]; then
      echo ">>> 🍃 health check success !"
      break;
    fi
  done;
  sleep 3
  echo ">>> nginx를 다시 실행 합니다."
  sudo cp /etc/nginx/nginx.green.conf /etc/nginx/nginx.conf
  sudo nginx -s reload
  echo ">>> blue container를 down합니다."
  docker stop blue
  docker rm blue

# green이 실행 중이면 blue를 up합니다.
else
  echo "### GREEN => BLUE ###"
  echo ">>> blue image를 pull합니다."
  docker pull letsintern/blue-green
  echo ">>> blue container up합니다."
  docker run -d --name blue -p 8081:8080 -e TZ=Asia/Seoul letsintern/blue-green
  while [ 1 = 1 ]; do
    echo ">>> blue health check 중..."
    sleep 3
    REQUEST=$(curl http://127.0.0.1:8081)
    if [ -n "$REQUEST" ]; then
      echo ">>> 🍃 health check success !"
      break;
    fi
  done;
  sleep 3
  echo ">>> nginx를 다시 실행 합니다."
  sudo cp /etc/nginx/nginx.blue.conf /etc/nginx/nginx.conf
  sudo nginx -s reload
  echo ">>> green container를 down합니다."
  docker stop green
  docker rm green
fi
