# projet_generateur

1)présentation de la structure 

1.1	Package generator
Ce package contients  les classes permettant  de generer du code :
-la class AbstractRepository , permet de genérer les lots d'instance
-la classe abstraite Visitor  permettant d'implementer le  pattern visitor
-la classe JavaTypeNameVisitor permettant de construire la chaine de caractere correspondant au type de la valeur recherchée
elle comporte :
	-un attribut result corespondant  a la chaine de caractere necessaiare a l'initialisation de la variable
	-un attribut constructor  retournant la chaine  necessaire a l'initialisation d'un attribut via constructeur
	- elle prend egalement l'instance de la classe de configuration instancier lors du parsage du fichier xml de primitive
-la classe  abstraite LangageVisitor héritant de Visitor definition les variales commmune a la generation des différents langage
-la classe JavaVisitor permettant de generer le code correspondant au xml passer en parametre elle pred en paramettre 
la classe model obtenu a partir  du xml modele et la classe paramettre obtenu a partir du xml de parametrage des valeurs primitives
 


1.2	Package main
Le package main contient les class permettant de lancer la génération du code et de le tester
-la class Generateur permettant de generer les classe java  il faut lui fournir  le nom des xml de configuration et de minispec
-les classes de test  permettant de tester les différentes specifications du  projet


1.3	Package modelMiniSpec
il contiens les différentes classe permettant de modeliser  le fichier xml de minispec

1.4	Package modelParameter
il contiens les différentes classe permettant de modeliser  le fichier xml de configuration

1.5	Package parser
il contiens  les classe permettant de parser les fichiers xml:
-la class MiniSpecParser permettant de parser le fichier xml de minispec vers une class du model de type MsModel
-la class Param parser permettant de parser le fichier xml de configuation vers une class PrmConfig


1.6	Package xmlExamples
il contient les différent fichier xml 
-import.xml correspondant au fichier de configuration
 les fichiers xml de test contenant les minispecs permettant de tester les specification du projet



