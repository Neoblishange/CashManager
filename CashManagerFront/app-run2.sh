#!/bin/bash

# Répertoire du projet
PROJECT_DIR="/app/CashManagerFront"

apt-get update && apt-get install -y dos2unix
dos2unix app-run.sh

# Se déplacer dans le répertoire du projet
cd "$PROJECT_DIR"

# Télécharger les dépendances
./gradlew downloadDependencies

# Nettoyer le projet et générer l'APK
./gradlew clean assembleDebug

# Afficher un message
echo 'APK built and copied'

