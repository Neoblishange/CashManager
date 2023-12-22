# Utilisez l'image officielle de Maven pour la construction
FROM maven:latest

WORKDIR /app

# Copiez les fichiers de construction nécessaires
# COPY Back_Cash_Manager/ .

# Exécutez la construction avec Maven

# Copiez le JAR construit depuis l'étape de construction
# COPY --from=builder /app/target/Back_Cash_Manager-0.0.1-SNAPSHOT.jar /app/Back_Cash_Manager.jar

# Exposez le port sur lequel le serveur fonctionnera
EXPOSE 8080
ENTRYPOINT mvn spring-boot:run
CMD bash -cl ./app-run_copy.sh 
