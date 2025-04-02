import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileHandler {

    //#region Variables
    private BufferedReader reader;
    private ArrayList<File> fileList;
    private ArrayList<String> commentsList;
    private String commentStart;
    private String commentEnd;

    //#endregion

    //#region Constructor
    public FileHandler(ArrayList<File> fileList){
        //Initialise the list to contain all files to be handled
        this.fileList = fileList;
    }

    //#endregion

    //#region Methods
    // Prints the comments of a file only 
    public void printFileCommentsOnly(File file){

        // Get the XML to a string.
        String xmlString= getXMLtoString(file);

        
    }

    
    // Prints the contents of a single file
    public void printFileContents(File file){
        try {
            // Opens the file using FileReader, wraps that FileReader with a BufferedReader to allow efficient reading 
            // and assigns the resulting BufferedReader object to the variable reader.
            reader = new BufferedReader(new FileReader(file));
            String line;
            // Go over each line
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Appends all of XML contents to a single string.
    public String getXMLtoString(File file){

        String xmlContentsString;
        String filePath;

        try {
            // Get the absolute path to the file
            filePath = file.getAbsolutePath();
            // Create a byte array which will contain all characters from the file (includes new lines and spaces)
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            // COnvert the bytes to a string using the proper encoding. 
            xmlContentsString = new String(fileBytes, StandardCharsets.UTF_8);
            return xmlContentsString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //#endregion

    //#region Helper functions

    // Get an arraylist consisting of all comments by passing it the xml in a single string. 
    private ArrayList<String> identifyAllComments(String xmlString){

        // Initialise a processingString which will be updated eveytime we cut a comment out of the original string. 
        String processingString = xmlString;
        String comment; 

        // Initialise the ArrayList to contain the comments to a file
        commentsList = new ArrayList<String>();

        while(doesStringHaveComments(processingString)){
            // Extract the comment 
            comment = identifySingleComment(xmlString);
            // Cut it out of the original string
            processingString = xmlString.replace(comment, "");
            // Add it to the comments list
            commentsList.add(comment);
        }
        // Note that the comments are also stored in the class. 
        return commentsList;
    }

    // Get a string consiting of a single comments inlcuding the comment start and end singles.
    private String identifySingleComment (String inputString){
        if(doesStringHaveComments(inputString)){
            // Declare and initialise the comments. 
            int indexCommentStart;
            int indexCommentEnd;
            String commentString;

            // Get the index of the first character of the commentStart and the index of the last character of commentEnd
            indexCommentStart = (inputString.indexOf(commentStart));
            indexCommentEnd = inputString.indexOf(commentEnd) + commentEnd.length();
            // Extract a substring between the index comments
            commentString = inputString.substring(indexCommentStart, indexCommentEnd);

            return commentString;
        } else {
            System.out.println("There were no comments in the file");
            return null;
        }
    }

    private boolean doesStringHaveComments(String inpuString){
        // If the comment start and end  donot exist, we assume htere are no comments in the string.
        if(inpuString.indexOf(commentStart) == -1 && inpuString.indexOf(commentEnd) == -1){
            return false;
        } else {
            return true;
        }
    }

    //#endregion
}
