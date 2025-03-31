import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String path = "/Users/george/Downloads/Scripts";

        FileLocator fileLocator = new FileLocator(path);
        ArrayList<File> filePaths = fileLocator.getAllFilePaths();
        fileLocator.printFilePaths(filePaths);

    }
}
