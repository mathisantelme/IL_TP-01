# IL_TP-01
## Ingénierie Logicielle - TP 01 - Rapport

Disclamer: Tout les morceaux de code sont commentés et la  plupart des réponses sont présente dans ces dis commentaires. J'ai aussi rencontré un autre problème lors de l'installation de SonarQube, je n'ai donc pas pu l'utiliser lors de la réalisation de ce TP et cela m'as fait perdre du temps.

### Exercice 1:

Pour la mise en place d'un quatrième traitement, il a suffit d'ajouter une classe et un case dans ProgrammFactory.

### Exercice 2:

Je n'ai pas compris la question n°4 de cet exercice. Mon architecture est la suivante:

- Une interface pour les parsers de file path
- deux classes implémantant cette interface (une pour chaque OS)
- Une interface pour les compteurs de folders
- deux classes implémantant cette interface
- Une classe factory qui permet de créer un parser ou un counter indépendament de l'os
- deux classe qui permet de créer les parsers et counter en fonction de l'OS correspondant.

Je n'ai donc pas compris à quoi servirait la nouvelle "factory" que l'on nous demande d'implémenter dans la question 4 et n'ai donc pas terminé cet exercice

### Exercice 3:

Cet exercice à été relativement simple. En effet il suffisait de créer un constructeur privé pour la classe Airport, une variable statique qui contient une instance de Airport (créée à la compilation) et une méthode publique qui retourne le poiteur de l'instance. De ce fait, seul la classe airport créé sa propre instance et personne d'autre. Le fait de ne pas faire de lazy-loading dans airport permet de rentre cette classe Thread Proof.

### Exercice 4:

Je n'ai réussi qu'à faire la première question de cet exercice, étant donné que je n'avais plus de temps pour écrire ce rapport ou développer la solution.