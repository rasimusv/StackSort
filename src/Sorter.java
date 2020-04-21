import java.io.IOException;

public class Sorter {
    
    private static final int quantity = 10;

	public static void main(String[] args) throws IOException {
        for (int j = 0; j < quantity; j++) {
            int [] array = ArrayScannerCSV.getArray("./resources/unsorted" + j + ".csv");
            sort(array);
            ArrayPrinterCSV.print(array, "./resources/sorted" + j + ".csv");
        }
	}

public static void sort(int [] array) {
    int n = array.length;
    for (int j = 1; j < n; j++) {
        int key = array[j];
        int i = j-1;
        while ( (i > -1) && ( array [i] > key ) ) {
            array [i+1] = array [i];
            i--;
        }
        array[i+1] = key;
        
    }
}}