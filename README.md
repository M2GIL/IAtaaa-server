# Iataaa Server

Iataaa Server is web platform to manage draughts AI. The name IAtaaa stands for the IA (Intelligence Artificielle in french) acronym and the Yatta japanese word for "We did it".

## Requirements

You need Maven on your system to run the platform. Maven is a tool for Java projects. Install it on your system using [this documentation](https://maven.apache.org/install.html). Make sure to have the mvn command on your path.

## Getting Started

### Building the project

Execute `mvn clean install` in the root directory to build the Maven project.

### Run the project

Execute `mvn spring-boot:run` in the root directory to run the Spring-Boot project.
You need the [IAtaaa-client](https://github.com/M2GIL/IAtaaa-server) interface to add AI and run a game. You can also develop your own interface using the Web Service and Websocket provided by the server.

The port can be configured in the `src/main/resources/application.properties` file.

## Documentation

* [See the docs](https://github.com/M2GIL/IAtaaa-server/wiki)
