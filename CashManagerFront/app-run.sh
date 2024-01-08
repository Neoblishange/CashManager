#!/bin/bash
apt-get update && apt-get install -y dos2unix
find . -type f -exec dos2unix {} \;

# Se déplacer dans le répertoire du projet
# Télécharger les dépendances
./gradlew downloadDependencies

# Nettoyer le projet et générer l'APK
./gradlew clean assembleDebug

# Afficher un message
echo 'APK built and copied'