
## Project Spring Boot Microservice Logging

This Java Project is designed to expose business metrics and good amount of logs
- Step1: Build the Maven project
`mvn clean install`
- Step 2: Build Docker image or use existing one
  `docker build -t sumanth17121988/springbootmetrics:2`
- Step 3: Run it in Kubernetes
  `kubectl apply -f deployment.yml`
  `kubectl get pods`
  `kubectl get svc`
  
To test this API  : http://localhost:8881/api/json 

### Access Prometheus
- Endpoint: `/actuator/prometheus`
- Example URL: [http://localhost:8881/actuator/prometheus]
- Liveness Probe : http://<ipadress>:8881/actuator/health/liveness
- Readiness Probe : http://<ipadress>:8881/actuator/health/readiness

#### Metrics
- **`api_json_transaction_timer_count`**: Total requests (used for RPS calculation).
- **`api_json_transaction_timer_sum`**: Total duration of requests (in seconds).
- **`api_json_transaction_timer_max`**: Maximum transaction duration.
- **`api.product.views.count`**: Counts product views.

### Prometheus
#### RPS (Requests Per Second)
Query Prometheus with:
```promql
rate(api_json_transaction_timer_count[1m])
