import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayPrinterCSV {


    public static void print(int [] array, String pathname) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(pathname);
        String output = "";
        if (array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                output = output + array[i] + ",";
            }
            output = output + array[array.length - 1];
        }
        fileOutputStream.write(output.getBytes());
        fileOutputStream.close();
    }
}
