FROM openjdk:8-jdk-alpine
ADD target/skillsoftpercipio.jar skillsoftpercipio.jar
ENTRYPOINT ["java", "-jar","skillsoftpercipio.jar"]
EXPOSE 5022