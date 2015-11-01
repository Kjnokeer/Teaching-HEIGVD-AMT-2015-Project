# Project Report


## Administrative information

### Team

- Dolt Mathias | Kjnokeer
- Duchoud Thibaud | Manamiz
- Ferreira Mario | UnsafeDriving
- Moret Jérôme | jermoret

### Tasks realized by the different team members



## Introduction

## User Guide

### How to execute and access the application
### How to use the application
### How to update, build and deploy the application
### How to run the automated test procedure
#### JUnit ####
Pour les tests unitaires il faut tout d'abord il faut lancer le serveur web.

Ensuite, ouvrir le projet "MoussaRaserTests" dans NetBeans et faire un "Clean and Build".

Pour finir, il faut simplement appuyer sur la touche "F6" (Tests).

#### JMeter ####
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

### Test strategy
### Tools
Pour les tests unitaires, tester le bon fonctionnement de l'application, nous avons utilisé JUnit (Selenium).
Pour les tests de charge, nous avons utilisé Jmeter.
### Procedures
Pour la création des tests unitaires avec JUnit, nous avons créé des classes pour gérer chaque page, ensuite nous avons utilisé ces classes pour réaliser les tests. Nous avons testé les fonctionnalités existantes:
- Création de compte.
- Connexion.
- Créer une application.
- Modifier une application.
- Modification d'un compte.
- Déconnexion.
- Afficher liste d'utilisateurs d'une application.

Pour la création des tests de charge avec JMeter, nous nous sommes inspirés de l'exemple vu en classe avec MVCDemo. Deux tests ont été réalisés, dans le premier, le nombre d'utilisateurs en simultané grandit petit à petit, tandis que dans le deuxième, un grand nombre d'utilisateurs se connecte en même temps depuis le début.


### Results

Dans cette section du rapport nous allons vous parler des tests unitaires réalisés avec Selenium.

#### Connexion ####
**Identifiants corrects**

Lors de la connexion avec des identifiants corrects, l'utilisateur devrait être redirigé vers la page d’accueil.

Résultat : 

**Identifiants incorrects**

Lors de la connexion avec des identifiants incorrects, l'utilisateur devrait rester sur la même page, page de connexion.

Résultat : 

#### Création d'un nouveau compte ####
**Données correctes**

Lors de la création d’un nouveau compte avec toutes les informations nécessaires correctes, l’utilisateur devrait être redirigé vers la page de connexion.

Résultat : 

**Mots de passe différents**

Lors de la création d’un nouveau compte, si le mot de passe et sa confirmation sont différents, l’utilisateur devrait rester sur la même page, page d’enregistrement.

Résultat :

**Mot de passe manquant**

Lors de la création d’un nouveau compte, si le mot de passe manque, l’utilisateur devrait rester sur la même page, page d’enregistrement.

Résultat :

**Prénom manquant**

Lors de la création d’un nouveau compte, si le prénom manque, l’utilisateur devrait rester sur la même page, page d’enregistrement.

Résultat :

**Nom manquant**

Lors de la création d’un nouveau compte, si le nom manque, l’utilisateur devrait rester sur la même page, page d’enregistrement.

Résultat :

**Adresse mail manquante**

Lors de la création d’un nouveau compte, si l’adresse mail manque, l’utilisateur devrait rester sur la même page, page d’enregistrement.

Résultat :

**Utilisateur déjà existant**

Lors de la création d’un nouveau compte, si l’utilisateur existe déjà (adresse mail), l’utilisateur devrait rester sur la même page, page d’enregistrement.

Résultat :

#### Modification d'un compte ####
**Données correctes**

Lors de la modification d’un compte avec toutes les informations nécessaires correctes, l’utilisateur devrait être redirigé vers la page d’accueil.

Résultat :

**Mots de passe différents**
Lors de la modification d’un compte, si le mot de passe et sa confirmation sont différents, l’utilisateur devrait rester sur la même page, page de modification de compte.

Résultat :

**Mot de passe manquant**
Lors de la modification d’un compte, si le mot de passe manque, l’utilisateur devrait rester sur la même page, page de modification de compte.

Résultat :

**Prénom manquant**

Lors de la modification d’un compte, si le prénom manque, l’utilisateur devrait rester sur la même page, page de modification de compte.

Résultat :

**Nom manquant**

Lors de la modification d’un compte, si le nom manque, l’utilisateur devrait rester sur la même page, page de modification de compte.

Résultat :

#### Navigation dans l'accueil ####
**Logout**

Lors du clic sur « Logout », l’utilisateur devrait se déconnecter et être redirigé vers la page de connexion. 

Résultat :

**Liste d'utilisateurs**

Lors du clic sur le nombre d’utilisateurs d’une application, l’utilisateur devrait être redirigé vers la page avec la liste de nombre d’utilisateurs de l’application. 

Résultat :

#### Création d'une application ####
**Données correctes**

Lors de la création d’une application avec toutes les informations correctes, l’utilisateur devrait être redirigé vers la page d’accueil.

Résultat :

**Nom manquant**

Lors de la création d’une application avec le nom manquant, l’utilisateur devrait rester sur la même page, page de création d’une application.

Résultat :

#### Modification d'une application ####
**Données correctes**

Lors de la modification d’une application avec toutes les informations correctes, l’utilisateur devrait être redirigé vers la page d’accueil.

Résultat :

**Nom manquant**

Lors de la modification d’une application avec le nom manquant, l’utilisateur devrait rester sur la même page, page de modification d’une application.

Résultat :


#### JMeter ####
Dans cette section du rapport nous allons vous parler des tests de surcharge réalisés avec JMeter.

Dans cette phase du projet nous avons seulement réalisé des tests de surcharge. Plusieurs personnes qui se connectent en même temps et qui chargent plusieurs fois la page d'accueil.


Voici les résultats obtenus dans le cas où le nombre d'utilisateurs en simultané augmente doucement:

Nombre d'utilisateurs en simultané (threads) :

![Number of threads](img/jmeter_nb_users_1.png)

Temps d'attente (ms) :

![Waiting time](img/jmeter_pool_1.png)

Comme vous pouvez le constater, les temps de réponse restent corrects même avec une grande quantité d'utilisateurs qui se connectent en même temps. Nous avons un pique au moment où il y a le plus grand nombre d'utilisateurs connectés. (~50 msec)

Les différentes requêtes possèdent le même temps de réaction.

Voici les résultats obtenus dans le cas où le nombre d'utilisateurs en simultané augmente assez rapidement:

Nombre d'utilisateurs en simultané (threads) :

![Number of threads](img/jmeter_nb_users_2.png)

Temps d'attente (ms) :

![Waiting time](img/jmeter_pool_2.png)

Dans ce cas, le temps de réponse reste correcte, même si un peu plus élevé (~120 msec) rester correct, notre système réponds correctement à une grande quantité de requêtes. 

Par contre, nous constatons un pique (~540 msec) lors de l'affichage de la page d'accueil après la connexion de l'utilisateur (~00:12:00). Ce pique arrive au moment d'une surcharge constante. Après plusieurs tests, ce pique est régulier, mais nous n'avons pas trouvé la cause. 



## Known Issues

## Conclusion

## Appending A: Auto Evaluation

