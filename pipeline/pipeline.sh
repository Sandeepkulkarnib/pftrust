#!/usr/bin/env bash
set -e

git clone https://github.com/rayeesGmail/pf-trust.git

cd pf-trust

./mvnw clean package -DskipTests=true

cp ./target/mahindrapf.war ~/new_pf_application/

cd ~/new_pf_application/

nohup java -jar mahindrapf.war &

ps xw

cat ./nohup.out