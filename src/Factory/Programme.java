package Factory;

// l'interface Programme qui permet au Client d'appeler les méthodes communes à chaque programme de traitement
public interface Programme {
    public void go();
}

// le programme de traitement 1 (on implémente l'interface Programme afin de pouvoir appeler la méthode go sans distinction)
class Programme1 implements Programme {
    public Programme1 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 1");
    }
}

// le programme de traitement 2 (on implémente l'interface Programme afin de pouvoir appeler la méthode go sans distinction)
class Programme2 implements Programme {
    public Programme2 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 2");
    }
}

// le programme de traitement 3 (on implémente l'interface Programme afin de pouvoir appeler la méthode go sans distinction)
class Programme3 implements Programme {
    public Programme3 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 3");
    }
}

// le programme de traitement 4
// on copie colle le code de Programme3 en modifiant ce qui à besoin de l'etre
class Programme4 implements Programme {
    public Programme4 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 4");
    }
}

// classe qui permet la création des objets de traitement
class ProgramFactory {
    public Programme createProgramme (int n) {
        // en fonction de la valeur de l'entier on initialise  le traitement correspondant et on le retourne
        switch (n) {
            case 1:
                return new Programme1();

            case 2:
                return new Programme2();

            case 3:
                return new Programme3();

            // il suffit d'ajouter un case pour créer un nouvel objet
            case 4:
                return new Programme4();
        
            default:
                // si le numéro n'est pas correct alors on affiche un message
                System.out.println("Wrong arguement. Please use 1, 2 or 3");
                return null;
        }
    }
}

// la classe client qui appelle les différents programmes de traitement
class Client {
    public static void main (String[] args) {
        try {
            // on initialise un object factory qui nous permet de créer les traitements
            ProgramFactory prgFactory = new ProgramFactory();

            System.out.println("Je suis le traitement 1");

            // on appelle la méthode go directement depuis le traitement retourné par la fonction de création de traitement de la factory (si l'objet retourné est null alors on affiche un message d'erreur grace au try catch)
            prgFactory.createProgramme(Integer.valueOf(args[0]).intValue()).go();

        // si on a pas récupéré un nombre entier alors on quitte le programme avec l'exception
        // si le nombre d'arguements passé n'est pas correct alors on quitte avec un message d'erreur
        } catch (java.lang.NumberFormatException | java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e) {
            System.out.println(e.toString());
        }
    }
}