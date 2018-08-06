package by.imix.taskexecutor.temp;

import by.imix.taskexecutor.filter.Filter;
import by.imix.taskexecutor.sightbot.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * VKAction implementation
 */
public class VKActionImpl implements VKAction {

    private static Logger _log = LoggerFactory.getLogger(VKActionImpl.class);

    private WebDriver driver;

    private final static String mobileSiteName = "https://m.vk.com";
    private final static String siteName = "https://vk.com";

    private VKActionSettings vkActionSettings;

    public VKActionImpl(WebDriver driver) {
        this.driver = driver;
    }

    public VKActionImpl(WebDriver driver, VKActionSettings vkActionSettings) {
        this.driver = driver;
        this.vkActionSettings = new VKActionSettings();
    }

    public List<Article> findRecords(String url) {
        driver.navigate().to(url);
        waitLoadSelector(By.cssSelector("#global_prg"), 1, driver);
        List<WebElement> wel = null;
        List<Article> artticles = new ArrayList<Article>();
        try {
            wel = driver.findElements(By.cssSelector("._post"));
            if (wel != null && wel.size() > 0) {
                for (WebElement we : wel) {
                    if (checkFilter(we, Filter.filterMap.get(url))) {
                        try {
                            Article article = new Article();
                            WebElement elText = we.findElement(By.cssSelector("div.wall_post_text"));
                            if (elText != null) {
                                article.setArticle(elText.getText());
                            }
                            List<WebElement> imgs = we.findElements(By.cssSelector(".page_post_sized_thumbs a.page_post_thumb_wrap"));
                            if (imgs != null && imgs.size() > 0) {
                                List<String> lI = getAllURsImagesFromElement(imgs);
                                article.setImages(lI.toArray(new String[lI.size()]));
                            }
                            artticles.add(article);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artticles;
    }

    private List<String> getAllURsImagesFromElement(List<WebElement> imgs) {
        List<String> lI = new ArrayList<String>();
        if (imgs != null && imgs.size() > 0) {
            for (WebElement wed : imgs) {
                String l = wed.getAttribute("style");
                if (isURLtoContent(l)) {
                    lI.add(getURLfromStyleContent(l));
                }
            }
        }
        return lI;
    }

    private String getURLfromStyleContent(String l) {
        return l.substring(l.indexOf("url(") + 5, l.lastIndexOf(")") - 1);
    }

    private boolean isURLtoContent(String l) {
        return l != null && l.indexOf("url(") > -1 && l.lastIndexOf(")") > -1;
    }

    private boolean checkFilter(WebElement we, FilterArticle filter) {
        if (filter != null) {
            List<WebElement> lL = null;
            if (filter.getCountLike() != null && filter.getCountLike() > 0) {
                lL = we.findElements(By.cssSelector(".post_full_like .post_like span"));
                if (lL != null && lL.size() > 0) {
                    int cL = 0;
                    try {
                        cL = Integer.parseInt(lL.get(1).getText());
                    } catch (NumberFormatException e) {
                    }
                    if (cL < filter.getCountLike()) {
                        return false;
                    }
                } else {
                    //Not like
                    return false;
                }
            }
            if (filter.getCountPost() != null && filter.getCountPost() > 0) {
                lL = we.findElements(By.cssSelector(".post_full_like .post_share span"));
                if (lL != null && lL.size() > 0) {
                    int cL = 0;
                    try {
                        cL = Integer.parseInt(lL.get(1).getText());
                    } catch (NumberFormatException e) {
                    }
                    if (cL < filter.getCountPost()) {
                        return false;
                    }
                } else {
                    //Not post
                    return false;
                }
            }
        }
        return true;
    }

    private Random rnd = new Random(System.currentTimeMillis());

    private int getRandom(int min, int max) {
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }

    public void addFriendToVKAccount() {
        String url = "https://vk.com/friends?act=find&c%5Bage_from%5D=" + getVkActionSettings().getAgeforsearch()+ "&c%5Bage_to%5D="
                + getVkActionSettings().getAgeforsearch() + "&c%5Bbday%5D=" + getVkActionSettings().getbDay() +
                "&c%5Bbmonth%5D=" + getVkActionSettings().getBmonth() + "&c%5Bcity%5D=" + getVkActionSettings().getCity() +
                "&c%5Bcountry%5D=" + getVkActionSettings().getCountry() + "&c%5Bname%5D=1&c%5Bphoto%5D=" + getVkActionSettings().getPhoto()
                + "&c%5Bper_page%5D=40&c%5Bsection%5D=people&c%5Bsex%5D=" + getVkActionSettings().getSex() + "&c%5Bsort%5D=1";

        driver.navigate().to(url);

        waitLoadSelector(By.cssSelector("#results"), 2, driver);
        WebElement we = null;
        waitLoadSelector(By.cssSelector("#results button"), 1, driver);
        try {
            we = driver.findElement(By.cssSelector("#results button"));
        } catch (Exception e) {
        }
        if (we != null && !"".equals(we.getText().trim())) {
            List<WebElement> listWe = driver.findElements(By.cssSelector("div.people_row"));
            for (int i=0; i<2; i++) {
                try {
                    clickButton( listWe.get(i).findElements(By.cssSelector("button.flat_button")).get(0));

                    sleepOn(5000);
                } catch (Exception e) {
                    _log.debug("friend wasn't added");
                }
            }
        }
    }

    public void loginToSite(Account account) {
            loginToSite(account.getLogin(), account.getPassword());
    }

    public void loginToSiteFull(String login, String password) {
        driver.navigate().to(mobileSiteName);

        WebElement elementLogin = driver.findElement(By.id("email"));
//        WebElement elementLogin = driver.findElement(By.id("quick_email"));
        elementLogin.sendKeys(login);

        WebElement elementPass = driver.findElement(By.id("pass"));
//        WebElement elementPass = driver.findElement(By.id("quick_pass"));
        elementPass.sendKeys(password);

        WebElement elementButLogin = driver.findElement(By.id("login_button"));
//        WebElement elementButLogin = driver.findElement(By.id("quick_login_button"));
        elementButLogin.click();

//        WebElement element = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.top_profile_name")));


    }

    public void loginToSite(String login, String password) {
        driver.navigate().to(siteName);

        WebElement elementLogin = driver.findElement(By.id("index_email"));
//        WebElement elementLogin = driver.findElement(By.id("quick_email"));
        elementLogin.sendKeys(login);

        WebElement elementPass = driver.findElement(By.id("index_pass"));
//        WebElement elementPass = driver.findElement(By.id("quick_pass"));
        elementPass.sendKeys(password);

        WebElement elementButLogin = driver.findElement(By.id("index_login_button"));
//        WebElement elementButLogin = driver.findElement(By.id("quick_login_button"));
        elementButLogin.click();

//        WebElement element = (new WebDriverWait(driver, 5)).until(
//                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.top_profile_name")));

    }

    @Override
    public void logoutFromSite() {
        WebElement elementIcon = driver.findElement(By.cssSelector("li.mmi_logout a"));
        try {
            scrollToElement(elementIcon);

            sleepOn(1000);
            elementIcon.click();
        } catch (Exception e) {
            _log.debug("Can not use common method. Try use javascript for click");
            sleepOn(1000);
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("var evt = document.createEvent('MouseEvents');" +
                        "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
                        "arguments[0].dispatchEvent(evt);", elementIcon);
            } catch (Exception e1) {
                sleepOn(10000);
                _log.debug("Can not logout repeat attempt");
                logoutFromSite();
            }

        }
    }

    private void scrollToElement(WebElement elementIcon) {
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,0);", elementIcon);
//        sleepOn(500);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(elementIcon);
//        actions.perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" + "window.scrollBy(0,-100);", elementIcon);

//       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementIcon);
        sleepOn(500);
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
    }


    public static void waitLoadSelector(final By selector, int delay, WebDriver driver) {
        waitLoadSelector(selector,delay,driver, 0);
    }

    private static void waitLoadSelector(final By selector, int delay, WebDriver driver, int countDelay) {
        try {
            driver.findElement(selector);
        } catch (Exception e) {
            countDelay++;
            if (countDelay >= delay) {
                _log.debug("stop wait");
                return;
            }
            _log.debug("selector doesn't find yet");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
            }
            waitLoadSelector(selector, delay, driver, countDelay);
        }
    }


    public void tellRandomArticle(String groupURL) {
        tellRandomArticle(groupURL, "getAllButtonForSharing");
    }

    /**
     * Tell a random article any one shared it
     * @param groupURL groupURL
     */
    public void tellRandomArticleAnyOneSharedIt(String groupURL) {
        tellRandomArticle(groupURL, "getAllPressedShareButton");
    }

    @Override
    public void likeRandomArticle(String groupURL) {
        driver.navigate().to(mobileSiteName+"/"+groupURL);
        waitLoadSelector(By.cssSelector(".item_like"), 2, driver);
        int i = 0;
        while (i < 10) {
            scrollAndSleep();
            i++;
        }
        List<WebElement> likeBtns = getAllArticleForLike();

        if (likeBtns.size() > 0) {
            _log.debug("Element size: " + likeBtns.size());
            boolean d = false;
            int ind = 0;
            while (!d) {
                try {
                    clickRandomButton(likeBtns);
                    d = true;
                } catch (Exception e) {
                    ind++;
                    if (ind > 10) {
                        _log.debug("I didn't share this article");
                        return;
                    }
                    sleepOn(1000);
                    _log.debug("Page error: " + ind);
                }
            }
        }
    }

    private List<WebElement> getAllArticleForLike() {
        try {
            return driver.findElements(By.cssSelector("a.item_like:not(.item_sel)"));
        } catch (Exception e) {
            _log.debug("Don't find element", e);
            return new ArrayList<>();
        }
    }

    public void tellRandomArticle(String groupURL, String functionName) {
        driver.navigate().to(mobileSiteName+"/"+groupURL);
        waitLoadSelector(By.cssSelector("i.post_share_icon"), 2, driver);
        int i = 0;
        while (i < 10) {
            scrollAndSleep();
            i++;
        }
        List<WebElement> pressedShareBtns;
        if("getAllButtonForSharing".equals(functionName) ) {
            pressedShareBtns = getAllButtonForSharing();
        }else{
            pressedShareBtns = getAllPressedShareButton();
        }
        if (pressedShareBtns.size() > 0) {
            _log.debug("Element size: " + pressedShareBtns.size());
            boolean d = false;
            int ind = 0;
            while (!d) {
                try {
                    clickRandomButton(pressedShareBtns);
                    d = true;
                } catch (Exception e) {
                    ind++;
                    if (ind > 10) {
                        _log.debug("I didn't share this article");
                        return;
                    }
                    sleepOn(1000);
                    _log.debug("Page error: " + ind);
                }
            }
            // Wait for the page to load, timeout after 10 seconds
            waitLoadSelector(By.cssSelector("#publish_send_block:visible"), 2, driver);
            sleepOn(2000);
            WebElement elementButShare = driver.findElement(By.cssSelector("#publish_send_block input"));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if (elementButShare != null) {
                elementButShare.click();
            } else {
                _log.debug("I didn't share this article");
            }
            _log.debug("Page title is: " + driver.getTitle());
        }
    }



    private List<WebElement> getAllButtonForSharing() {
        try {
            return driver.findElements(By.cssSelector("a.item_share:not(.item_sel)"));
        } catch (Exception e) {
            _log.debug("Don't find element", e);
            return new ArrayList<>();
        }
    }

    /**
     * Sleep on millisec time
     *
     * @param millisecSleep sec for sleep
     */
    public static void sleepOn(int millisecSleep) {
        try {
            Thread.sleep(millisecSleep);
        } catch (InterruptedException e1) {
        }
    }

    private void clickRandomButton(List<WebElement> pressedShareBtns){
        WebElement element = pressedShareBtns.get(new Random().nextInt(pressedShareBtns.size()));
        clickButton(element);
    }

    private void clickButton(WebElement button){
        try {
            scrollToElement(button);
            sleepOn(1000);
            button.click();
            _log.debug("click has executed");
        } catch (Exception e) {
            _log.debug("Can not use common method. Try use javascript for click");

            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("var evt = document.createEvent('MouseEvents');" +
                        "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
                        "arguments[0].dispatchEvent(evt);", button);
                _log.debug("javascript click has executed");
            } catch (Exception e1) {
                _log.debug("Not possible click " + button.getTagName());
                throw new RuntimeException("Not possible click " + button.getTagName());
            }

        }
    }

    private List<WebElement> getAllSharedBtns() {
        try {
            return driver.findElements(By.cssSelector("a.item_share:not(.item_sel) b.v_share"));
        } catch (Exception e) {
            _log.debug("Don't find element", e);
            return new ArrayList<>();
        }
    }

    private List<WebElement> getAllPressedShareButton() {
        List<WebElement> elementsShareBut2 = new ArrayList<>();
        for (WebElement el : getAllSharedBtns()) {
            if (isBtnPressed(el)) {
                elementsShareBut2.add(el.findElement(By.xpath("..")));
            }
        }
        return elementsShareBut2;
    }

    private void scrollAndSleep() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,1550)", "");
        sleepOn(1000);
    }

    private boolean isBtnPressed(WebElement el) {
        return el.getCssValue("opacity") != "1";
    }

    public void addArticle(String groupURL, String article) {
        addArticle(groupURL, article, null);
    }

    public void addArticle(String groupURL, String article, String[] images) {
        driver.navigate().to(groupURL);
        waitLoadSelector(By.id("post_field"), 2, driver);
        WebElement elementArt = null;
        int ind = 0;
        boolean d = false;
        while (!d) {
            elementArt = driver.findElement(By.id("post_field"));
            try {
                elementArt.click();
                d = true;
            } catch (Exception e) {
                if (ind > 9) {
                    _log.debug("addArticle жопа");
                    return;
                }
                sleepOn(1000);
                ind++;
                _log.debug("Attempt #" + ind);
            }
        }
        if (images != null) {
            for (String image : images) {
                elementArt.sendKeys(image);
                sleepOn(1000);
                elementArt.sendKeys(" ");
                sleepOn(5000);
                elementArt.clear();
            }
        }

        sleepOn(500);
        elementArt.sendKeys(article);
        sleepOn(1000);
//        ind = 0;
//        d = false;
//        WebElement butEl = driver.findElement(By.cssSelector("button.flat_button.addpost_button"));
        sleepOn(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(" wall.sendPost()", "");
        sleepOn(2000);
        _log.debug("addArticleS");
    }


    /**
     * Method for change word from changeSent on value from this map in doc
     *
     * @param doc        document
     * @param changeSent map for change
     * @return change doc
     */
    public static String changeV(String doc, Map<String, String> changeSent) {
        for (String sent : changeSent.keySet()) {
            try {
                doc = doc.replaceAll(sent, changeSent.get(sent));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doc;
    }

    public VKActionSettings getVkActionSettings() {
        return vkActionSettings;
    }

    public void setVkActionSettings(VKActionSettings vkActionSettings) {
        this.vkActionSettings = vkActionSettings;
    }
}
