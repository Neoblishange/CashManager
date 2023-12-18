# Utilisez l'image officielle de Maven pour la construction
FROM maven:3.8-jdk-11 AS builder

WORKDIR /app

# Copiez les fichiers de construction nécessaires
COPY Back_Cash_Manager/ .

# Exécutez la construction avec Maven
RUN mvn clean install

# Étape de production
FROM openjdk:11-jre-slim

WORKDIR /app

# Copiez le JAR construit depuis l'étape de construction
COPY --from=builder /app/target/Back_Cash_Manager-0.0.1-SNAPSHOT.jar /app/Back_Cash_Manager.jar

# Exposez le port sur lequel le serveur fonctionnera
EXPOSE 8080

# Commande pour démarrer le serveur
CMD ["java", "-jar", "/app/Back_Cash_Manager.jar"]
