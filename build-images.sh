#!/bin/bash

echo "🧹 Eliminando imágenes anteriores..."


echo "🚀 Construyendo imágenes con prefijo rfgualotuna/..."

# Compilar
mvn clean package -DskipTests

# Construir con prefijo correcto
cd eureka-server
docker build -t rfgualotuna/eureka-server:latest .
cd ..

cd api-gateway
docker build -t rfgualotuna/api-gateway:latest .
cd ..

cd authservice
docker build -t rfgualotuna/authservice:latest .
cd ..

cd publicaciones
docker build -t rfgualotuna/publicaciones:latest .
cd ..

cd notificaciones
docker build -t rfgualotuna/notificaciones:latest .
cd ..

cd catalago
docker build -t rfgualotuna/catalogo:latest .
cd ..

cd sincronizacion
docker build -t rfgualotuna/sincronizacion:latest .
cd ..

echo "✅ Imágenes construidas con prefijo rfgualotuna/!"
docker images | grep rfgualotuna/