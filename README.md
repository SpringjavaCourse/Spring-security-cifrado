

## Cifrado

Es un proceso de codificar la información de su representación original (Texto plano) 
a texto cifrado, de manera que solamente puede ser descifrado utilizando una clave.

### Historia del cifrado:

1. Almacenar contraseñas en texto plano.
2. Almacenar contraseñas cifradas con una función hash.
3. Almacenar contraseñas cifradas con una función hash + salt.
4. Almacenar contraseñas cifradas con una función adaptativa + factor de trabajo


La seguridad se gana haciendo que **la validación de contraseña sea costosa computacionalmente**.

## Algoritmos en Spring Security:

* BCrypt
* PBKDF2
* scrypt
* argon2

****


## JWT

Es un estandar abierto, que permite transmitir información entre dos partes:Cliente y Servidor. 
Para obtener una introducción y datos mas especificos ingresar al siguiente [LINK](https://jwt.io/introduction).

JSON Web Token

## Funcionamiento Session

1. Cliente envia una petición a un servidor ```"(/api/login)"```.
2. Servicor valida username y password, si no son validos devolverá ```401 unauthorized```.
3. Seridor valida username y password, si son validos, se almacena el usuario en session.
4.  Se genera una cookie en el Cliente.
5. En próximos peticiones se comprueba que el cliente está en session.

### Desventajas:
* La información se almacena en el servidor, lo cual consume recursos.


## Funcionamiento JWT

1. Cliente envia una petición a un servidor ```"(/api/login)"```.
2. Servicor valida username y password, si no son validos devolverá ```401 unauthorized```.
3. Seridor valida username y password, si son validos, genera un Token JWT utilizando una secret Key
4. Servidor devuelve el token JWT generado.
5. Cliente envia peticiones a los endpoints REST del servidor utilizando el token JWT en las cabeceras.
6. Servidor valida el token JWT en cada petición y si es correcto permite el acceso a los datos.

### Ventajas

* El token se almacena en el Cliente, de manera que consume menos recursos en el servidor, lo cual permite mejor escalabilidad.

### Desventajas
* El token esta en el navegador, por lo tanto, no es posible invalidar el token antes de la fecha de expiración asignada cuando se creó.
* Lo que se realiza es dar la opción de logout, generando que el token se borre.

## Estructura del token JWT

3 partes separadas por un (.) y codificadas en base 64 cada una:

1. Header

```json
{
  "alg": "HS512",
  "typ": "JWT"
}
```

2. Payload (información, datos del usuario, no sensibles)

```json
{
  "name": "Duvan Castro",
  "admin": true
}
```

3. Signatura

```
HMACKSHA256(
base64UrlEncode(header) + "." + base64UrlEncode(payload), secret
)
```


El User-Agent envía el token JWT en las cabeceras:

```
Authorization: Bearer <token>
```

## Configuración Spring

Crear proyecto con las siguientes dependencias:
* Spring Security
* Spring Web
* Spring Boot devtools
* Spring Data JPA
* PostgreSQL
* Dependencia JWT (se añade manualmente en pm.xml)

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>        
```

****

## Open Authorization (OAuth)

Es un framework de autorización, abierto y está construido estandares IETF y licenciado bajo Open Web Foundation.

Es un protocolo de delegación:
* Permite que alguien que controla un recurso permita a una palicación de software acceder a ese recurso en su propio nombre sin pasar por ellos.
* Con la ayuda de OAuth los usuarios pueden autorizar a third part applications a acceder a sus datos o ejecutar determinadas operaciones sin necesidad de proporcionar usuario y contraseña.

### Flujo de trabajo con OAuth:

1. Una aplicación solicitada autenticación.
2. Se realiza login mediante Google
3. La aplicación se comunica con Google donde se utiliza las credenciales de Googlesin que la aplicación pueda verlos.
4. El servidor de Google pregunta al usuario si desea conceder X permisos.
5. El uduario acepta los permisos.
6. Google genera un token de acceso como respuesta.
7. La aplicación utiliza ese token.

### Escenarios para implementar OAuth

1. Autenticación HTTP en la que no se requiere utilizar usuario y contraseña todo el tiempo.
2. Múltiples aplicaciones dentro de una misma empresa y en consecuencia multiples cuentas con el mismo usuario y contraseña.
3. Arquitecturas de microservicios.
4. Interacción de aplicaciones de terceros.

### Proveedores

1. Google
2. Github
3. Facebook
4. Okta
5. 

### OAuth en Spring Security

Inicialmente habia un proyecto llamado OAuth.

En el 2018 se sobreescribe para hacerlo más eficiente, con un codigo base mas sencillo.

Se depreca el antiguo y ahora OAuth está integrado sobre el propio Spring Security.

Incluye:

* Client Support
* Resource server
Authorization server

keycloak: https: //www.keycloak.org/

Ver ejemplos de aplicaciones: **https://github.com/spring-projects/spring-authorization-server**

### Flujos de acción OAuth:

* Authorization code.
* Implicit.
* Resource Owner password credentials
* Client Credentials
* Refres Token

### OpenID Connect

* OpenID Connect ---> Authentication
* OAuth 2.0 ---> Authorization
* HTTP