import java.io.File;
import java.io.IOException;

public class FileGenerator {


    public static void generate(int quantity) throws IOException {
        for (int j = 0; j < quantity; j++) {
            new File("./resources/sorted" + j + ".csv").createNewFile();
            new File("./resources/unsorted" + j + ".csv").createNewFile();

        }
    }
}