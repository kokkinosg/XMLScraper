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
        // Extract the first comment 
        String comment = extractSingleComment(xmlString);
        //Print it.
        System.out.println(comment);
    }

    public String extractSingleComment (String xmlString){
        // Declare and initialise the comments. 

        String commentStart = "<!--";
        String commentEnd = "-->";
        int indexCommentStart;
        int indexCommentEnd;
        String commentString;

        // Get the index of the first character of the commentStart and add the length of the comment Start to ensure that we read all characters after that.
        indexCommentStart = (xmlString.indexOf(commentStart)) + commentStart.length();
        indexCommentEnd = xmlString.indexOf(commentEnd);
        // Extract a substring between the index comments
        commentString = xmlString.substring(indexCommentStart, indexCommentEnd);

        return commentString;
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
}
