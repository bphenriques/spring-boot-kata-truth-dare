#!/bin/bash

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres SPRING_DATASOURCE_USERNAME=postgres SERVER_PORT=8080 ./gradlew bootRun
