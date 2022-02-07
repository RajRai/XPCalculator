package common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class HighscoreReader {

    private HashMap<String, Integer> xp = new HashMap<>();

    public HighscoreReader(String name) throws IOException {
        String baseURL = "http://highscores.pkhonor.net/?u=";
        String nameMod = name.replace(' ', '+');
        String fullURL = baseURL + nameMod;

        Document doc = Jsoup.connect(fullURL).get();
        Elements rows = doc.select("tr");
        for (Element row : rows){
            String[] split = row.text().split(" ");
            if (split.length > 3){
                try {
                    xp.put(split[0], Integer.parseInt(split[3].replace(",", "")));
                } catch (NumberFormatException numberGreaterThanMax) { }
            }
        }
    }

    public int getXp(String skill){
        return xp.get(skill);
    }

}
