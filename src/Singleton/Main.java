package Singleton;

class Airport {
    boolean piste_libre = false;
    
    // on passe le constructeur en privé afin que seul la classe airport puisse controler la création d'instance de Airport
    private Airport () {
        this.piste_libre = true;
    }

    // On a une seule instance de Airport stockée en privée en tant que variable de classe
    private static Airport INSTANCE = new Airport();

    // cette méthode publique permet de partager le pointeur de classe de airport aux utilisateur (avions par example)
    public static Airport getAirport () {
        return INSTANCE;
    }
}

class Avion extends Thread {
    String  name;
    Airport airport;

    public Avion (String str) {
        this.name = str;
    }

    @Override
    public void run() {
        this.airport = Airport.getAirport();
        System.out.println("I am plane " + this.name + " on airport " +  this.airport);
    }
}

// Main =========================================
public class Main {
    public static void main(String[] args) {
        // on  créé des avions
        Avion a1 = new Avion("avion 1");
        Avion a2 = new Avion("avion 2");
        Avion a3 = new Avion("avion 3");
        Avion a4 = new Avion("avion 4");

        // on lance les threads de chaque avion
        // on utilise la méthode start de la classe Thread de laquelle hérité Avion afin de lancer les avions en parrallèle
        a1.start();
        a2.start();
        a3.start();
        a4.start();
    }
}