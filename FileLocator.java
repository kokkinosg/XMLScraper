// This class is responsible for examining a given directory and returning all files of the specified extension

import  java.io.File;
import java.util.ArrayList;

public class FileLocator {

    //#region Local variables
    private String primaryDirectoryPath; 
    private ArrayList<File> pathArrayList;

    //#endregion

    //#region Constructor

    public FileLocator(String primaryDirectoryPath){

        //Initialise the directory by passing it as an argument to the constructor.
        this.primaryDirectoryPath = primaryDirectoryPath;
    }

    //#endregion 

    //#region Methods

    // Get all files in a directory.    
    public ArrayList<File> getAllFilePaths(){

        // Declare and initialise the primary directory as a file object
        File primaryDirectory = new File(primaryDirectoryPath);
        // Initiialise the arrayList to hold all file objects
        this.pathArrayList = new ArrayList<File>();
        // Find all files in the primary directory inlcuding all files in subdirectories
        scanDirectory(primaryDirectory);

        // Return the arraylist which contains all file pahts. 
        return pathArrayList;
    }

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

    public void printFilePaths(ArrayList<File> allFilePaths ){
        System.out.println("Files are:"); 
  
        // Display the names of the files 
        for (int i = 0; i < allFilePaths.size(); i++) { 
            System.out.println(allFilePaths.get(i)); 
        }
    }
    //#region
}
