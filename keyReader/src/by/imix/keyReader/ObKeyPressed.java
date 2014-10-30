package by.imix.keyReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 08.01.14
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class ObKeyPressed implements Serializable {
    private List<KeyPressed> listKP;//список нажатых кнопок
    private String title;//Название
    private String description;//Описание

    public ObKeyPressed() {
        listKP=new ArrayList<KeyPressed>();
    }

    public ObKeyPressed(List<KeyPressed> listKP) {
        this.listKP = listKP;
    }

    public List<KeyPressed> getListKP() {
        return listKP;
    }

    public void setListKP(List<KeyPressed> listKP) {
        this.listKP = listKP;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }
}
