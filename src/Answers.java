import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Answers {

    @Deprecated
    public static Answer getAnswerWithAlgorithm() throws IOException {
        Answer rightAnswer = null;
        int page = 1;
        boolean flag = false;
        while (!flag) {
            ArrayList<Question> questionsList = new Questions(page).getQuestionLinks();
            int size = questionsList.size();

            for (int questionNumber = 0; !flag && questionNumber < size; questionNumber++) {

                ArrayList<Answer> answerList = CodeCleaner.clean(questionsList.get(questionNumber).getAnswers());

                for (Answer answer : answerList) {
                    System.out.println(answer.answerBody);
                    System.out.println();
                    if (ConsoleQuestion.getAnswer("Is there a method with correct algorithm here?")) {
                        flag = true;
                        rightAnswer = answer;
                        break;
                    }
                }
            }
            page++;
        }
        return rightAnswer;
    }

    public static ArrayList<Answer> getAnswersWithAlgorithm(int num) throws IOException {
        ArrayList<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < num/50; i++) {
            ArrayList<Question> questionsList = new Questions(i).getQuestionLinks();
            int size = questionsList.size();

            for (int questionNumber = 0; questionNumber < size; questionNumber++) {
                answerList.addAll(CodeCleaner.clean(questionsList.get(questionNumber).getAnswers()));
            }
        }
        return answerList;
    }
}
