# spring-boot-security

A basic spring boot security application, leveraging custom UserDetailsService returning hard coded UserDetails model.

build the app

`gradle clean build`

run the app

`cd /build/libs`

`java -jar spring-security-basic-0.0.1-SNAPSHOT`

launch the app

`http://localhost:8080/hello`

Spring security kicks in and prompt for user credentials. 
free text user. Password as admin - will hit the controller and show message.
free text user. Password anything other then admin - will show bad credentials.