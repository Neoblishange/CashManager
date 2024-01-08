#!/bin/bash

# Se déplacer dans le répertoire du projet
cd /app/Back_Cash_Manager

# Afficher la version de Maven (facultatif, pour déboguer)
mvn --version

# Exécuter la construction Maven
mvn clean install

java -jar /app/target/Back_Cash_Manager-0.0.1-SNAPSHOT.jar
