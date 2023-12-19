# Étape de construction
FROM openjdk:11 AS builder

WORKDIR /app

# Copiez les fichiers nécessaires pour installer les dépendances
# COPY CashManagerFront/build.gradle CashManagerFront/settings.gradle ./
# COPY CashManagerFront/gradle ./gradle
# COPY CashManagerFront/app/build.gradle CashManagerFront/app/settings.gradle ./CashManagerFront/app/

# Téléchargez les dépendances
RUN ./gradlew downloadDependencies

# Copiez le reste des fichiers de l'application
# COPY CashManagerFront/ .

# Construisez l'APK
RUN ./gradlew clean assembleDebug

# Copiez l'APK construit depuis l'étape de construction vers le répertoire /app/public
COPY --from=builder /app/CashManagerFront/app/build/outputs/apk/debug/app-debug.apk /app/public/client.apk

CMD ["sh", "-c", "echo 'APK built and copied'"]
