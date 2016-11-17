FROM maven
ADD . /code
WORKDIR /code
CMD mvn spring-boot:run
