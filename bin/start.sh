#!/usr/bin/env bash

set -e

echo "========================================================================="
echo "Running start.sh as:"
echo "========================================================================="

CMD="java -XX:MaxRAMPercentage=75.0 -jar pftrust.war"
echo "Launch command is $CMD"

bash -c "$CMD"
