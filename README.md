

# Scaling Pods Based on Prometheus Metrics Using KEDA

This guide outlines the steps to set up and configure KEDA to scale your pods dynamically based on Prometheus metrics.

## Step 1: Install KEDA
1. Add the KEDA Helm chart repository:
   ```bash
   helm repo add kedacore https://kedacore.github.io/charts
   helm repo update





# Scaling Pods based on Prometheus Metrics using Keda
- Step 1: Install KEDA
 helm repo add kedacore https://kedacore.github.io/charts
 helm repo update
 helm install keda kedacore/keda --namespace keda --create-namespace
Verify the installation:
kubectl get pods -n keda
Verify Prometheus Setup
Ensure Prometheus is installed and accessible. If you’re using the kube-prometheus-stack Helm chart, verify its service:
kubectl get svc -n monitoring | grep prom-stack-kube-prometheus-prometheus
kubectl port-forward -n monitoring svc/prom-stack-kube-prometheus-prometheus 9090:9090

Test connectivity:
kubectl run curl-test --image=curlimages/curl:latest -it --rm -- sh
curl http://prom-stack-kube-prometheus-prometheus.monitoring.svc.cluster.local:9090/api/v1/query?query=up

Step 4: Configure the Prometheus ScaledObject for KEDA 
kubectl apply -f keda-scaledobject.yaml
Step 5: Test Autoscaling
sudo apt install wrk -y
wrk -t10 -c200 -d30s http://<springboot-app-service-cluster-ip>:8881/json
kubectl get pods -w
Check KEDA logs for scaling actions:
kubectl logs -n keda -l app=keda-operator

