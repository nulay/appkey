package by.imix.razborImage;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 08.01.14
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class FileOperation {
    private Logger _log=Logger.getLogger(FileOperation.class);

    public FileOperation() {
    }

    public boolean saveFile(String nameFile, Serializable saveObject){
        File file=new File(nameFile);
        return saveFile(file,saveObject);
    }

    public boolean saveFile(File nameFile, Serializable saveObject){
        try {
            FileOutputStream fos = new FileOutputStream(nameFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
            oos.flush();
            oos.close();
            return true;
        } catch (IOException e1) {
            _log.error(e1.getMessage());
        }
        return false;
    }

    public Object readObjectFromFile(String nameFile){
        File file=new File(nameFile);
        return readObjectFromFile(file);
    }

    public Object readObjectFromFile(File nameFile){
        try {
            FileInputStream fis = new FileInputStream(nameFile);
            ObjectInputStream oin = new ObjectInputStream(fis);
            return oin.readObject();
        } catch (IOException e) {
            _log.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            _log.error(e.getMessage());
        }
        return null;
    }
}
