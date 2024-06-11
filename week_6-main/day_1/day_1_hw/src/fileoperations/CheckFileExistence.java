package fileoperations;

import java.io.File;
import java.io.FileNotFoundException;

public class CheckFileExistence {

    //This is used to check for the file and will terminate the whole application if the file isn't found

    public static boolean doesFileExist (String fileName) throws FileNotFoundException {
        File file = new File ("src", fileName);
        if (file.exists()){
            return true;
        } else {
            throw new FileNotFoundException("File does not exist, sorry");
        }

    }
}
