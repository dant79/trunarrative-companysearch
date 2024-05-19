# Trunarrative Company Search

This is a gradle project built with the Gradle Wrapper. 

To build run 
```
./gradlew clean build
```

To run the integration tests run 
```
./gradlew check
```

To start the spring boot application run 
```
./gradlew bootRun
``` 

To use the service you will need to make a post request to the following endpoint. 
The api key will need to be set in a header of x-api-key 
and a parameter of active-only will need to be set to true or false depending whether you wish to see only active companies.


```
http://localhost:8080/companysearch?active-only=true
```

To test error conditions the spring profile 'local' has been set to return a 500 error from the Trunarrative API.
To run with this profile 

```
./gradlew bootRun --args='--spring.profiles.active=local'
```