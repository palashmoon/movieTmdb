FROM openjdk:10-jdk

ADD ./target/movietmdb-0.0.1-SNAPSHOT.jar /usr/app/movietmdb-0.0.1-SNAPSHOT.jar

WORKDIR usr/app
\NTRYPOINT ["java","-jar", "movietmdb-0.0.1-SNAPSHOT.jar"]-