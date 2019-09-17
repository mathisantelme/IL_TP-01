// une interface qui permet de parser les nom de fichier depuis un chemin absolu
interface FileNameParser {
    void parse_filename (String path);
}

// une classe implémentant FileNameParser qui permet de parser un nom de fichier sous UNIX
class LinuxFileNameParser implements FileNameParser {
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
    // la fonction qui permet de trouver un nom de fichier depuis un chemin absolu sous windows
    public void parse_filename (String path) {
        // index est l'endroit ou se situe, dans la String path, la dernière apparition du caractère \
        int index = path.lastIndexOf("\\");

        // on construit une string qui ne contient que la partie à droite du dernier caractére \
        String r = path.substring(index + 1);
        System.out.println(r);
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

public class Main {
    public static void main (String[] args) {
        // on créé une factory afin de créer des parsers
        ParserFactory parserFactory = new ParserFactory();

        // on essaie de parser un chemin de fichier de type windows
        parserFactory.createWindowsParser().parse_filename("C:\\Windows\\hello.dll");

        // on essaie de parser un chemin de fichier de type unix
        parserFactory.createLinuxParser().parse_filename("/home/hell0.rc");
    }
}