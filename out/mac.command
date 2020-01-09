#!/bin/bash

cd "$(dirname "$0")"

mkdir ./src/main/resources/cache/raw
mkdir ./src/main/resources/cache/world

java -jar ./luna-client.jar
