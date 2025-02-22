#!/bin/bash

# 백엔드 실행
cd /path/to/backend
    ./mvnw spring-boot:run &

# 프론트엔드 실행
cd /path/to/frontend
npm start
