Guillou Ronan Tabata : readme

Projet bas� sur : 
Tutoriel Google Developers Room With A View 
LiveData recuperation : Articles & code examples on how to use properly the mvvm & fragments (Rakay, Hitesh Sahu entre autres)
Countdown Timer : Class example de Francis Brunet Manquat 
Vibrator & other problems : StackOverflow Q&A

De fait les commentaires sont en anglais et en fran�ais de mani�re un peu hasardeuse, surtout que j'ai plut�t le r�flexe de coder en anglais 
la plupart du temps �tant donn� que 95% de la doc est en anglais et que je travaille en Anglais au boulot. D�sol� d'avance ! 

Rappel des attentes fondamentales du projet Tabata timer :

- Besoins minimals :
  + Cr�er/supprimer/lister des s�ances d'entrainement :
	- La page d'accueil de l'application est la page de liste de vos s�ances.
	- Un click sur le bouton bleu permet de cr�er une nouvelle s�ance , un slide left d'une s�quence de la supprimer.
	- L'id d'une s�ance est la date de cr�ation � la seconde pr�s, ce qui garantit a priori son unicit�.
	- La couleur se choisit � l'aide d'un colorpicker 4 couleurs.
	- Le click sur une s�quence permet de visualiser la s�quence , puis de la lancer en cliquant sur le bouton chronom�tre en haut a droite.

  + G�rer les erreurs possibles provenant de l'utilisateur :

	- La cr�ation ne permet l'entr�e que de chiffres sur les champs chiffr�s. 

  + Lancer une s�ance d�entrainement (compteur avanc�) :

	- Le click sur une s�quence permet de visualiser la s�quence , puis de la lancer en cliquant sur le bouton chronom�tre en haut a droite.
	- Arriv� sur la s�ance correspondante, le timer se lance en cliquant sur le bouton PLAY/PAUSE.

  + Notifier clairement les �tats et les changements d��tats pendant la s�ance d�entrainement (travail, repos, etc.) :

	- Chaque changement d'�tat est associ� � son texte correspondant et le t�l�phone vibre � chacun de ces changements.

  + Proposer une interface utilisateur en mode portrait et paysage (au minimum sur la s�ance d�entrainement) :

	- Le fragment de vue tabata est bien disponible en format paysage. Cela ne m'a pas paru n�cessaire pour le reste de l'appli �tant donn� qu'elle est 

  + Conserver l�application en �tat si coup de t�l�phone ou autres d�rangements :

	- Fonctionnel. (onSave & onRestore) -> ne met pas en pause le timer du tout pour �viter de g�ner le timer en cas de d�tection un peu rapide.
	Le bouton de pause est cependant disponible gr�ce � la vue paysage.

  +++ D'autres besoins ou comp�tences techniques, on peut-�tre �t� n�cessaire pour r�aliser votre projet (bonus) :
	- vibrations impl�ment�es directement dans la classe timer avec le contexte du timer pass� en param�tre 
	(c'�tait un peu compliqu� pour rien mais l'id�e de base c'�tait de g�rer des types de s�ances -sport, -time's up, que j'ai finalement abandonn�e par manque de temps, 
	et la vibration sur diff�rentes vues avait besoin, pour ne pas �tre impl�ment�e 50 fois, de passer le contexte du fragment en param�tre au timer)
	- interface utilisateur en plusieurs Fragments
	- gestion des slide sur item afin de supprimer
	- possibilit� de reset l'activit� en cours en appuyant sur le chronom�tre en haut a droite de la vue tabata 
	(pas super utile mais m'a beaucoup servi pour tester, donc autant le laisser)