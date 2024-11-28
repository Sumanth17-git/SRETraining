# Self-Healing with KEDA for High CPU Usage

This guide demonstrates how to implement self-healing in Kubernetes using **KEDA** to monitor and handle high CPU usage. The system leverages Prometheus metrics and KEDA's Prometheus scaler to automate scaling and recovery.

---

## Scenario

- **Monitor High CPU Usage**: Track application CPU usage with Prometheus metrics.
- **Trigger Scaling Actions**: Use KEDA's Prometheus scaler to react to defined CPU thresholds.
- **Self-Healing**: Automatically restart pods or scale the application to manage increased load.

---

## Steps to Implement

### 1. Install KEDA

1. Add the KEDA Helm chart repository:
   ```bash
   helm repo add kedacore https://kedacore.github.io/charts
   helm repo update



```bash
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
