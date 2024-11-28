# SRETraining
These are the SRE training Materials  and projects
Project 1: springboot-microservice-logging
This Java Project is to expose business metrics
Access Prometheus at /actuator/prometheus. Validate:
---http://localhost:8881/actuator/prometheus

api_json_transaction_timer_count: Total requests (used for RPS calculation).
api_json_transaction_timer_sum: Total duration of requests (in seconds).
api_json_transaction_timer_max: Maximum transaction duration.
api.product.views.count: Counts product views
------------------------------------PROMETHEUS------------------------------------------------
For RPS:
Query Prometheus with:
rate(api_json_transaction_timer_count[1m])
-------------------------------------GRAFANA--------------------------------------------------
Visualization in Grafana
Import Prometheus metrics into Grafana.
Create dashboards for:
Requests per second: Use rate(api_json_transaction_timer_count[1m]).
Average transaction duration: api_json_transaction_timer_sum / api_json_transaction_timer_count.
Product views: api_product_views_count.
This setup tracks requests, durations, and user behaviors, making your metrics rich and actionable. Let me know if you need help creating Grafana dashboards or extending this further!
