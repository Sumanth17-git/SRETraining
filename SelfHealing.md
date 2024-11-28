# Self-Healing with KEDA for High CPU Usage
'''bash
Scenario:
Monitor high CPU usage using Prometheus metrics.
Use KEDA's Prometheus scaler to trigger scaling actions.
Restart pods or scale the application based on CPU metrics.
1. Install KEDA
helm repo add kedacore https://kedacore.github.io/charts
helm repo update
helm install keda kedacore/keda --namespace keda --create-namespace
kubectl get pods -n keda

2.Configure Prometheus
kubectl port-forward -n monitoring svc/prom-stack-kube-prometheus-prometheus 9090:9090
Test the query for CPU usage in Prometheus
sum(rate(container_cpu_usage_seconds_total[1m])) by (pod)
