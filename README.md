# spring-security-hydra

Spring Security and Hydra integration sample

## Setup

### Install Hydra CLI

```
brew tap ory/hydra
brew install ory/hydra/hydra
```

```
hydra version
Version:    v1.10.3
Git Hash:   ea931581eb54ab5dc142ea1f81357f25b8e4156a
Build Time: 2021-07-14T14:42:23Z
```

### Database

```
docker run -p 5432:5432 --name hydra-postgres -e POSTGRES_PASSWORD=pass -d postgres
psql -h localhost -p 5432 -U postgres
create database hydra;
quit
```

```
# Migration
export DSN=postgres://postgres:pass@localhost:5432/hydra?sslmode=disable
hydra migrate sql --yes $DSN
```

### Hydra

```
docker network create hydra-for-spring
docker pull oryd/hydra:v1.10.5-pre.1

# Run Hydra
export DSN=postgres://postgres:pass@host.docker.internal:5432/hydra?sslmode=disable
docker run -d \
  --name hydra-for-spring \
  --network hydra-for-spring \
  -p 9000:4444 \
  -p 9001:4445 \
  -e SECRETS_SYSTEM=hydra-secret-123456 \
  -e DSN=$DSN \
  -e URLS_SELF_ISSUER=http://localhost:9000/ \
  -e URLS_CONSENT=http://localhost:8080/consent \
  -e URLS_LOGIN=http://localhost:8080/login \
  oryd/hydra:v1.10.5-pre.1 serve all --dangerous-force-http 
docker logs hydra-for-spring

# Create client
hydra clients create --id hydra-for-spring --secret secret --scope api --callbacks https://example.com/callback --endpoint=http://localhost:9001
```

### Run Spring Application

```
./gradlew bootRun
```

## Authorization Code Grant

### Authorization Request

```
http://localhost:9000/oauth2/auth?client_id=hydra-for-spring&response_type=code&state=3cf22d86-fbde-11eb-9a03-0242ac130003&scope=api&redirect_uri=https://example.com/callback
```

### Token Request

```
curl -X POST -u "hydra-for-spring:secret" -d "grant_type=authorization_code" -d "code=Authorization code from Authorization Response" -d "redirect_uri=https://example.com/callback" http://localhost:9000/oauth2/token
```

### Call Spring API with Access Token

```
curl -H GET 'http://localhost:8080/api' -H 'Content-Type:application/json;charset=utf-8' -H 'Authorization: Bearer Access Token from Token Response'
```
