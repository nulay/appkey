package by.imix.taskexecutor.temp;

import by.imix.taskexecutor.sightbot.Account;

import java.util.List;

/**
 * Class for different action in VK site
 */
public interface VKAction {

    /**
     * Tell friends about random topic
     * @param groupURL group URL
     */
    void tellRandomArticle(String groupURL);

    /**
     * Tell a random article any one shared it
     * @param groupURL groupURL
     */
    void tellRandomArticleAnyOneSharedIt(String groupURL);

    /**
     * Like Random Article
     * @param groupURL group URL
     */
    void likeRandomArticle(String groupURL);

    /**
     * Login site
     * @param account account
     */
    void loginToSite(Account account);

    /**
     * Login sight
     * @param login login
     * @param password password
     */
    void loginToSite(String login, String password);

    /**
     * Logout site
     */
    void logoutFromSite();

    /**
     * Invite friends to you account
    */
    void addFriendToVKAccount();

    /**
     * Add article to group
     * @param groupURL groupURL
     * @param article who need to be add
     */
    void addArticle(String groupURL, String article);

    /**
     * Add article to group with images
     * @param groupURL groupURL
     * @param article who need to be add
     * @param images set imageUrl
     */
    void addArticle(String groupURL, String article, String[] images);

    /**
     * Method that find records
     */
    List<Article> findRecords(String url);
}
