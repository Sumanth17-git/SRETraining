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

# Port-forward commands for kube-prometheus-stack services
# Alertmanager
kubectl port-forward service/alertmanager-operated 9093:9093 -n monitoring &
# Grafana
kubectl port-forward service/prom-stack-grafana 3000:80 -n monitoring &
# Prometheus Alertmanager
kubectl port-forward service/prom-stack-kube-prometheus-alertmanager 9093:9093 -n monitoring &
# Prometheus Operator
kubectl port-forward service/prom-stack-kube-prometheus-operator 443:443 -n monitoring &
# Prometheus
kubectl port-forward service/prom-stack-kube-prometheus-prometheus 9090:9090 -n monitoring &
# kube-state-metrics
kubectl port-forward service/prom-stack-kube-state-metrics 8080:8080 -n monitoring

helm upgrade --install  prom-stack prometheus-community/kube-prometheus-stack \
  --namespace monitoring \
  --set grafana.service.type=LoadBalancer \
  --set prometheus.service.type=LoadBalancer \
  --set grafana.adminPassword="admin@123"

kubectl get svc -n monitoring
kubectl get svc prom-stack-grafana -n monitoring
kubectl get svc prom-stack-kube-prometheus -n monitoring

helm rollback prom-stack <previous_revision> -n monitoring
helm history prom-stack -n monitoring

kubectl get pods -n monitoring

Grafana Dashboards
https://grafana.com/grafana/dashboards/4701-jvm-micrometer/
https://grafana.com/grafana/dashboards/17325-nodejs-application-dashboard/



# Remove the helm charts
helm uninstall kube-prometheus-stack --namespace monitoring
helm repo list
helm repo remove prometheus-community

