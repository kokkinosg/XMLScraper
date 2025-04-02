import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String path = "/Users/george/Downloads/Scripts";
        String type = ".xml";

        FileLocator fileLocator = new FileLocator(path, type);
        ArrayList<File> allFilePaths = fileLocator.getAllFilePaths();
        ArrayList<File> typeFilePaths = fileLocator.getSpecificFilePaths();
        
        //fileLocator.printFilePaths(filePaths);
        //fileLocator.printFilePaths(fileLocator.getSpecificFilePaths());

        fileLocator.printFileCount(allFilePaths, typeFilePaths);

        System.out.println(".....................");
        //fileLocator.printFilePaths(typeFilePaths);


    }
}
