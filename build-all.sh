#!/usr/bin/env bash

echo Building discovery-server...
cd coordinatoren-discovery; gradle --daemon clean build distDocker -x test; cd -

echo Building edge-server...
cd coordinatoren-edge; gradle --daemon clean build distDocker -x test; cd -

echo Building business-unit-service...
cd coordinatoren-businessunit-service; gradle --daemon clean build distDocker -x test -x asciidoctor; cd -

#echo Building bediende-service...
#cd coordinatoren-bediende-service; gradle --daemon clean build distDocker -x test -x asciidoctor; cd -

echo Building contract-service...
cd coordinatoren-contract-service; gradle --daemon clean build distDocker -x test -x asciidoctor; cd -

echo Building planning-service...
cd coordinatoren-planning-service; gradle --daemon clean build distDocker -x test -x asciidoctor; cd -

#echo Building beheer-service...
#cd coordinatoren-beheer-service; gradle --daemon clean build distDocker -x test -x asciidoctor; cd -
