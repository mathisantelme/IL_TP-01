public class Main {
    // la fonction qui permet de trouver un nom de fichier depuis un chemin absolu sous windows
    public static void main_parse_filename (String path) {
        // index est l'endroit ou se situe, dans la String path, la dernière apparition du caractère \
        int index = path.lastIndexOf("\\");

        // on construit une string qui ne contient que la partie à droite du dernier caractére \
        String r = path.substring(index + 1);
        System.out.println(r);
    }

    public static void main (String[] args) {
        main_parse_filename("C:\\Windows\\hello.dll");
    }
}