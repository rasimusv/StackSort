import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Question {

    private String url;
    public int id;

    public Question(String url) {
        this.url = "http://stackoverflow.com" + url;
        this.id = Integer.parseInt(url.split("/")[2]);
    }

    public ArrayList<Answer> getAnswers() throws IOException {
        ArrayList<Answer> answersList = new ArrayList<>();

        Document document = Jsoup.connect(url).get();

        Elements answers = document.getElementsByAttributeValue("class", "answer");

        for (int i = 0; (i < answers.size()) && (i < 30); i++) {
            Elements answerBody = answers.get(i).getElementsByTag("code");

            for (Element element : answerBody) {
                answersList.add(new Answer(element.toString(), id));
            }
        }
        return answersList;
    }
}