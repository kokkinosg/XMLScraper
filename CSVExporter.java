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
            bufferedWriter.write("Script Name$General Description$Step by Step");
            bufferedWriter.newLine();

            for (ArrayList<String> singleFileComments: data){

                String row = cleanAndPrepRow(singleFileComments);
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Export to CSV completed!");
    }

    // Clean and prepare the row String for hte csv
    private String cleanAndPrepRow(ArrayList<String> singleFileComments){
        //Write the name of script
        String scriptName = getScriptName(singleFileComments);
        //System.out.println(scriptName);

        // Write the general description.
        String genDesc = getGeneralDescription(singleFileComments);
        //System.out.println(genDesc);

        // Write the single file comments
        String steps = getAllSteps(singleFileComments);
        //System.out.println(steps);

        // Clean the new lines and carriage returns

        String cleanGenDesc = genDesc.replaceAll("[\\r\\n]+", " ");
        String cleanSteps = steps.replaceAll("[\\r\\n]+", " ");
        
        //Compose the row
        String row = scriptName + "$" + cleanGenDesc + "$" + cleanSteps;
        //System.out.println(row);

        return row;
    }

    // Get the name of the file 
    private String getScriptName(ArrayList<String> singleFileComments){
        if (singleFileComments == null || singleFileComments.isEmpty()) {
            // Option 1: Return an empty string if no general description is present
            return "No name available";
            // Option 2: Throw an exception with a descriptive message:
            // throw new IllegalArgumentException("No comments found for this file.");
        }

        // Get the general descritpion which is always tje first comment in the arraylist
        String name = singleFileComments.get(0);
        // Get rid of comment markers
        String processedName = removeCommentMarks(name);
        return processedName;
    }

    // Get the general description which is always the second comment. Rememebr we added the first comment to be the name of the file from File object.
    private String getGeneralDescription(ArrayList<String> singleFileComments){
        // If it only has 1 or 0 elements then there is no general description. The first element is the name. 
        if (singleFileComments.size() <= 1) {
            // Option 1: Return an empty string if no general description is present
            return "No general description available";
            // Option 2: Throw an exception with a descriptive message:
            // throw new IllegalArgumentException("No comments found for this file.");
        }
        // Get the general descritpion which is always the second comment in the arraylist
        String generalDescription = singleFileComments.get(1);
        // Get rid of comment markers
        String processedGenDescription = removeCommentMarks(generalDescription);
        return processedGenDescription;
    }

    // Get all steps in one string. 
    private String getAllSteps(ArrayList<String> singleFileComments){
        if (singleFileComments == null || singleFileComments.isEmpty()) {
            // Option 1: Return an empty string if no general description is present
            return "No set of steps available";
            // Option 2: Throw an exception with a descriptive message:
            // throw new IllegalArgumentException("No comments found for this file.");
        }
        // Create a string builder for more efficient string building
        StringBuilder combinedSteps = new StringBuilder();

        // Iterate through the comments starting from the second comment.
        for (int i = 2; i < singleFileComments.size(); i++) {
            // Remove comment marks from each step.
            String processedStep = removeCommentMarks(singleFileComments.get(i));
            // Append the step and add a newline.
            // combinedSteps.append(processedStep).append(System.lineSeparator());
            combinedSteps.append(processedStep);

            // If there are more steps after this one, add the arrow 
            if (i<singleFileComments.size()-1){
                combinedSteps.append("->");
            }
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
