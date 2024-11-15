#!/usr/bin/env bash
set -e

echo "building pftrust"

java --version

./mvnw clean package -DskipTests=true

echo "building docker image for pftrust"

docker build . -t pftrust:latest
