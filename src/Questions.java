import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Questions {

    private String url;

    public Questions(int page) {
        url = "http://stackoverflow.com/questions/tagged/java%2bsorting%2balgorithm?tab=frequent&page=" + page + "&pagesize=50";
    }

    public ArrayList<Question> getQuestionLinks() throws IOException {
        ArrayList<Question> pagesList = new ArrayList<>();

        Document document = Jsoup.connect(url).get();
        Elements h3Elements = document.getElementsByAttributeValue("class", "summary");

        h3Elements.forEach(h3Element -> {
            Element aElement = h3Element.child(0).child(0);
            String url = aElement.attr("href");
            pagesList.add(new Question(url));
        });

        return pagesList;
    }
}