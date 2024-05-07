Make Authentication using Keycloak , SpringBoot , MySQL and Docker.

==docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.3 start-dev

http://localhost:8080/realms/SpringBootDemoKeyCloak/protocol/openid-connect/token (Login user)
username = app_user
password = 123

http://localhost:8080/realms/SpringBootDemoKeyCloak/protocol/openid-connect/token (Login admin)
username = app_admin
password = 123
