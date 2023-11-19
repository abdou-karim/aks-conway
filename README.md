# Frontend Angular - Game of Life

Le frontend Angular pour le Game of Life est une interface utilisateur permettant d'interagir avec le backend 
et de visualiser le célèbre automate cellulaire inventé par John Conway.

## Table des matières

1. [Introduction](#introduction)
2. [Fonctionnalités](#fonctionnalités)
3. [Prérequis](#prérequis)
4. [Installation](#installation)
5. [Configuration](#configuration)
6. [Utilisation](#utilisation)

## Introduction

Bienvenue dans le frontend Angular du Game of Life, une interface conviviale pour interagir avec l'API backend 
et explorer le monde fascinant de l'automate cellulaire. Le frontend fournit des fonctionnalités pour démarrer,
arrêter, enregistrer et charger des configurations de jeux,ainsi que 
la possibilité de basculer entre les thèmes Dark et Light.

## Fonctionnalités

1. Visualisation de la grille : Affiche la grille de jeu avec des cellules pouvant être vivantes ou mortes.
2. Contrôle de la vitesse : Permet de régler la vitesse de mise à jour de la génération.
3. Démarrage et arrêt du jeu : Démarre et arrête le processus de mise à jour automatique de la génération.
4. Sélection de cellules : Permet de sélectionner ou désélectionner des cellules spécifiques sur la grille.
5. Enregistrement de motifs : Permet d'enregistrer des configurations de jeux pour une utilisation ultérieure.
6. Chargement de jeux existants : Permet de charger des configurations de jeux existantes depuis le backend.
7. Thèmes Dark et Light : Permet de basculer entre les thèmes Dark et Light pour une expérience utilisateur personnalisée.

## Prérequis
- Node.js : Version v18.10.0
- npm : Version 8.19.2
- Angular CLI : Version 16.2.10
## Installation
- Commencer par cloner le projet
```bash 
git clone -b Frontend https://github.com/abdou-karim/aks-conway.git 
```
- Installer les dépendances
```bash 
npm install 
```

## Configuration
Assurez-vous que le backend Game of Life est en cours d'exécution et accessible depuis le frontend.
Modifiez le fichier src/environments/environment.ts avec l'URL appropriée du backend.

## Utilisation
- Démarrer l'application Angular
- Accéder à l'application dans votre navigateur à l'adresse http://localhost:4200
- Explorez les fonctionnalités pour interagir avec le Game of Life.

## Thèmes
Le frontend offre la possibilité de basculer entre les thèmes Dark et Light.
Utilisez cette fonctionnalité pour personnaliser votre expérience utilisateur en fonction de vos préférences.
