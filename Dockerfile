FROM openjdk:17
WORKDIR usr/src/myapp
COPY target/my-web.jar /usr/src/myapp
CMD ["java","-jar","/usr/src/myapp/my-web.jar"]