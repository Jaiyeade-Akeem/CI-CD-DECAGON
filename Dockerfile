FROM openjdk:8
EXPOSE 8080
ADD target/springboot-cicd-git-action.jar springboot-cicd-git-action.jar
ENTRYPOINT ["java", "-jar", "springboot-cicd-git-action.jar"]
