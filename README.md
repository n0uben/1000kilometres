# 1000kilometres

Pour pouvoir faire tourner le projet Back :
- créer un fichier "application.properties" dans le dossier "Back/src/main/resources"
- renseigner les informations suivantes dans le fichier : 

```
spring.datasource.url=jdbc:mysql://adresse_de_votre_bdd/nom_de_votre_bdd
spring.datasource.username=votre_username
spring.datasource.password=votre_mot_de_passe

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```
