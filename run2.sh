#!/bin/bash

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5431/postgres SPRING_DATASOURCE_USERNAME=postgres SERVER_PORT=8081 SEED_ENABLED=true TRUTHDARE_GATEWAY_URL=http://localhost:8080 ./gradlew bootRun
