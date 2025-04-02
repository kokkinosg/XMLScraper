import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileHandler {

    //#region Variables
    private BufferedReader reader;

    //#endregion

    //#region Constructor
    public FileHandler(){

    }

    //#endregion

    //#region Methods
    
    // Prints the contents of a single file
    public void printFileContents(File file){
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
