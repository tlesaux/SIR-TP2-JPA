# TP 5 Servlet

- Lancer un conteneur mySQL et phpMyAdmin comme pour le TP 2-4  
  (attention le modèle de données est différent du TP 2-4 donc il faut changer de conteneur docker)

- Lancer la classe JpaTest afin de remplir la bdd avec quelques données
- Ici on utilise le plugin Lombok pour simplifier le code des classes modèle

Le fichier index.html contient le contenu de la page princiale du servlet, il contient un bouton redirigeant vers la
liste des ticket en appelant la méthode GET de la classe TicketInfo

On peut alors visualiser l'ensemble des tickets en bdd, de plus on peur accéder à un formulaire de création de ticket en appelant la méthode get de TicketForm

On peut alors créer un nouveau ticket en ajoutant un nom au formulaire en utilisant la méthode post de TicketForm (problème lors d'un formulaire avec plusieurs input -> seul le premier input renvoyait une valeur)  

On a alors un message de succès avec un bouton de redirection vers la liste des tickets