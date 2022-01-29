# Feature Flipping 4 Java - FF4J

## Overview

This project is built on the example from 

* [FF4J](https://ff4j.github.io/)

## Feature Flags

Feature flags allow you to turn on/off sections of code dynamically while the program is running.  You can employ a number
of different strategies for enabling/disabling features including: by user, ip, server name, etc


## Cool Features

This projects combines a number of cool features from the framework
* Enable Swagger to show documentation for the REST API
* Secure the web console with a username/password that is defined in src/main/resources/application.yaml
* Checking features via "if statements"
* Using features via annotations
* Command line interface to features

## Project

To run the project:

Run As -> Java Application -> com.scttech.sandbox.ff4j.Application

With the Spring Boot application running you can navigate to the following places:

* [http://localhost:8080/api/simple](http://localhost:8080/api/simple)

  The above should return "Hello" by default

* [http://localhost:8080/api/aop/simple](http://localhost:8080/api/aop/simple)

  The above will return "Hello" by default, this is an example of using Aspect-Oriented Programming (AOP).  The response is controlled by an annotation on the method that toggled by the "GreetingFeature"
  
* [http://localhost:8080/ff4j-web-console/](http://localhost:8080/ff4j-web-console/)
  
  Sign on using the username/password defined in src/main/resources/application.yaml
  
  The above will display an interactive screen where you can toggle navigate to the features and toggle on/off.  
  
  If you "Disable" the feature "Hello", then the URL [http://localhost:8080/api/simple](http://localhost:8080/api/simple) will return "Goodbye"
  
  If you "Enable" the feature "GreetingFeature" then the URL [http://localhost:8080/api/aop/simple](http://localhost:8080/api/aop/simple) will return "Bonjour"
  
  
* Run the CLI by Run As -> Java Application -> MainCli - org.ff4j.cli

  Log into the "local" environment and you will have command line access to the features
  
* View Swagger Documentation
  
  Swagger documentation is available [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

