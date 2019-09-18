// PathParsers ============================================================================

// une interface qui permet de parser les nom de fichier depuis un chemin absolu
interface FileNameParser {
    void parse_filename (String path);
    int count_folders_to_root (String path);
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

    @Override
    public int count_folders_to_root(String path) {
        return this.linuxCounter.count_folders_to_root(path);
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

    @Override
    public int count_folders_to_root(String path) {
        return this.winCounter.count_folders_to_root(path);
    }
}

// une factory qui permet de créer différents parsers
class ParserFactory {
    // retourne un parser pour Linux
    public LinuxFileNameParser createLinuxParser () {
        return new LinuxFileNameParser();
    }

    // retourne un parser pour Windows
    public WindowsFileNameParser createWindowsParser () {
        return new WindowsFileNameParser();
    }
}

// FolderCounters =====================================================================

// une classe implémentant FolderCounter qui permet de compter le nombre de dossier pour remonter à la racine sous un systeme windows
class WindowsFolderCounter implements FileNameParser {
    public int count_folders_to_root (String path) {
        String str = (path.charAt(path.length() - 1) == '\\' ) ? path.substring(0, path.length() - 1): path; // on supprime le caractère / de fin si il existe
        int cpt = 0; // le compteur de dossier
    
        // on compte le nombre de '/' dans le path afin de déduire le nombre de dossier présents jusqu'a la racine du systeme du fichier
        for (int i = 0 ; i < str.length() ; i++)
            cpt = (str.charAt(i) == '\\') ? cpt + 1 : cpt;

        return (cpt - 1); // on retourne le nombre de dossier
    }

    @Override
    public void parse_filename(String path) {} // on est obligé dedéfinir la méthode afin d'implémenter l'interface ParserFactory
}

// une classe implémentant FolderCounter qui permet de compter le nombre de dossier pour remonter à la racine sous un systeme UNIX
class LinuxFolderCounter implements FileNameParser {
    public int count_folders_to_root (String path) {
        String str = (path.charAt(path.length() - 1) == '/' ) ? path.substring(0, path.length() - 1): path; // on supprime le caractère / de fin si il existe
        int cpt = 0; // le compteur de dossier

        // on compte le nombre de '/' dans le path afin de déduire le nombre de dossier présents jusqu'a la racine du systeme du fichier
        for (int i = 0 ; i < str.length() ; i++)
            cpt = (str.charAt(i) == '/') ? cpt + 1 : cpt;

        return (cpt - 1); // on retourne le nombre de dossier
    }

    @Override
    public void parse_filename(String path) {} // on est obligé de définir la méthode afin de pouvoir implémenter l'interface ParserFactory
}

// ======================================================

public class Main {
    public static void main (String[] args) {
        // on créé une factory afin de créer des parsers
        ParserFactory parserFactory = new ParserFactory();

        // on essaie de parser un chemin de fichier de type windows
        parserFactory.createWindowsParser().parse_filename("C:\\Windows\\hello.dll");

        // on essaie de parser un chemin de fichier de type unix
        parserFactory.createLinuxParser().parse_filename("/home/hello.rc");

        
        // on essaie de compter les dossier jusqu'à la ra ParserFactorycine du fichier (UNIX)
        System.out.println("folders to system root: " + parserFactory.createLinuxParser().count_folders_to_root("/home/kalhan/test.c/")); // doit retourner 2
        System.out.println("folders to system root: " + parserFactory.createWindowsParser().count_folders_to_root("C:\\Windows\\hello.dll")); // doit retourner 1
    }
}