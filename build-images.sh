#!/bin/bash

echo "🚀 Construyendo todas las imágenes Docker..."

# Compilar todos los microservicios
echo "📦 Compilando con Maven..."
mvn clean package -DskipTests

# Construir imágenes Docker
echo "🐳 Construyendo imágenes Docker..."

# Eureka Server
echo "📡 Construyendo eureka-server..."
cd eureka-server
docker build -t eureka-server:latest .
cd ..

# API Gateway
echo "🌐 Construyendo api-gateway..."
cd api-gateway
docker build -t api-gateway:latest .
cd ..

# Auth Service
echo "🔐 Construyendo authservice..."
cd authservice
docker build -t authservice:latest .
cd ..

# Publicaciones
echo "📚 Construyendo publicaciones..."
cd publicaciones
docker build -t publicaciones:latest .
cd ..

# Notificaciones
echo "🔔 Construyendo notificaciones..."
cd notificaciones
docker build -t notificaciones:latest .
cd ..

# Catalogo
echo "📖 Construyendo catalogo..."
cd catalago
docker build -t catalogo:latest .
cd ..

# Sincronización
echo "⏰ Construyendo sincronizacion..."
cd sincronizacion
docker build -t sincronizacion:latest .
cd ..

echo "✅ Todas las imágenes construidas correctamente!"
echo ""
echo "📋 Imágenes creadas:"
docker images | grep -E "(eureka-server|api-gateway|authservice|publicaciones|notificaciones|catalogo|sincronizacion)"

echo ""
echo "🚀 Ahora puedes aplicar los manifiestos de Kubernetes:"
echo "kubectl apply -f k8s/"