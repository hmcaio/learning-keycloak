# learning-keycloak

Project for learning Keycloak and OAuth2.

# Running

## Local

When running Keycloak via Docker Compose and the Spring Boot app locally, the token can be generated using the Insomnia collection.

## Docker

When running both Keycloak and the Spring Boot app using Docker Compose, the token generated via Insomnia will not work, because the iss claim in the generated token ("http://localhost:9000/realms/myrealm") will not be accepted by Keycloak.  
You need to get the token from inside the keycloak container, so that the iss will be matching ("http://keycloak:8080/realms/myrealm"):
```shell
curl --request POST \
    --url http://keycloak:8080/realms/myrealm/protocol/openid-connect/token \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --header 'User-Agent: insomnia/12.4.0' \
    --cookie JSESSIONID=REDACTED \
    --data client_id=myclient \
    --data grant_type=password \
    --data username=<youruserhere> \
    --data password=<yourpasswordhere>
```

# Versions

| Tool        | Version |
|-------------|---------|
| Java        | 25      |
| Spring Boot | 4.0.5   |
| Keycloak    | 26.5.6  |
|             |         |

# References

- [Integrating Keycloak Authentication with Spring Boot: A Complete Guide](https://medium.com/@iaravinda33/integrating-keycloak-authentication-with-spring-boot-a-complete-guide-98df2c8d244a)
- [Signing in with Google with Keycloak in 5 minutes](https://medium.com/@stefannovak96/signing-in-with-google-with-keycloak-bf5166e93d1e)
- [Keycloak User Self-Registration](https://www.baeldung.com/keycloak-user-registration)
- [Customizing the Login Page for Keycloak](https://www.baeldung.com/keycloak-custom-login-page)
- [Add Users to Keycloak realm with Spring Boot and perform various operations.](https://medium.com/@d.navle95/add-users-to-keycloak-realm-with-spring-boot-and-perform-various-operations-879529ff7a9f)
- [Retrieve User Information in Spring Security](https://www.baeldung.com/get-user-in-spring-security)
- [Spring Security Docs](https://docs.spring.io/spring-security/reference/index.html)
