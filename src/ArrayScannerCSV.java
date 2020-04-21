import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayScannerCSV {

    public static int [] getArray(String pathname) throws FileNotFoundException {
        Scanner inputStream = new Scanner(new File(pathname));
        String [] arrayStr = inputStream.next().split(",");
        inputStream.close();
        int [] array = new int[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            array[i] = Integer.parseInt(arrayStr[i]);
        }
        return array;
    }
}
