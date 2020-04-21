import java.io.IOException;

public class SortedCleaner {

    public static void clean(int quantity) throws IOException {
        for (int j = 0; j < quantity; j++) {
            ArrayPrinterCSV.print(new int[0], "./resources/sorted" + j + ".csv");
        }
    }
}
