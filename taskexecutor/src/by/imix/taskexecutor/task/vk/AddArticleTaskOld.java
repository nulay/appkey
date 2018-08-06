package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.TaskMain;
import by.imix.taskexecutor.temp.Article;
import by.imix.taskexecutor.temp.VKActionImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by miha on 14.05.2017.
 *
 * Class for create task add article and store data for it
 */
public class AddArticleTaskOld extends TaskMain implements Task {

    private static Logger log = LoggerFactory.getLogger(VKActionImpl.class);

    public Article article;
    public String url;

    public AddArticleTaskOld(WebDriver driver) {
        super(driver);
    }

    /**
     * @OverrideDoc
     */

    public void execute() {
        moveTo(url);
        addArticle(article);
    }

    protected void addArticle(Article article) {
        log.debug("addArticle method start.");
        WebElement elementArt = null;
        int ind = 0;
        boolean d = false;
        while (!d) {
            elementArt = searchContainerForPastArticle();
            try {
                elementArt.click();
                d = true;
            } catch (Exception e) {
                if (ind > 9) {
                    log.debug("We could not add article");
                    return;
                }
                sleepOn(1000);
                ind++;
                log.debug("Attempt #" + ind);
            }
        }

        if (article.getImages() != null) {
            pastImagesToArticle(article.getImages(), elementArt);
        }

        sleepOn(500);
        elementArt.sendKeys(article.getArticle());
        sleepOn(2000);
        executeJavaScript(" wall.sendPost()");
        sleepOn(2000);
        log.debug("addArticle method end.");
    }

    private WebElement searchContainerForPastArticle() {
        WebElement element = driver.findElement(By.id("post_field"));
        return element;
    }

    private void pastImagesToArticle(String[] images, WebElement elementArt) {
        for (String image : images) {
            pastImageToArticle(image, elementArt);
        }
    }

    private void pastImageToArticle(String image, WebElement elementArt) {
        elementArt.sendKeys(image);
        sleepOn(1000);
        elementArt.sendKeys(" ");
        sleepOn(5000);
        elementArt.clear();
    }


}
