package State;

import java.util.Scanner;

class Carte {
    private String code; // le code secret de la carte

    // on créé la carte avec le code secret
    public Carte (String p_code) {
        this.code = p_code;
    }

    // cette méthode retourne le résultat de la comparaison du code secret de la carte et le code passé en paramètre
    public boolean isCodeValid (String p_code) {
        return p_code.equals(this.code);
    }
}

class Distributeur {
    // les différents états du distributeur
    public static final int STATE_ATTENTE_CARTE = 0;    
    public static final int STATE_ATTENTE_CODE = 1;
    public static final int STATE_ATTENTE_OPERATION = 2;
    public static final int STATE_DONE = 3;


    private int currentState;
    private Carte carte;
    private Scanner sc = new Scanner(System.in);

    public Distributeur () {
        this.currentState = Distributeur.STATE_ATTENTE_CARTE;
    }

    public void insererCarte (Carte c) {
        // tant que le distributeur n'as pas terminé la transaction, alors on boucle
        while (this.currentState != Distributeur.STATE_DONE) {
            switch (this.currentState) {
                // si le distributeur est en attente de carte
                case STATE_ATTENTE_CARTE:
                    // on met à jour la carte
                    this.carte = c;
                    System.out.println("> Carte insérée");
                    this.currentState = Distributeur.STATE_ATTENTE_CODE;
                    break;
            
                // si le distributeur attend le code de la carte
                case STATE_ATTENTE_CODE:
                    int tries = 3; // in définit un nombre d'essai pour rentrer son code
                    String temp_code;

                    // on laisse trois chances a l'utilisateur pour entrer son code
                    while (this.currentState != STATE_ATTENTE_OPERATION && tries != 0){
                        System.out.println("> Veuillez saisir votre code [essais: " + tries + "]: ");
                        
                        // si le code est valide alors on passe à la suite
                        if (this.carte.isCodeValid(sc.nextLine().trim()))
                            this.currentState = Distributeur.STATE_ATTENTE_OPERATION;
                        // si le code est erroné, alors on enlève un essai
                        else 
                            tries--;
                    }

                    // si le code est erroné 3 fois alors on avale la carte et on quitte le programme
                    if (tries == 0) {
                        System.out.println("> Carte avalée");
                        System.exit(1);
                    }
                    break;

                // si le code est bon alors le distributeur est en attente d'opération
                case Distributeur.STATE_ATTENTE_OPERATION:
                    System.out.println("> Veuillez choisir une opération: \n> [1] retrait carte \n> [2] retrait espèce");

                    // on récupère la valeur entrée par l'utilisateur
                    switch (sc.nextLine().trim()) {
                        // si l'utilisateur veux retirer sa carte alors on la lui redonne et on quitte le programme
                        case "1":
                            System.out.println("> Veuillez récupérer votre carte");
                            this.currentState = Distributeur.STATE_DONE;
                            break;  

                        // si l'utilisateur veux des retirer de l'espece alors on lui donne 50e (c'est arbitraire pour le moment)
                        case "2":
                            System.out.println("> Veuillez récupérer vos 50e et votre carte");
                            this.currentState = Distributeur.STATE_DONE;
                            break;  
                            
                        // si la valeur n'est pas comprise alors on redonne la carte et on repasse à l'état d'attente de carte
                        default:
                            System.out.println("> Option invalide, veuillez récupérer votre carte");
                            this.currentState = Distributeur.STATE_ATTENTE_CARTE;
                            break;
                    }

                    break;
            }
        }
    }

}

// Main =====================================
public class Main {
    public static void main(String[] args) {
        Distributeur dis = new Distributeur();
        Carte c = new Carte("1234");

        dis.insererCarte(c);
    }
}