import java.io.FileOutputStream;
import java.io.IOException;

public class SorterConstructor {

    public static void construct(String body) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./src/Sorter.java");
        fileOutputStream.write((HEAD + getMain(body) + body + FOOT).getBytes());
        fileOutputStream.close();
    }

    public static String getMain(String body) {
        String [] initialWords = body.split("\\(")[0].split(" ");
        return initialWords[initialWords.length - 1] + MAINPART;
    }


    private static final String HEAD = "import java.io.IOException;\n" +
            "\n" +
            "public class Sorter {\n" +
            "    \n" +
            "    private static final int quantity = " + Main.quantity + ";\n" +
            "\n" +
            "\tpublic static void main(String[] args) throws IOException {\n" +
            "        for (int j = 0; j < quantity; j++) {\n" +
            "            int [] array = ArrayScannerCSV.getArray(\"./resources/unsorted\" + j + \".csv\");\n" +
            "            ";


    private static final String MAINPART = "(array);\n" +
            "            ArrayPrinterCSV.print(array, \"./resources/sorted\" + j + \".csv\");\n" +
            "        }\n" +
            "\t}\n" +
            "\n";



    private static final String FOOT = "}";
}