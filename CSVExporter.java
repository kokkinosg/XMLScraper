import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class CSVExporter {

    //#region Variables
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;


    //#endregion

    //#region Constructor
    public CSVExporter(String pathName){
    
        try {
            // Initialise a new file at the given path
            file = new File(pathName);
            // Initialise the fileWriter object
            fileWriter = new FileWriter(file);
            // Initialise the bufferedWriter object
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //#endregion

    //#region Methods

    // Export to CSV where the delimiter is &&&
    public void exportToCSV(ArrayList<ArrayList<String>> data){
        try {
            // Create the headers
            bufferedWriter.write("Script Name&&& General Description&&& Step by Step");
            bufferedWriter.newLine();

            for (ArrayList<String> singleFileComments: data){
                //Write the name of script
                bufferedWriter.write(getScriptName(getGeneralDescription(singleFileComments)));
                // Write the general description.
                bufferedWriter.write("&&&" + getGeneralDescription(singleFileComments));
                // Write the single file comments
                bufferedWriter.write("&&&" + getAllSteps(singleFileComments));
                bufferedWriter.newLine();
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the name of the file 
    private String getScriptName(String generalDescription){
        String nameStartLocator = "name:";
        String nameEndLocator = ".xml";
        // Get the index of the start of the name of the script
        int indexNameStartLocator = generalDescription.indexOf(nameStartLocator) + nameStartLocator.length();
        // Get the index of the end of the name of the script
        int indexNameEndLocator = generalDescription.indexOf(nameEndLocator);
        // Get the name 
        String name = generalDescription.substring(indexNameStartLocator,indexNameEndLocator);
        return name;
    }

    // Get the general description which is always the first comment
    private String getGeneralDescription(ArrayList<String> singleFileComments){
        
        // Get the general descritpion which is always tje first comment in the arraylist
        String generalDescription = singleFileComments.get(0);
        // Get rid of comment markers
        String processedGenDescription = removeCommentMarks(generalDescription);
        return processedGenDescription;
    }

    // Get all steps in one string. 
    private String getAllSteps(ArrayList<String> singleFileComments){
        // Create a string builder for more efficient string building
        StringBuilder combinedSteps = new StringBuilder();

        for (int i = 1; i < singleFileComments.size(); i++) {
            // Remove comment marks from each step.
            String processedStep = removeCommentMarks(singleFileComments.get(i));
            // Append the step and add a newline.
            combinedSteps.append(processedStep).append(System.lineSeparator());
        }
        return combinedSteps.toString();
    }

    // Remove the comment marks from a given string
    private String removeCommentMarks(String comment){
        
        String processedComment = comment;
        processedComment = processedComment.replace("<!--", "");
        processedComment = processedComment.replace("-->", "");

        return processedComment;
    }

    //#endregion

    
}
