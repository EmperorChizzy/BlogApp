FROM openjdk:17
COPY /target/EliteFashionBlog-0.0.1-SNAPSHOT.jar /app/EliteFashionBlog-app.jar
WORKDIR /app
EXPOSE 8085
CMD ["java", "-jar", "EliteFashionBlog-app.jar"]