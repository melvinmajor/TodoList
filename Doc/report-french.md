# Rapport du projet TodoList

## Groupe 30
Notre groupe est consitué de 3 personnes :
- Melvin Campos Casares (_**@melvinmajor**_)
- Maxime Liber (_**@LiberTMx**_)
- Hubert Van De Walle (_**@HE201496**_)

## GitHub

__Lien du repository :__ https://github.com/melvinmajor/TodoList
__Lien du Wiki :__ https://github.com/melvinmajor/TodoList/wiki

## Cahier des charges - descriptif

### Quel est notre projet ?

Notre projet est une liste de tâches à faire.

L'intérêt de réaliser ce projet est qu'il peut être utilisé au quotidien pour divers tâches/choses dans divers domaines : les cours, les projets personnels comme professionnels, etc.

Cette première analyse nous à par ailleurs donné l'idée d'implémenter des catégories non spécifiées afin de laisser l'utilisateur de les créer.

Comme demandé, ce projet fonctionnera sous le modèle MVC. Nous aurons un affichage console et un affichage graphique qui sera "user-friendly".
Nous travaillerons également sous l'aspect client-serveur-socket.
L'interface graphique sera écrite sous JavaFX ou Swing.

Notre contrainte est que la todolist devra fonctionner sous Java 10 au lieu de Java 8 (celui vu au cours pratique) et que nous créerons des tests unitaires JUnit 5.


### Ce que notre projet doit absolument faire :

La première chose qu'il doit pouvoir faire est de créer de nouvelles tâches, de pouvoir les compléter ainsi que de les supprimer.
En d'autres termes, il faut qu'il puisse gérer les différentes tâches et les enregistrer localement.

### Ce que notre projet serait capable de faire :

- Une partie du contenu des tâches pourra être modifié par la suite par l'utilisateur (tout sauf la date de création),
- Trier par importance, date, etc.
- Filtrer par utilisateur, catégorie ou période spécifique de temps.

### Quelques idées d'implémentations :

Parce que nous souhaitons avoir quelque chose de réellement complet, nous pourrions implémenter certaines des idées suivantes :

- Une nouvelle variable qui stockera s'il s'agit d'un travail de groupe ou non,
- D'autres options de tri ou filtre,
- Une barre de progression pour des éléments précis comme la date d'échéance ou l'importance,
- Une API JSON pour l'intégration du programme dans divers applications par la suite (site internet, etc.).

## Diagramme UML

Ce diagramme UML est celui du modèle MVC (partie modèle uniquement).

<p align="center"><img src ="https://github.com/melvinmajor/TodoList/blob/uml/uml-model.png" /></p>

## Choix d'implémentation effectués

## Difficultés rencontrées

### Utilisation de 2 IDE différents avec __Maven__

L'utilisation de deux IDE différents n'était peut-être pas la meilleure option en soit mais nous y avons trouvé un certain intérêt.
[IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) est un IDE très performant et bien meilleur qu'[Eclipse](https://www.eclipse.org).

Etonnamment, notre gestionnaire de dépendance [*"Maven"*](https://maven.apache.org/) fonctionnait bien mieux et de façon bien plus transparente avec IntelliJ qu'Eclipse.
Maven est pourtant conçu par les développeurs derrière Eclipse.

Il ne fut pas rare de devoir reconstruire notre workspace (lors de la création du serveur et puis de l'interface graphique) ou de devoir effectuer un refresh du workspace à la moindre synchronisation avec Git.

### Java 10 et 11

L'utilisation d'une version plus récente de Java n'a pas été chose facile.
En effet, Oracle ayant changé les conditions d'utilisations de Java, il nous a fallu suivre [OpenJDK](https://openjdk.java.net/) pour récupérer un Java Development Kit (JDK) pouvant être librement utilisé.

Grâce à cela, nous avons profité d'un JDK avec Java Runtime Environment (JRE) intégré, ce qui nous à évité d'autres contraintes d'implémentation avec Eclipse.

### JavaFX ou Swing ?

Au départ, nous voulions créer l'interface grpahique en JavaFX et non Swing.
Cette décision était prise étant donné les problèmes majeurs lié aux DPI des écrans (Windows et Linux impacté contrairement à macOS), entre autres choses.

Malheureusement, on a très vite remarqué un manque de temps crucial et nous avons préféré utiliser Swing avec Window Builder comme point de départ.
Cela à occasionné un code très lourd et difficile à séparer, ce qui nous a permis de comprendre l'intérêt minime des systèmes WYSIWYG (What You See Is What You Get).

## Pistes d'amélioration éventuelles

### Localizer

Une détection de la langue de l'environnement dans lequel le programme fonctionne pour automatiquement s'adapter en fonction de sa base de données et à défaut, être disponible en anglais serait une amélioration envisageable à moyen terme.

Il faudra réadapter une partie du programme pour que cela soit possible et de manière aisée.

## Conclusion

### Melvin Campos Casares

### Maxime Liber

### Hubert Van De Walle
