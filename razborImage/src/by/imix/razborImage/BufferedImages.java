package by.imix.razborImage;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 08.01.14
 * Time: 9:46
 * To change this template use File | Settings | File Templates.
 */
public class BufferedImages implements Serializable {
    List<BufferedImage> listImage;

    public BufferedImages(){
        listImage=new ArrayList<BufferedImage>();
    }

    public BufferedImages(List<BufferedImage> listImage) {
        this.listImage=listImage;
    }

    public boolean addImage(BufferedImage image){
        return listImage.add(image);
    }

    public boolean removeImage(BufferedImage image){
        return listImage.remove(image);
    }

    public List<BufferedImage> getListImage(){
        return listImage;
    }

    public boolean addListImage(List<BufferedImage> listImage){
        return this.listImage.addAll(listImage);
    }
}
