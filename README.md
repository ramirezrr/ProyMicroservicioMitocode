# ProyMicroservicioMitocode

### URL's
```shell 
# Url que cargará las propiedades de licencia
curl http://localhost:9000/licencia-service/dev
```
```shell
curl -X POST \
http://localhost:9000/encrypt \
-d "<contraseña>"
```
```shell
curl -X POST \
  http://localhost:9000/decrypt \
  -H 'Content-Type: application/json' \
  -d '<value-to-decrypt>'
```