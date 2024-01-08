# Utilisez l'image officielle de Maven pour la construction
FROM openjdk:18.0.2.1
FROM maven:3.8.8

WORKDIR /app

# Copiez les fichiers de construction nécessaires
# COPY Back_Cash_Manager/ .

# Exécutez la construction avec Maven

# Copiez le JAR construit depuis l'étape de construction
# COPY --from=builder /app/target/Back_Cash_Manager-0.0.1-SNAPSHOT.jar /app/Back_Cash_Manager.jar

# Exposez le port sur lequel le serveur fonctionnera
EXPOSE 8080

CMD bash -cl ./app-run.sh
#CMD ["bash", "-cl", "./app-run.sh; ./app-run2.sh"]