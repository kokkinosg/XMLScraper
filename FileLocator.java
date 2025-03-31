// This class is responsible for examining a given directory and returning all files of the specified extension

import  java.io.File;

public class FileLocator {

    //#region Local variables
    private String directoryPath; 
    private File[] filePaths; 
    private File fileObj;

    //#endregion

    //#region Constructor

    // Pass the directoryPath as an argument to the constructor
    public FileLocator(String directoryPath){
        this.directoryPath = directoryPath;
    }

    //#endregion 

    //#region Methods
    
    public File[] getAllFilePaths(String directory){
        // Create a File class object to get access to the methods. Pass the directory to the constructor.
        fileObj = new File(directory);
        // Get all names of the files present in the directory
        filePaths = fileObj.listFiles();
        return filePaths;
    }

    public void readFilePaths(File[] filePaths){
        System.out.println("Files are:"); 
  
        // Display the names of the files 
        for (int i = 0; i < filePaths.length; i++) { 
            System.out.println(filePaths[i].getName()); 
        }
    }
    //#region
    
}
