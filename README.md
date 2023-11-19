# API Game of Life

L'API Game of Life est une interface permettant de gérer le jeu de la vie, un automate cellulaire inventé par John
Conway. Cet automate se compose d'une grille bidimensionnelle de cellules, chacune pouvant être dans un état vivant ou
mort, en fonction de règles simples.

## Le projet a deux branches, BACKEND ET FRONTEND ce branche est pour le BACKEND
## Table des matières

1. [Introduction](#introduction)
2. [Fonctionnalités](#fonctionnalités)
3. [Prérequis](#prérequis)
4. [Installation](#installation)
5. [Configuration](#configuration)
6. [Endpoints](#endpoints)


## Introduction

Bienvenue dans l'API Game of Life, une interface robuste pour interagir avec le célèbre automate cellulaire inventé par
John Conway. Le Game of Life est un jeu fascinant qui évolue au fil des générations en fonction de règles simples,
créant des motifs complexes à partir d'états de cellules initiaux.

## Fonctionnalités

1. Initialisation de la grille : Permet d'initialiser une grille de jeu avec une taille donnée.
2. Mise à jour de la génération : Met à jour la génération actuelle du jeu en suivant les règles du jeu de la vie.
3. Sélection de cellules : Permet de sélectionner ou désélectionner des cellules spécifiques sur la grille.
4. Chargement de jeux existants : Permet de charger des configurations de jeux existantes à partir de la base de
   données.
5. Sauvegarde de jeux : Permet de sauvegarder des configurations de jeux actuelles dans la base de données pour un usage
   ultérieur.

## Prérequis

- L'API utilise Docker et Docker-Compose pour gérer la base de données MongoDB, une base de données NoSQL.
- Spring Boot offre une structure modulaire et des fonctionnalités avancées pour le développement.

- Docker : Version 24.0.7
- Docker-compose : Version 1.29.2
- Java Development Kit (JDK) : 17
- Spring Boot : Version 3.1.5

## Installation
- Commencer par cloner le projet
```bash 
git clone -b Backend https://github.com/abdou-karim/aks-conway.git 
```

## Configuration

- Pour demarrer la base de donnée :
```bash
   docker compose up mongodb -d
 ```
- Pour demarrer le projet : 
```bash
 ./mvnw spring-boot:run 
```


## Endpoints

- GET /api/v1/game/initializeGrid : Initialiser une nouvelle grille de jeu avec la taille spécifiée
- PUT /api/v1/game/update/grid : Mettre à jour l'état de la grille de jeu
- GET /api/v1/game/updateGeneration/{gameName} : permet de recuperer la generation d'une partie 
- POST /api/v1/game/save : Enregistrer un jeu spécifique.
- GET /api/v1/game/load : recupere tous les jeux disponible
- GET /api/v1/game/name/{gameName} : recupere un jeu specifique

## L'API sera disponible à l'adresse http://localhost:9000

