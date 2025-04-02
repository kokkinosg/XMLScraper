// This class is responsible for examining a given directory and returning all files of the specified extension

import  java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileLocator {

    //#region Local variables
    private String primaryDirectoryPath; 
    private ArrayList<File> pathArrayList;
    private ArrayList<File> typePathArrayList;
    private String fileType;

    //#endregion

    //#region Constructor

    public FileLocator(String primaryDirectoryPath, String fileType){

        // Initialise the directory by passing it as an argument to the constructor.
        this.primaryDirectoryPath = primaryDirectoryPath;
        // Initialise the file type
        this.fileType = fileType;
    }

    //#endregion 

    //#region Public Methods

    // Gets all files of a specified type, e.g. xml. 
    public ArrayList<File> getSpecificFilePaths(){

        // Initialise an empty array list to take all files of a specified file.
        typePathArrayList = new ArrayList<File>();

        // Name of each file
        String fileName;

        // Get all files regardless of their type.        
        ArrayList<File> allFilepaths = getAllFilePaths();

        // Interogate each file to check if it is of the specified extension
        for (File path:allFilepaths){
            // Get the name of file: "example.xml"
            fileName = path.getName();
            // Check if it contains the extension and if it does, add it to the list
            if(fileName.contains(fileType)){
                typePathArrayList.add(path);
            }
        }
        return typePathArrayList;
    }


    // Get all files in a directory.  
    // To be made private  
    public ArrayList<File> getAllFilePaths(){

        // Initialise an empty array list to take all files of all types. 
        this.pathArrayList = new ArrayList<File>();

        // Declare and initialise the primary directory as a file object
        File primaryDirectory = new File(primaryDirectoryPath);
        // Find all files in the primary directory inlcuding all files in subdirectories
        scanDirectory(primaryDirectory);

        // Return the arraylist which contains all file pahts. 
        return pathArrayList;
    }

    public void printFilePaths(ArrayList<File> allFilePaths){
        System.out.println("Files are:"); 
  
        // Display the names of the files 
        for (int i = 0; i < allFilePaths.size(); i++) { 
            System.out.println(allFilePaths.get(i)); 
        }
    }

    public void printFileCount(ArrayList<File> allFilePaths, ArrayList<File> typeFilePaths){

        int allFileCount = allFilePaths.size();
        int typeFileCount = typeFilePaths.size();
        System.out.println("Total number of files = " + allFileCount);
        System.out.println("Total number of "+ fileType + " = " + typeFileCount);
    }
    //#endregion

    //#region Helper functions

    // Recursive helper function to dive into a folder until there are no more folders to explore. 
    private void scanDirectory(File directory ){

        // Get a list of all folders and files in the directory 
        File[] children = directory.listFiles();

        // Go over each item in the array of files
        for(File child : children){
            //Check if it is a directory or a file
            if(child.isDirectory()){
                // Use recursion to go deeper. If it is a directory, then pass that directory to this method again. 
                scanDirectory(child);
            } else {
                // If it is not a directory, i.e. is a file, add it to the apporpriate File array. 
                pathArrayList.add(child);
            }
        }
    }

    //#endregion

}
