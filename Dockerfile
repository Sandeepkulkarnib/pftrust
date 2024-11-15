FROM openjdk:11

WORKDIR /app

COPY ./target/pftrust.war pftrust.war

COPY ./bin/start.sh start.sh

RUN chmod +x start.sh

EXPOSE 8089

ENTRYPOINT ["./start.sh"]