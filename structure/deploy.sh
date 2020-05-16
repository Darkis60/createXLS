#!/bin/bash
#制造冲突
mvn package -Dmaven.test.skip=true
docker-compose up --build -d