FROM gradle:8.0.2-jdk17

WORKDIR .

COPY . .

RUN gradle installDist

CMD ./build/install/intro-to-spring/bin/intro-to-spring