# Setup Prometheus, Grafana Stack using Helm Chart
   ```bash
Create a new namespace named 'monitoring' for kube-prometheus-stack installation
kubectl create namespace monitoring
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update

# Install Helm Chart setting "grafana.adminPassword=?"

helm upgrade --install prom-stack prometheus-community/kube-prometheus-stack --namespace monitoring --set grafana.adminPassword="admin_123*"
After installing kube-prometheus-stack Helm chart, we can use the 
kubectl get all -n monitoring
kubectl --namespace monitoring get pods -l "release=prom-stack"

# Remove the helm charts
helm uninstall kube-prometheus-stack --namespace monitoring
helm repo list
helm repo remove prometheus-community

