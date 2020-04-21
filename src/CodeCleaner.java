import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeCleaner {

    public static final Pattern p = Pattern.compile("(public|protected|private)+ +(static)? +(void|boolean)+ +[\\w\\<\\>\\[\\]]+\\( *int +.*\\[\\].*\\) *\\{");

    public static ArrayList<Answer> clean(ArrayList<Answer> list) {
        int n = list.size();
        for(int i = 0; i < n; i++) {
            Answer answer= list.get(i);

            answer.answerBody = answer.answerBody
                    .replaceAll("<code>", "")
                    .replaceAll("</code>", "")
                    .replaceAll("&lt;", "<")
                    .replaceAll("&gt;", ">")
                    .replaceAll("&amp;", "&")
                    .replaceAll("([.\\w]+)\\s*\\((.*)\\);","");


            Matcher m = p.matcher(answer.answerBody);



            if ((answer.answerBody.length() < 150)
                    || (!answer.answerBody.contains("{"))
                    || ((!answer.answerBody.contains("}")) || !m.find())) {
                list.remove(i);
                i--;
                n--;
            }
        }
        return list;
    }
}