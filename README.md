# Contacts Application

An application that utilizes a RESTful API, MongoDB, and a simple Thymeleaf view frontend.

## Getting Started

### Setting up the Database
Edit the following parts in application.properties:

```
spring.data.mongodb.host = [your_database_Ip]
spring.data.mongodb.port = [your_database_port]
spring.data.mongodb.database = [your_database_name]
```
Create a Collection with the name ```contacts```

### Import Dummy data

TBD

### Running the application

```
mvn spring-boot:run
```
Or if Maven is not installed, use the wrapper:
```
mvnw spring-boot:run
```
## Application Endpoints

### REST

```
*/contacts/rest/
*/contacts/rest/{id}
```
### View
```
*/contacts
*/contacts/
*/contacts/addContact
*/contacts/delete/{id}
*/contactseditContact/{id}
```


## Deployment

### Build with Maven

```
mvnw package
```

### Use Dockerfile with Dockerfile
```
$ docker build -t app .
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [SpringBoot](https://spring.io/) - 2.1.7.RELEASE

## Authors

* **Dániel Schreiber** - *Initial work* - [VikingPingvin](https://github.com/VikingPingvin)

