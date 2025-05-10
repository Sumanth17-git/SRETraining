# grafana-lgtm-compose
This repo contains a Docker Compose stack that deploys **Grafana LGTM** with an **OpenTelemetry (OTEL)** instrumented FastAPI application. The API includes a Swagger UI where you can test endpoints directly in the browser.

The API integrates with the Coinbase REST API and MySQL, generating:  

✅ Logs → Loki   
✅ Metrics → Prometheus  
✅ Traces → Tempo  (OTEL)

## Related Repositories:
FastAPI REST API on GitHub: d-blanco/fastapi-otlp-prometheus
API Docker Image on Docker Hub: danblanco/fastapi-otlp-mimir

## Instructions

1. Clone the repo and cd into it
2. Create a .env file, as shown below
```
# .env file
MYSQL_ROOT_PASSWORD=rootpassword
MYSQL_DATABASE=Crypto
MYSQL_USER=user1
MYSQL_PASSWORD=canbeanything
DB_PASSWORD=canbeanything
TEMPO_URL=tempo:4317
```
3. Run `docker-compose up -d`
4. visit endpoins below
```
http://localhost:8080/docs # Fastapi-app (crpyto currency API)
use BTC as symbol
http://localhost:9090 # Prometheus
http://localhost:3000 # Grafana
```
5. run `docker-compose down` when you are done.

6. Generate the Load
```bash
k6 run --vus 30 --duration 300s load-test.js
```
Install k6 (via deb package)
This is the most reliable way to install the latest version on Ubuntu.
```bash
# Add k6's GPG key
sudo apt install -y gnupg ca-certificates
curl -s https://dl.k6.io/key.gpg | sudo gpg --dearmor -o /usr/share/keyrings/k6-archive-keyring.gpg

# Add the repo
echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | \
  sudo tee /etc/apt/sources.list.d/k6.list

# Update package index
sudo apt update

# Install k6
sudo apt install -y k6

```