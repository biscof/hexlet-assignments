FROM gradle:7.4.0-jdk17

WORKDIR .

COPY . .

RUN gradle installDist

CMD ./build/install/deploy/bin/deploy