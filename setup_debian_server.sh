#!/bin/bash

echo "Starting system update..."
sudo apt update && echo "System update completed."

echo "Installing Git..."
sudo apt install git -y && echo "Git installation completed."

echo "Installing Maven..."
sudo apt install maven -y && echo "Maven installation completed."
mvn -version

echo "Installing OpenJDK 17..."
sudo apt install openjdk-17-jdk -y && echo "OpenJDK 17 installation completed."
java -version

echo "Installing additional updates..."
sudo apt install update && echo "Additional updates completed."

echo "Installing Curl..."
sudo apt install curl -y && echo "Curl installation completed."

echo "Installing Ping..."
sudo apt install iputils-ping -y && echo "Ping installation completed."

echo "Installing Python 3..."
sudo apt-get install python3 -y && echo "Python 3 installation completed."

echo "Installing Pip for Python 3..."
sudo apt-get install python3-pip -y && echo "Python 3 Pip installation completed."

echo "Installing Docker..."
sudo apt install docker.io -y && echo "Docker installation completed."
sudo systemctl start docker && echo "Docker service started."
sudo systemctl enable docker && echo "Docker service enabled to start on boot."

echo "Installing Docker Compose..."
sudo curl -L "https://github.com/docker/compose/releases/download/v2.14.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && echo "Docker Compose downloaded."
sudo chmod +x /usr/local/bin/docker-compose && echo "Docker Compose permissions set."
docker-compose --version && echo "Docker Compose installation completed."

echo "Adding current user to the Docker group..."
sudo usermod -aG docker $USER && echo "User added to Docker group. Please log out and log back in for this change to take effect."



echo "Setup Kubernetes on Compute......"
sudo apt-get update -y
echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
sudo apt-get install apt-transport-https ca-certificates gnupg curl -y
sudo apt-get install apt-transport-https ca-certificates  -y
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo tee /usr/share/keyrings/cloud.google.gpg > /dev/null
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /usr/share/keyrings/cloud.google.gpg
echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
sudo apt-get update && sudo apt-get install google-cloud-cli -y
apt-get update -y
apt-get install -y kubectl
kubectl version --client
gcloud --version
gke-gcloud-auth-plugin --version
apt-get install google-cloud-sdk-gke-gcloud-auth-plugin -y
kubectl get namespaces
gcloud container clusters get-credentials cluster-1 --zone us-central1-c --project focus-ensign-434715-g2
kubectl get nodes
kubectl get ns
echo "All commands executed successfully."
