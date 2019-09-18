package Singleton;

class Airport {
    
    // on passe le constructeur en privé afin que seul la classe airport puisse controler la création d'instance de Airport
    private Airport () {
        // on simule le temps de création de l'airport
        try {
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    /*  On a une seule instance de Airport stockée en privée en tant que variable de classe
        Cela permet aussi de le rendre Threadproof, puisque l'instance n'est créée qu'une seule fois lors de la compilation
        Aucune des classes avions ne peut demander la création d'un airport, seulement le pointeur     
        Si on avait voulu rendre ce code non threadproof, il aurait fallut faire du lazy-loading et créer l'instance de la classe
        dans le getter
    */
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