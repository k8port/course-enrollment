#pull latest gradle image as builder
FROM gradle:latest as builder
#copy codes to /app/gradle/src and chown: change ownership of files and directories
ADD --chown=gradle:gradle . /app/gradle/src
#change working directory
WORKDIR /app/gradle/src
#create jar file for user-service
RUN gradle clean assemble

#pull java 8 image
FROM java:8
#permanent storage folder
VOLUME /tmp
COPY ./wait-for-it.sh ./wait-for-it.sh
#wait-for-it as executable
RUN chmod +x /wait-for-it.sh
#copy and paste as app.jar
COPY --from=builder /app/gradle/src/build/libs/zuul-gateway-service-0.0.1.jar app.jar
#update on change
RUN bash -c 'touch /app.jar'
#runtime arguments=>SecureRandom as urandom
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]
