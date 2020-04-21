import java.io.File;
import java.io.IOException;

public class ResourcesCleaner {

    public static void clean() throws IOException {
        File file = new File("resources");
        file.delete();
        file.createNewFile();
    }
}
