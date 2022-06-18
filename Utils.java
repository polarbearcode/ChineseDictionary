import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteFile {

    /**
     * Write the object to the file specified by file path.
     * @param filePath  String path to the file
     * @param o The Object to save to the file.
     */
    public static void writeFile(String filePath, Object o) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
