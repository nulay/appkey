package by.imix.taskexecutor.temp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by miha on 22.11.2016.
 */
public class LoaderArticle {
    private static final Logger _log= LoggerFactory.getLogger(LoaderArticle.class);
    public static void main(String arg[]) {
        LoaderArticle aa = new LoaderArticle();
        try {
            aa.getFilm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Article getAnekdot() throws IOException {
        String url = "https://vk.com/notgravity";
        String urlAnekd = "http://task2-nulay.rhcloud.com/changer/nextanekdot3";

        HttpResponse response = Request.Get(urlAnekd).execute().returnResponse();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(new InputStreamReader(response.getEntity().getContent()));
        if (element.isJsonObject()) {
            Article article = new Article();
            JsonObject root = element.getAsJsonObject();
            String anek = root.get("anekdot").getAsString();
            anek = anek.replaceAll("<br>", "\r\n");
            _log.debug(anek);
            article.setArticle(anek);
            return article;
        }
        return null;
    }

    public Article getFilm() throws IOException {
        String url = "https://vk.com/notgravity";
        String urlAnekd = "http://task2-nulay.rhcloud.com/changer/nextfilm3";

        HttpResponse response = Request.Get(urlAnekd).execute().returnResponse();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(new InputStreamReader(response.getEntity().getContent()));
        if (element.isJsonObject()) {
            Article article = new Article();
            JsonObject root = element.getAsJsonObject();
            String articl = root.get("name").getAsString() + "\r\n" + root.get("film").getAsString() + "\r\n" + root.get("discription").getAsString();
            articl = articl.replaceAll("<br>", "\r\n");
            _log.debug(articl);
            article.setArticle(articl);

            article.setImages(new String[]{root.get("img").getAsString()});
            return article;

        }


        return null;
    }
}
