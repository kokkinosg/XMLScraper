import java.io.File;

public class Main {

    public static void main(String[] args) {

        String path = "/Users/george/Downloads/Scripts";

        FileLocator fileLocator = new FileLocator(path);
        File[] filePaths = fileLocator.getAllFilePaths();
        fileLocator.printFilePaths(filePaths);

    }
    
}
