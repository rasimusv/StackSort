import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    protected static int quantity;
    @Deprecated
    private static Algorithm algorithm;
    private static final int NUMBER_OF_ATTEMPTS = 50; //Has to be  multiple of 50

    public static void main(String[] args) throws IOException, InterruptedException {
        initialize();
        autoSorter();
    }

    private static void printResult(boolean result) {
        if (result) {
            System.out.println(TextColor.GREEN + "Algorithm is right!" + TextColor.RESET);
            System.out.println(TextColor.BLUE + "Correct algorithm was in question " + algorithm.questionId + "!" + TextColor.RESET);
        } else {
            System.out.println(TextColor.RED + "Algorithm is not right!" + TextColor.RESET);
        }
    }

    private static void initialize() throws IOException {
        getQuantity();
        if (ConsoleQuestion.getAnswer("\"Do you want to clear all files in \"resource\" path?\"")) {
            ResourcesCleaner.clean();
            FileGenerator.generate(quantity);
            UnsortedGenerator.generate(new Random(new Date().getTime()).nextInt(9900) + 100, quantity);
        } else {
            if (ConsoleQuestion.getAnswer("Do you want to generate new data sets?")) {
                FileGenerator.generate(quantity);
                UnsortedGenerator.generate(new Random(new Date().getTime()).nextInt(9900) + 100, quantity);
            } else {
                SortedCleaner.clean(quantity);
            }
        }


    }

    private static void compileAndRun() throws IOException, InterruptedException {
        Runtime r = Runtime.getRuntime();
        Process process = r.exec("javac -sourcepath src -d out src/Sorter.java");
        process.waitFor();
        process = r.exec("java -cp out Sorter");
        process.waitFor();
    }

    private static void getQuantity () {
        System.out.println(TextColor.BLUE + "Enter the quantity of files with data sets" + TextColor.RESET);
        quantity =  Integer.parseInt(new Scanner(System.in).next());
    }

    private static boolean checkSets() throws FileNotFoundException {
        for (int i = 0; i < quantity; i++) {
            int [] unsortedArray = ArrayScannerCSV.getArray("./resources/unsorted" + i + ".csv");
            int [] potentiallySortedArray = ArrayScannerCSV.getArray("./resources/sorted" + i + ".csv");
            Arrays.sort(unsortedArray);
            if (!Arrays.equals(potentiallySortedArray, unsortedArray)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    private static void newSorter() throws IOException {
        algorithm = Algorithms.extract(Answers.getAnswerWithAlgorithm());
        SorterConstructor.construct(algorithm.methodBody);
    }

    private static void autoSorter() throws IOException, InterruptedException {
        boolean flag = false;
        ArrayList<Algorithm> list = Algorithms.autoextract(Answers.getAnswersWithAlgorithm(NUMBER_OF_ATTEMPTS));
        for (Algorithm algorithmPotential : list) {
            algorithm = algorithmPotential;
            SorterConstructor.construct(algorithmPotential.methodBody);
            compileAndRun();
            if (checkSets()) {
                flag = true;
                break;
            }
        }
        printResult(flag);
    }
}
