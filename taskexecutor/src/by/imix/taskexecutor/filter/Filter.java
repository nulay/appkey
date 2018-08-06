package by.imix.taskexecutor.filter;

import by.imix.taskexecutor.temp.FilterArticle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miha on 30.05.2018.
 */
public class Filter {


    public static Map<String, String> strCh = new HashMap<>();
    public static Map<String, FilterArticle> filterMap = new HashMap<>();


    {
        strCh.put("пизд", "п&зд");
        strCh.put("Пизд", "П&зд");
        strCh.put("хуй", "х4й");
        strCh.put("Хуй", "Х4й");
        strCh.put("ебат", "@бат");
        strCh.put("Ебат", "@бат");
        strCh.put("ебан", "@бан");
        strCh.put("Ебан", "@бан");
        strCh.put("&nbsp;", "");

        strCh.put("anekdotov.net", "");
        strCh.put("анeкдотoв.net", "");
        strCh.put("анекдотов.nеt", "");
        strCh.put("aнекдотов.nеt", "");
        strCh.put("aнекдoтов.net", "");
        strCh.put("aнекдотoв.net", "");
        strCh.put("aнекдoтов.nеt", "");
        strCh.put("aнeкдoтов.nеt", "");
        strCh.put("анeкдoтов.net", "");
        strCh.put("aнeкдoтoв.nеt", "");
        strCh.put("анeкдотов.nеt", "");
        strCh.put("анeкдoтoв.net", "");
        strCh.put("анeкдoтoв.nеt", "");
        strCh.put("анeкдoтов.nеt", "");
        strCh.put("aнeкдoтoв.net", "");
        strCh.put("aнекдотoв.nеt", "");
        strCh.put("анекдoтов.nеt", "");
        strCh.put("анекдoтов.net", "");
        strCh.put("анeкдотов.nеt", "");
        strCh.put("анекдотoв.nеt", "");
        strCh.put("анекдотoв.net", "");
        strCh.put("aнeкдoтов.net", "");
        strCh.put("aнекдoтoв.net", "");
        strCh.put("aнeкдотoв.net", "");


        strCh.put("ебу", "@бу");
        strCh.put("Ебу", "@бу");
        strCh.put("еби", "@би");
        strCh.put("Eби", "@би");
        strCh.put("бля", "бл9");
        strCh.put("Бля", "Бл9");

        filterMap.put("https://vk.com/i.love.smile", new FilterArticle(2, 0));

    }
}
