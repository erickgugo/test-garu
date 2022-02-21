# test-garu

## Pre-Requeriment

* [Java](): Version 1.8 
* [Spring Boot](): Version 2.6.3
* [SwaggerUI](): Version 3.0
* [H2]():  


## Installation

Run

```bash
mvn spring-boot:run
```

## Documentation

URL [swagger](http://localhost:8080/swagger-ui/index.html).

##Operations
Create/Edit/Delete Student
```bash
POST    localhost:8080/student
PUT     localhost:8080/student
DELETE  localhost:8080/student/{studentId}
```
Create/Edit/Delete Class
```bash
POST    localhost:8080/course
PUT     localhost:8080/course
DELETE  localhost:8080/course/{code}
```
Browse list of all Student
```bash
localhost:8080/student
```
Browse list of all Classes
```bash
localhost:8080/course
```
View all Students assigned to a Class
```bash
localhost:8080/student/find/coursecode/INF-111
```
View all Classes assigned to a Student
```bash
localhost:8080/course/find/studentid/222BBB
```

##Relation Course-Student
```bash
GET localhost:8080/course/registered/MAT-112/studentid/222BBB
GET localhost:8080/course/unregistered/MAT-112/studentid/222BBB
```