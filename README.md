# FileProcessor

The software module, built using Spring Boot, consists of REST service that processess input files.

### What's the purpose of this software Module?

This module processess the file(.csv/xml) which contains the transaction information of a customer and validates it for the uniqueness of the transaction reference number and the transaction amount at the end of each transaction.

Any discrepencies found during the validation will be sent as an output to the validator. 

## Getting Started
The following software are necessary and can be downloaded from the links given below.
1. Download JDK 1.8 from this [link](http://www.oracle.com/technetwork/java/javaee/downloads/index.html).
2. Download Gradle 4.8.1 [here](https://gradle.org/releases/).
3. Download [Eclipse](https://www.eclipse.org/downloads/packages/release/photon/r/eclipse-ide-java-ee-developers).
4. Install Gradle plugin in Eclipse.

After downloading JDK and Gradle, set up the environment variable in your system.

## Setup the project
1. Open the Eclipse IDE and clone the project from github [link](https://github.com/jkappsdev/FileProcessor.git) to a local folder in your system.
2. Import the cloned project in to eclipse as a Gradle project in to Eclipse IDE.
3.Perform a Gradle refresh to download the dependencies.

## How to run the project
### Option 1: Running as Gradle Spring Boot application
1. Open command prompt on the project location.
2. Run the following command,
  ```bash
  gradle clean build bootrun
  ```
### Option 2: Running as Spring Boot fom the Eclipse IDE
1. As the project is imported as a Gradle project, you can run it from the IDE as a Spring Boot App.

## That's it! The application can be accessed at http://localhost:8080

If the port **8080** is being used by any other application, then make the following entry in application.properties and use the port that is free.

**server.port=******

## Testing the API:

1. Use postman or any other tool that can interact with HTTP APIs.
2. Attach the 'records.csv' or 'records.xml' file along with the request and hit the URL it.
3. Response will be the list of transaction references that are not valid.
