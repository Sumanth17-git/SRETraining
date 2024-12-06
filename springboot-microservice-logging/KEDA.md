

# Scaling Pods Based on Prometheus Metrics Using KEDA

This guide outlines the steps to set up and configure KEDA to scale your pods dynamically based on Prometheus metrics.

## Step 1: Install KEDA
1. Add the KEDA Helm chart repository:
   ```bash
   helm repo add kedacore https://kedacore.github.io/charts
   helm repo update
   helm install keda kedacore/keda --namespace keda --create-namespace

2. Verify the installation:
   ```bash
   kubectl get pods -n keda
3. Verify Prometheus Setup
Ensure Prometheus is installed and accessible. If you’re using the kube-prometheus-stack Helm chart, verify its service:
   ```bash
   kubectl get svc -n monitoring | grep prom-stack-kube-prometheus-prometheus
   kubectl port-forward -n monitoring svc/prom-stack-kube-prometheus-prometheus 9090:9090

4. Test connectivity:
   ```bash
   kubectl run curl-test --image=curlimages/curl:latest -it --rm -- sh
   curl http://prom-stack-kube-prometheus-prometheus.monitoring.svc.cluster.local:9090/api/v1/query?query=up

5. Configure the Prometheus ScaledObject for KEDA 
   ```bash
   kubectl apply -f keda-scaledobject.yaml
6. Setup Load test
   ```bash
   sudo apt install wrk -y
   wrk -t10 -c200 -d30s http://<springboot-app-service-cluster-ip>:8881/api/json
   kubectl get pods -w
   kubectl logs -n keda -l app=keda-operator
