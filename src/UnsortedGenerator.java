import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class UnsortedGenerator {

    public static void generate(int size, int quantity) throws IOException {
        Random random = new Random(new Date().getTime());
        for (int j = 0; j < quantity; j++) {
            int [] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt();
            }
            ArrayPrinterCSV.print(array, "./resources/unsorted" + j + ".csv");
        }
    }
}
