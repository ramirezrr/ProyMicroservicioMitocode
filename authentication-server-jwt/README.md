# Authentication server jwt


---
### Endpoints de pruebas
```http request
### POST REGISTER
POST http://localhost:9099/auth/register
Content-Type: application/json

{

  "name": "rodrigo",
  "lastname": "Ramirez",
  "email": "r4mire@gmail.com",
  "username": "VIEWER",
  "password": "VIEWER",
  "roles": [
    "ROLE_VIEWER"
  ]
}

### POST LOGIN
POST http://localhost:9099/auth/authenticate
Content-Type: application/json

{
  "username": "VIEWER",
  "password": "VIEWER"
}

### LISTAR TODO
GET http://localhost:9020/api/licencia-service/licencias/listLicenses
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWSUVXRVIiLCJpYXQiOjE3Mjc5MTI0NjIsImV4cCI6MTcyNzkxMjUyMiwicm9sZXMiOlsiUk9MRV9WSUVXRVIiXSwiZW1haWwiOiJyNG1pcmVAZ21haWwuY29tIiwibGFzdG5hbWUiOiJSYW1pcmV6In0.kcyD4XIdvn65st2nPMlBzwIFRtTY4eBabKkhms6csRkRKlVfCOH7jIPij816Edv2uHWyWVtF_003NkeH3L71Yg


### NUEVA LICENCIA
POST http://localhost:9020/api/licencia-service/licencias/issueLicense
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVU0VSIiwiaWF0IjoxNzI3OTExOTc3LCJleHAiOjE3Mjc5MTIwMzcsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJlbWFpbCI6InI0bWlyZUBnbWFpbC5jb20iLCJsYXN0bmFtZSI6IlJhbWlyZXoifQ.7zXSXosqf-6d_rPF9zSQVaxjtyfsswcqDSg6zwEyp8w4hxWewWv-QPLVVjlsLKfIOBXze0K94NaYYlfahESpNw

{
  "fechaEmision": "2024-10-02",
  "estado": "ACTIVO",
  "titular": {
    "id": 21,
    "numeroDocumento": "12345679",
    "nombres": "LUIS",
    "apellidos": "CARRASCO",
    "fechaNacimiento": "1985-06-25",
    "direccion": "Calle Falsa 123"
  },
  "tipoLicencia": {
    "id": 21,
    "categoria": "B1",
    "restricciones": "Para vehículos Pesados"
  }
}

```