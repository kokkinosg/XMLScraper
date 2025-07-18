import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String path = "/Users/george/Downloads/Scripts";
        String type = ".xml";

        // FileLocator Tests

        FileLocator fileLocator = new FileLocator(path, type);
        ArrayList<File> allFilePaths = fileLocator.getAllFilePaths();
        ArrayList<File> typeFilePaths = fileLocator.getSpecificFilePaths();
        
        //fileLocator.printFilePaths(filePaths);
        //fileLocator.printFilePaths(fileLocator.getSpecificFilePaths());

        fileLocator.printFileCount(allFilePaths, typeFilePaths);

        System.out.println(".....................");
        fileLocator.printFilePaths(typeFilePaths);

        // FileHandler tests
        FileHandler fileHandler = new FileHandler(typeFilePaths);

        //fileHandler.printFileContents(typeFilePaths.get(0));

        //System.out.println(fileHandler.getXMLtoString(typeFilePaths.get(0)));

        System.out.println("........................................");

        //fileHandler.printFileCommentsOnly(typeFilePaths.get(7));

        // Get all comments from all files
        ArrayList<ArrayList<String>> allFileComments = fileHandler.getAllCommentsFromAllFiles();

        //System.out.println(allFileComments.toString());

        System.out.println("........................................");

        // Path to CSV
        String csvPath ="/Users/george/Downloads/testCSV.csv";

        CSVExporter csvExportClass = new CSVExporter(csvPath);
        csvExportClass.exportToCSV(allFileComments);

        System.out.println("Process done");


    }
}
