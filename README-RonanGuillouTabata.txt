Guillou Ronan Tabata : readme

Projet basé sur : 
Tutoriel Google Developers Room With A View 
LiveData recuperation : Articles & code examples on how to use properly the mvvm & fragments (Rakay, Hitesh Sahu entre autres)
Countdown Timer : Class example de Francis Brunet Manquat 
Vibrator & other problems : StackOverflow Q&A

De fait les commentaires sont en anglais et en français de manière un peu hasardeuse, surtout que j'ai plutôt le réflexe de coder en anglais 
la plupart du temps étant donné que 95% de la doc est en anglais et que je travaille en Anglais au boulot. Désolé d'avance ! 

Rappel des attentes fondamentales du projet Tabata timer :

- Besoins minimals :
  + Créer/supprimer/lister des séances d'entrainement :
	- La page d'accueil de l'application est la page de liste de vos séances.
	- Un click sur le bouton bleu permet de créer une nouvelle séance , un slide left d'une séquence de la supprimer.
	- L'id d'une séance est la date de création à la seconde près, ce qui garantit a priori son unicité.
	- La couleur se choisit à l'aide d'un colorpicker 4 couleurs.
	- Le click sur une séquence permet de visualiser la séquence , puis de la lancer en cliquant sur le bouton chronomètre en haut a droite.

  + Gérer les erreurs possibles provenant de l'utilisateur :

	- La création ne permet l'entrée que de chiffres sur les champs chiffrés. 

  + Lancer une séance d’entrainement (compteur avancé) :

	- Le click sur une séquence permet de visualiser la séquence , puis de la lancer en cliquant sur le bouton chronomètre en haut a droite.
	- Arrivé sur la séance correspondante, le timer se lance en cliquant sur le bouton PLAY/PAUSE.

  + Notifier clairement les états et les changements d’états pendant la séance d’entrainement (travail, repos, etc.) :

	- Chaque changement d'état est associé à son texte correspondant et le téléphone vibre à chacun de ces changements.

  + Proposer une interface utilisateur en mode portrait et paysage (au minimum sur la séance d’entrainement) :

	- Le fragment de vue tabata est bien disponible en format paysage. Cela ne m'a pas paru nécessaire pour le reste de l'appli étant donné qu'elle est 

  + Conserver l’application en état si coup de téléphone ou autres dérangements :

	- Fonctionnel. (onSave & onRestore) -> ne met pas en pause le timer du tout pour éviter de gêner le timer en cas de détection un peu rapide.
	Le bouton de pause est cependant disponible grâce à la vue paysage.

  +++ D'autres besoins ou compétences techniques, on peut-être été nécessaire pour réaliser votre projet (bonus) :
	- vibrations implémentées directement dans la classe timer avec le contexte du timer passé en paramètre 
	(c'était un peu compliqué pour rien mais l'idée de base c'était de gérer des types de séances -sport, -time's up, que j'ai finalement abandonnée par manque de temps, 
	et la vibration sur différentes vues avait besoin, pour ne pas être implémentée 50 fois, de passer le contexte du fragment en paramètre au timer)
	- interface utilisateur en plusieurs Fragments
	- gestion des slide sur item afin de supprimer
	- possibilité de reset l'activité en cours en appuyant sur le chronomètre en haut a droite de la vue tabata 
	(pas super utile mais m'a beaucoup servi pour tester, donc autant le laisser)