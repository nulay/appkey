package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKworkHelper;
import by.imix.taskexecutor.parser.SerialochkaParser;
import by.imix.taskexecutor.sightbot.Account;
import by.imix.taskexecutor.temp.Article;
import by.imix.taskexecutor.vk.FilmTake;
import org.eclipse.jetty.util.ArrayQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Queue;


/**
 * Class for post Film to VK
 */
public class PostFilmToVKTask implements Task {
    private static Logger log = LoggerFactory.getLogger(PostFilmToVKTask.class);
    private SerialochkaParser serialochkaParser = new SerialochkaParser();
    //Queue when store article
    public Queue<Article> queueForArticle;
    private VKworkHelper vKworkHelper;

    /**
     * Account
     */
    private Account account;

    public PostFilmToVKTask(Account account) {
        this.account = account;
        queueForArticle = new ArrayQueue<>();
        vKworkHelper = new VKworkHelper(account);
    }

    @Override
    public void execute() {
        loadFilmToQueue();
        Article article = queueForArticle.peek();
        boolean isSuccess = vKworkHelper.addArticleWithRepeatUnsuccess(article);
        if (!isSuccess) {
            queueForArticle.add(article);
        }
    }

    /**
     * Method for searth new film and load them like Article to queue.
     * @return
     */
    private Article loadFilmToQueue() {
        Article article = null;
        if (queueForArticle.size() > 0) {
            article = queueForArticle.peek();
        } else {
            try {
                List<FilmTake> listFilm = serialochkaParser.startParse();
                for (FilmTake film : listFilm) {
                    article = new Article();
                    String content = film.getName() + "\r\n" + film.getFilm() + "\r\n" + film.getDiscription();
                    content = content.replaceAll("<br>", "\r\n");
                    log.debug(content);
                    article.setArticle(content);
                    article.setImages(new String[]{film.getImg()});
                    queueForArticle.add(article);
                }
                if (queueForArticle.size() > 0) {
                    article = queueForArticle.peek();
                }
            } catch (IOException e) {
            }
        }
        return article;
    }
}
