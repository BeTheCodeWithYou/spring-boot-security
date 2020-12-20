# spring-boot-security

A spring boot security application using JPA and H2 in memory Database.

build the app

`gradle clean build`

run the app

`cd /build/libs`

`java -jar jpa-spring-security-0.0.1-SNAPSHOT`

launch the app

`http://localhost:8080/hello`

Spring security kicks in and prompt for user credentials. 
user - neeraj
password - admin - will hit the controller and show message.

( user credentials are being set into the h2 in-memory DB by H2DatabaseInitializer.java)

Any other values to user and password will return error by spring security as Bad Credentials.