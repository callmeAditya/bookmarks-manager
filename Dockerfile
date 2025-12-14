# # --------------------------------------------------------------------------------
# # STAGE 1: BUILD THE APPLICATION JAR
# # Uses a JDK image to compile the source code and build the executable JAR.
# # --------------------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# # Set the working directory inside the container
WORKDIR /app

# # Copy the pom.xml file first to take advantage of Docker layer caching
COPY pom.xml .

# # This step attempts to download dependencies without building the full project.
# # If pom.xml hasn't changed, this layer is cached, speeding up subsequent builds.
RUN mvn dependency:go-offline

# # Copy the rest of the source code
COPY src ./src

# # Build the executable JAR file
RUN mvn package -DskipTests

RUN mvn clean install

CMD [ "mvn", "spring-boot:run" ]

# # Find the name of the generated JAR file (based on your pom.xml structure)
# ARG JAR_FILE=target/bookmarks-manager-0.0.1-SNAPSHOT.jar

# # --------------------------------------------------------------------------------
# # STAGE 2: CREATE THE FINAL, LIGHTWEIGHT RUNTIME IMAGE
# # Uses a minimal JRE (Java Runtime Environment) for production.
# # --------------------------------------------------------------------------------

# # Define the entry point to run the application
# # Use the executable form for correct signal handling (e.g., stopping the container)
# ENTRYPOINT ["java", "-jar", "/app/app.jar"]
# WORKDIR '/app'

# COPY ./pom.xml ./

# RUN mvn clean install

# COPY ./ ./

# CMD [ "mvn", "spring-boot:run" ]