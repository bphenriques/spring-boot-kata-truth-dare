#!/bin/bash

curl -XPOST -H 'Content-Type: application/json' 'http://localhost:8080/truth-or-dare' -d"
  {
    \"challenge\": \"Talk in an accent for the following 5 minutes\",
    \"type\": \"DARE\"
  }
"

curl -XPOST -H 'Content-Type: application/json' 'http://localhost:8080/truth-or-dare' -d"
  {
    \"challenge\": \"Do a magic trick\",
    \"type\": \"DARE\"
  }
"

curl -XPOST -H 'Content-Type: application/json' 'http://localhost:8080/truth-or-dare' -d"
  {
    \"challenge\": \"Most awkward date.\",
    \"type\": \"TRUTH\"
  }
"
