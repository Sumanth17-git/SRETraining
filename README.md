# SRE Training Materials and Projects

## Project 1 : Setup Prometheus Grafana ####



## Project 2: Spring Boot Microservice Logging

This Java Project is designed to expose business metrics.
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
