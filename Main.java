import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String path = "/Users/george/Downloads/Scripts";
        String type = ".xml";

        FileLocator fileLocator = new FileLocator(path);
        ArrayList<File> filePaths = fileLocator.getAllFilePaths();
        fileLocator.printFilePaths(filePaths);

        System.out.println("Next test");

        fileLocator.printFilePaths(fileLocator.getSpecificFilePaths(type));


    }
}
