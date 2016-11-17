## Demo data pipeline

### Objective
This project implements a simple data pipeline to process/forward a particular resource data. Example resource taken here is a user data with JSON representation.

There are three endpoints:
1. /users: to POST users to the data pipeline. This endpoint initiates the processing.
2. /allusers: to GET all the users posted till date in a JSON format.
3. /userfeed: A simple testing endpoint to observe live user feed being processed by data pipeline. It also provides a simple form to post user data to validate the working of data pipeline.

### Design and application components
Pipeline consists of
1. spring-boot application
2. Mysql RDBMS
3. Redis
4. ActiveMQ

setup in a distributed setting using docker containers. Application design is such that new type of resource can be easily added and processed. That is changes will be additive to introduce and process a new resource/data types, irrespective of the kind of processing required. The sample resource used to demo this pipeline has minimal to no processing, but rather gives out the skeleton to do so.

### Setup and Deployment
1. clone this repository
2. cd into the cloned directory and run the command: ``docker-compose up``

After MySql, ActiveMQ, and Redis have started, maven will perform a massive download for packages for building and starting spring-boot app. That's it. You can hit ``/userfeed`` endpoint and test the pipeline yourself. Generated application log should give out the flow of entered data.
