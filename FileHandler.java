import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class FileHandler {

    //#region Variables
    private BufferedReader reader;
    private ArrayList<File> fileList;
    // All comments from a single file
    private ArrayList<String> commentsList;
    // All comments from all files
    private ArrayList<ArrayList<String>> allFileCommentsList;
    private String commentStart;
    private String commentEnd;

    //#endregion

    //#region Constructor
    public FileHandler(ArrayList<File> fileList){
        //Initialise the list to contain all files to be handled
        this.fileList = fileList;
        this.commentStart = "<!--";
        this.commentEnd ="-->";
    }

    //#endregion

    //#region Methods
    public ArrayList<ArrayList<String>> getAllCommentsFromAllFiles(){

        // Initialise an empty arraylist to hold all comments to all files. Each element is all the comments of a single file. 

        allFileCommentsList = new ArrayList<ArrayList<String>>();

        // Initialise a new arraylist to hold the comments of each file.
        ArrayList<String> singleFileComments = new ArrayList<String>();
        
        // Go over each file in fileList
        for(File file: fileList){
            // Get all comments from a file to an arraylist
            singleFileComments = getAllCommentsFromFile(file);
            // Add that arraylist to the grand arrayList
            allFileCommentsList.add(singleFileComments);
            // Status indicator
            System.out.println("Processed: "+file.getName());
        }
        return allFileCommentsList;
    }
   
    // Get all comments from a file into an ArrayList.
    public ArrayList<String> getAllCommentsFromFile(File file){
        
        // Get the XML to a string.
        String xmlString= getXMLtoString(file);
        ArrayList<String> comments = identifyAllComments(xmlString);

        return comments;
    }

    // Prints the comments of a file only 
    public void printFileCommentsOnly(File file){

        // Get the XML to a string.
        String xmlString= getXMLtoString(file);
        ArrayList<String> comments = identifyAllComments(xmlString);

        // Print all comments 
        for(String comment : comments){
            System.out.println(comment);
        }
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

    //#endregion

    //#region Helper functions

    // Appends all of XML contents to a single string.
     private String getXMLtoString(File file){

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

    // Get an arraylist consisting of all comments by passing it the xml in a single string. 
    private ArrayList<String> identifyAllComments(String xmlString){

        // Initialise a processingString which will be updated eveytime we cut a comment out of the original string. 
        String processingString = xmlString;
        String comment; 

        // Initialise the ArrayList to contain the comments to a file
        commentsList = new ArrayList<String>();

        while(doesStringHaveComments(processingString)){
            // Extract the comment 
            comment = identifySingleComment(processingString);
            // Cut it out of the original string but only in its first occurance. To do this we use replaceFirst which takes a regex.
            // But I create an exact reggex to the comment using Pattern.quote.
            processingString = processingString.replaceFirst(Pattern.quote(comment), "");
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

    // Check if a string includes at least one comment. 
    private boolean doesStringHaveComments(String inpuString){

        int indexCommentStart = inpuString.indexOf(commentStart);
        int indexCommentEnd = inpuString.indexOf(commentEnd);
        // If the comment start and end  donot exist, we assume htere are no comments in the string.
        if(indexCommentStart == -1 && indexCommentEnd == -1){
            return false;
        } else {
            return true;
        }
    }

    //#endregion
}
