FROM gradle:7.4.0-jdk17

WORKDIR /deploy

COPY /deploy .

RUN gradle installDist

CMD ./build/install/deploy/bin/deploy