






# Scaling Pods based on Prometheus Metrics using Keda
- Step 1: Install KEDA
 helm repo add kedacore https://kedacore.github.io/charts
 helm repo update
 helm install keda kedacore/keda --namespace keda --create-namespace


kubectl apply -f keda-scaledobject.yaml
