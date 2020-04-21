import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Algorithms {

    @Deprecated
    public static Algorithm extract(Answer answer) {
        Scanner sc = new Scanner(System.in);
        String rightSample =answer.answerBody;

        String []  list = rightSample.split("\n");
        System.out.println(TextColor.BLUE);
        for (int i = 0; i < list.length; i++) {
            System.out.println((i + 1) + "    " + list[i]);
        }
        System.out.println(TextColor.RESET);
        System.out.println();
        System.out.println();
        System.out.println("Select the lines with the method. (Example: 1 - 10)");
        String  [] range = sc.nextLine().replace(" ", "").split("-");
        String rightAlgorithm = "";
        for (int i = Integer.parseInt(range[0]) - 1; i < Integer.parseInt(range[1]); i++) {
            rightAlgorithm = rightAlgorithm + list[i] + "\n";
        }
        System.out.println(TextColor.GREEN + rightAlgorithm + TextColor.RESET);
        return new Algorithm(rightAlgorithm, answer.questionId);
    }

    public static ArrayList<Algorithm> autoextract(ArrayList<Answer> input) {
        ArrayList<Algorithm> list = new ArrayList<>();

        for (Answer answer : input) {
            String [] arrayBlocks = CodeCleaner.p.split(answer.answerBody);
            for (int i = 1; i < arrayBlocks.length; i++) {
                String buffer = checkBlock(arrayBlocks[i]);
                if (buffer != null) {
                    list.add(new Algorithm(buffer, answer.questionId));
                }
            }
        }
        return list;
    }


    private  static String checkBlock(String input) {
        String [] array = input.split("");
        Stack<String> stack = new Stack<String>();
        stack.push("{");
        String algorithm = "public static void sort(int [] array) {";
        for (int j = 0; j < array.length; j++){
            algorithm = algorithm + array[j];
            if (array[j].equals("(") ||
                    array[j].equals("{") ||
                    array[j].equals("[")) {
                stack.push(array[j]);
            } else if (array[j].equals(")") ||
                    array[j].equals("}") ||
                    array[j].equals("]")) {
                String elem = stack.pop();
                if ((elem.equals("(") && !array[j].equals(")")) ||
                        (elem.equals("[") && !array[j].equals("]")) ||
                        (elem.equals("{") && !array[j].equals("}"))) {
                    return null;
                }
            }
            if (stack.isEmpty()) {
                return algorithm;
            }
        }
        return null;
    }
}
