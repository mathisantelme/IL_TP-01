// la classe abstraite Programme qui permet au Client d'appeler les méthodes communes à chaque programme de traitement
abstract class Programme {
    abstract void go();
}

// le programme de traitement 1 (on hérite de la classe Programme afin de pouvoir appeler la méthode go sans distinction)
class Programme1 extends Programme {
    public Programme1 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 1");
    }
}

// le programme de traitement 2 (on hérite de la classe Programme afin de pouvoir appeler la méthode go sans distinction)
class Programme2 extends Programme {
    public Programme2 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 2");
    }
}

// le programme de traitement 3 (on hérite de la classe Programme afin de pouvoir appeler la méthode go sans distinction)
class Programme3 extends Programme {
    public Programme3 () {} // le constructeur ne sert à rien pour le moment

    public void go () {
        System.out.println("Je suis le traitement 3");
    }
}

// la classe client qui appelle les différents programmes de traitement
class Client {
    public static void main (String[] args) {

        Programme prg = null; // on initialise un programme par défaut qui est null

        // on vérifie que l'on récupère bien un entier
        try {

            // en fonction de la valeur de l'entier on initialise  le traitement correspondant
            switch (Integer.valueOf(args[0]).intValue()) {
                case 1:
                    prg = new Programme1();
                    break;
    
                case 2:
                    prg = new Programme2();
                    break;
    
                case 3:
                    prg = new Programme3();
                    break;
            
                default:
                    // si le numéro n'est pas correct alors on affiche un message
                    System.out.println("Wrong arguement. Please use 1, 2 or 3");
                    break;
            }
    
            // si le programme n'est pas null alors on appelle la méthode du traitement correspondant
            if (prg != null) {
                System.out.println("Je suis le traitement 1");
                prg.go();
            }

        // si on a pas récupéré un nombre entier alors on quitte le programme avec l'exception
        // si le nombre d'arguements passé n'est pas correct alors on quitte avec un message d'erreur
        } catch (java.lang.NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong arguement type");
            System.out.println(e.toString());
        }
    }
}