package AbstractFactory;

// Parsers =============================================================================

// une interface qui permet de parser les nom de fichier depuis un chemin absolu
interface FileNameParser {
    void parse_filename (String path);
}

// une classe implémentant FileNameParser qui permet de parser un nom de fichier sous UNIX
class LinuxFileNameParser implements FileNameParser {

    // on stocke la classe de traitement du nombre de dossier
    LinuxFolderCounter linuxCounter = new LinuxFolderCounter();

    // la fonction qui permet de trouver un nom de fichier depuis un chemin absolu sous UNIX
    public void parse_filename (String path) {
        // index est l'endroit ou se situe, dans la String path, la dernière apparition du caractère /
        int index = path.lastIndexOf("/");

        // on construit une string qui ne contient que la partie à droite du dernier caractére /
        String r = path.substring(index + 1);
        System.out.println(r);
    }
}

// une classe implémentant FileNameParser qui permet de parser un nom de fichier sous WINDOWS
class WindowsFileNameParser implements FileNameParser {

    // on stocke la classe de traitement du nombre de dossier
    WindowsFolderCounter winCounter = new WindowsFolderCounter();

    // la fonction qui permet de trouver un nom de fichier depuis un chemin absolu sous windows
    public void parse_filename (String path) {
        // index est l'endroit ou se situe, dans la String path, la dernière apparition du caractère \
        int index = path.lastIndexOf("\\");

        // on construit une string qui ne contient que la partie à droite du dernier caractére \
        String r = path.substring(index + 1);
        System.out.println(r);
    }
}

// FolderCounters =====================================================================

interface FolderCounter {
    int count_folders_to_root (String path);
}

// une classe implémentant FolderCounter qui permet de compter le nombre de dossier pour remonter à la racine sous un systeme windows
class WindowsFolderCounter implements FolderCounter {
    public int count_folders_to_root (String path) {
        String str = (path.charAt(path.length() - 1) == '\\' ) ? path.substring(0, path.length() - 1): path; // on supprime le caractère / de fin si il existe
        int cpt = 0; // le compteur de dossier
    
        // on compte le nombre de '/' dans le path afin de déduire le nombre de dossier présents jusqu'a la racine du systeme du fichier
        for (int i = 0 ; i < str.length() ; i++)
            cpt = (str.charAt(i) == '\\') ? cpt + 1 : cpt;

        return (cpt - 1); // on retourne le nombre de dossier
    }
}

// une classe implémentant FolderCounter qui permet de compter le nombre de dossier pour remonter à la racine sous un systeme UNIX
class LinuxFolderCounter implements FolderCounter {
    public int count_folders_to_root (String path) {
        String str = (path.charAt(path.length() - 1) == '/' ) ? path.substring(0, path.length() - 1): path; // on supprime le caractère / de fin si il existe
        int cpt = 0; // le compteur de dossier

        // on compte le nombre de '/' dans le path afin de déduire le nombre de dossier présents jusqu'a la racine du systeme du fichier
        for (int i = 0 ; i < str.length() ; i++)
            cpt = (str.charAt(i) == '/') ? cpt + 1 : cpt;

        return (cpt - 1); // on retourne le nombre de dossier
    }
}

// Factory ======================================================

// une factory qui permet de créer un parser et un counter
interface OsFactory {
    FileNameParser createParser (); // permet de créer un parser
    FolderCounter createCounter (); // permet de créer un counter
}

// une factory qui permet de créer un parser et un counter pour Windows
class WindowsFactory implements OsFactory {
    // retourne un parser pour Windows
    public FileNameParser createParser () {
        return new WindowsFileNameParser();
    }

    public FolderCounter createCounter() {
        return new WindowsFolderCounter();
    }
}
// une factory qui permet de créer un parser et un counter pour Unix
class LinuxFactory implements OsFactory {
    // retourne un parser pour Linux
    public FileNameParser createParser () {
        return new LinuxFileNameParser();
    }

    public FolderCounter createCounter() {
        return new LinuxFolderCounter();
    }
}

// Main =========================================================

public class Main {
    public static void main (String[] args) {
        // on créé une factory pour chaque OS
        OsFactory windowsFactory = new WindowsFactory();
        OsFactory linuxFactory = new LinuxFactory();

        // Pour chaque OS on créé un parser et un counter
        // pour windows =================================
        FileNameParser winParser = windowsFactory.createParser(); // création d'un parser
        FolderCounter winCounter = windowsFactory.createCounter(); // création d'un counter

        // on créé des filepaths de test
        String winPath1 = "C:\\Windows\\ProgramFiles\\test\\hello.dll"; // il y a 3 dossiers jusqu'à la racine
        String winPath2 = "C:\\Windows\\ProgramFiles\\test.dll"; // il y a 2 dossiers jusqu'à la racine

        // on appelle la fonction de parsing et on affiche les résultats
        winParser.parse_filename(winPath1); // est sensé afficher "hello.dll"
        winParser.parse_filename(winPath2); // est sensé afficher "test.dll"

        // on appelle la fonction du counter et on affiche les résultats
        System.out.println("[Win] Folder count to system root: " + winCounter.count_folders_to_root(winPath1)); // est sensé afficher 3
        System.out.println("[Win] Folder count to system root: " + winCounter.count_folders_to_root(winPath2)); // est sensé afficher 2

        // pour linux ===================================
        FileNameParser linuxParser = linuxFactory.createParser(); // création d'un parser
        FolderCounter linuxCounter = linuxFactory.createCounter(); // création d'un counter

        // on créé des filepaths de test
        String linuxPath1 = "/home/Documents/test/hello.c"; // il y a 3 dossiers jusqu'à la racine
        String linuxPath2 = "/home/Documents/test.c"; // il y a 2 dossiers jusqu'à la racine

        // on appelle la fonction de parsing et on affiche les résultats
        linuxParser.parse_filename(linuxPath1); // est sensé afficher "hello.c"
        linuxParser.parse_filename(linuxPath2); // est sensé afficher "test.c"

        // on appelle la fonction du counter et on affiche les résultats
        System.out.println("[Unix] Folder count to system root: " + linuxCounter.count_folders_to_root(linuxPath1)); // est sensé afficher 3
        System.out.println("[Unix] Folder count to system root: " + linuxCounter.count_folders_to_root(linuxPath2)); // est sensé afficher 2
    }
}