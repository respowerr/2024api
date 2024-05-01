# Utilisez une image de base avec Java préinstallé
FROM openjdk:21

# Copiez l'exécutable du projet dans le conteneur
COPY ./build/libs/*.jar /usr/app/

# Définissez le répertoire de travail
WORKDIR /usr/app

# Exposez le port sur lequel votre application fonctionne
EXPOSE 8080

# Commande pour démarrer votre application
CMD ["java", "-jar", "account-0.0.1.jar"]