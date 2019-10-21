## JWT Integration example
Idea of this sample program is to illustrated jwt integration.
We will use authorization-code grant-type. This sample java/maven code is to illustrate JWT flow, this code must be used only as an example. Although for illustration this example uses HTTP (and localhost as domain), in real world you must use HTTPS, else token/s will be easily stolen while in transit.

## Dependencies
This example uses Spring-boot to create standalone http web-application and REST Api.

1. Spring-boot: 	Used for creating stand-alone, spring-boot based application
2. Okat :  We will use Okta as Authorization server. Okat will Authenticate user, Provision access-token (JWT) and will Authorize access (validate jwt token) when one ties to access data/API on resource server.

### Modules and responsibilities
1.	resource-server-api: This module renders data, acts as a data provider. All its APIs are secured and can be accessed only if valid jwt/access-token is presented.
2.	client-web-application: This module acts as client-interface over browser. It intercets user call, redirects to Okta for authentication, exchanges authorization token for access-token, and makes call to "resoruce-server-api" module for data.


## Flow
1) User access application using browser
2) If User is not yet authroized, then user is redirected to Okat login page. User Authenticates by providing credentials (user-id and password).
3) Web-Application gets authorization-code from Okta
4) Web-Application exchanges authorization-code  for access-token, .i.e. web-application will present its credentails (webapplication client credentails) along with user's authorization-token, in exhange it gets access-token from Okta. This access-token will be used to access data/API on resource-server on behalf of User.
5) Web-Application sets access-token in browser's cookie ( accessible only over http/s), so that it can not be read by jscript.
6) Web-Application presents valid access-token to resource-server, and gets data from resource server (REST API).
7) If one tries to access resource server (REST API) without valid access-token(jwt), then resource server will return http-error 401 (UnAuthorized)
