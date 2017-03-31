# embedded_gradle
This is a basic example of a gradle/maven project using Spring Data Neo4j to communicate with an embedded Neo4j instance.
Domain code is taken from here - https://spring.io/guides/gs/accessing-data-neo4j/

Application code explicitly sets up driver config to use the embedded driver. There's a commented out section in `Application.java` showing how to configure the HTTP driver to use the external Neo4j server.

## To run:
1. clone the repo
2. with gradle: `gradle run` 
3. with maven: `mvn clean install`, `java -jar ./target/embedded_gradle-0.1.0.jar`

## NOTE
This is only intended to be used as an example, hence the hardcoded String literals, verbose setup for logging (as opposed to using Lombok for example), support for both Gradle and Maven etc.
