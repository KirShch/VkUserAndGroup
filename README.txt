Java SpringBoot REST app.
One endpoint
Header (vk_service_token), body (userId, groupId).
Return user first, last, middle names and membership to group.
Swagger: http://localhost:8080/swagger-ui/index.html

Can be deployed local to minikube (deploy.sh).
In deploy.sh has a command to open browser.

Add /swagger-ui/index.html to the path to open Swagger doc:
http://127.0.0.1:58080/swagger-ui/index.html).

Or can be opened by obtained URL:
like http://127.0.0.1:58080/swagger-ui/index.html#