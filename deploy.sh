#!/bin/bash

echo "Building VK User And Group application..."
./gradlew clean build

echo "Starting Minikube..."
minikube start --driver=docker --cpus=2 --memory=4g

echo "Building image directly in Minikube..."
minikube image build -t vkuserandgroup-app:latest .

echo "Deploying to Kubernetes..."
kubectl apply -f k8s/deployment.yml
kubectl apply -f k8s/service.yml

echo "Waiting for pods to be ready..."
sleep 10
kubectl wait --for=condition=ready pod -l app=vkuserandgroup-app --timeout=120s

echo "Application deployed!"
echo "Service info:"
kubectl get service vkuserandgroup-app-service

echo "Opening service in browser..."
minikube service vkuserandgroup-app-service