# Getting started

## Prerequisites

In order to run the tests locally, you'll need to have a `mongo` database running, and a `rabbitmq-server` instance.

You can install these using homebrew.

Run these with: `mongod` and `rabbitmq-server`


## Pull the project

`git pull https://github.com/tothepoint-itco/coordinatorentool.git`

Because this project exists of multiple git repo's, that I've binded using `git submodules`, you need to pull all submodules. You can do this by running this command in the project root:

`git pull && git submodule init && git submodule update && git submodule status`

## Build the project and the docker images

I've written a `build-all.sh` file to do this job. `cd` to the project root directory and run:

`/build-all.sh`

If your machine won't run this command, try chmodding it first with:

`chmod +x build-all.sh`

## Launch the docker containers

To launch all the containers at once, I've created a `docker-compose` file. Navigate to the project root and run:

`docker-compose up -d`

## Rebuilding the containers

If you've changed something and would like to rebuild the docker containers. You can just re-run the `build-all.sh`and the `docker-compose up -d` commands. The `docker-compose` command will check if there were any changes to the docker images and re-run the ones that have changed.

An exception is the frontend service. You need to rebuild this with the command `docker-compose build frontend` and then run the `docker-compose up -d` command.
