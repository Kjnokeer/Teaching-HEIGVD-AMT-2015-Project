# Project Report


## Administrative information

### Team

- Dolt Mathias | Kjnokeer
- Duchoud Thibaud | Manamiz
- Ferreira Mario | UnsafeDriving
- Moret Jérôme | jermoret



## Introduction

Notre application a été développée à l’aide de la spécification Java EE. Dans notre cas, nous avons implémenté cette spécification grâce au serveur d’applications Glassfish d’Oracle. 
La persistance des données est gérée par JPA qui se connecte à une base de données MySQL à travers une source de données.


## User Guide
### Configuration

Pour pouvoir exécuter notre application, vous avez besoin de configurer :


- La base de données MySQL

Il vous suffit de créer une base de données portant le nom `moussaraser`.
Notre application tentera d’accéder à la base de données à travers l’identifiant 
`root` accompagné du mot de passe `pass`.

- Un pool de connexion JDBC

Pour cela, le plus simple est d’utiliser la console d’administration graphique atteignable au travers de l’adresse suivante : `http://<adresse_ip_serveur_glassfish>:4848/`.

Vous pourrez ensuite dans l’onglet des ressources, puis dans celui de JDBC, créer le pool de connexion dont nos propriétés figurent sur l’image ci-dessous.

![Pool de connexion JDBC](img/poolProperties.png)

Il est important que le nom du pool soit bien le suivant : **moussaRaserPool**.


- Une ressource JDBC

Dans le même menu que pour le pool de connexion JDBC, créez une ressource JDBC liée au pool précédemment créé et portant le nom suivant : **jdbc/moussaRaserDS**.


- D’un IDE supportant la spécification Java EE ainsi que Glassfish

Nous avons utilisé l’IDE NetBeans qui réalise de manière remarquable cette tâche. Si vous utilisez NetBeans, il vous suffit d’importer le projet maven présent dans `projet/MoussaRaser`. Effectuez ensuite un `Clean and Build`. Enfin vous pourrez effectuer un `Run` qui se chargera de déployer automatiquement l’application sur Glassfish.

Vous pouvez, dès lors, accéder à l’application sur `http://<adresse_ip_serveur_glassfish>:8080/MoussaRaser/`.
### Utilisation

#### Website pages
##### Home page
![](img/homePage.png)

Vous pouvez ici soit vous loggez au sein de l'application ou alors créer un compte si vous n'en possédez pas.
##### Registration page
![](img/registrationPage.png)
##### My apps page
![](img/appsPage.png)

Cette page donne un resumé de vos applications, il est possible de modifier les propriétés d'une application à l'aide du bouton `edit`. 

Vous pouvez également enregister une nouvelle application à l'aide du bouton `Register new app`. 
###### Register new app page
![](img/registerNewApp.png)
###### Edit app page
![](img/editAppPage.png)
##### My account page
![](img/accountPage.png)

#### JUnit
Pour les tests unitaires il faut tout d'abord il faut lancer le serveur web.

Ensuite, ouvrir le projet "MoussaRaserTests" dans NetBeans et faire un "Clean and Build".

Pour finir, il faut simplement appuyer sur la touche "F6" (Tests).

#### JMeter
Dans le cas des tests de charge il faut simplement ouvrir les fichier "MoussaRaser Pool.jmx" et appuyer sur le bouton "Play".


## Design

### System overview
### Gamification features
### User interface
### REST API
### Design patterns


## Implementation

### Package structure
### Selected aspects


## Testing and validation
### Tools

### Procedures


### Results



## Known Issues

## Conclusion

## Appending A: Auto Evaluation

