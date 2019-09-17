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
        // on appelle le premier programme
        Programme prg = new Programme1();
        System.out.println("Je suis le main 1");
        prg.go();

        // puis le second
        prg = new Programme2();
        System.out.println("Je suis le main 1");
        prg.go();

        // puis le dernier
        prg = new Programme3();
        System.out.println("Je suis le main 1");
        prg.go();
    }
}