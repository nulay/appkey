package by.imix.razborImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 08.01.14
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class FileOperation {
    private static final Logger _log = LoggerFactory.getLogger(FileOperation.class);

    public FileOperation() {
    }

    public boolean saveFile(String nameFile, Serializable saveObject){
        File file=new File(nameFile);
        return saveFile(file,saveObject);
    }


    public boolean saveFile(File file, Serializable saveObject, Class classObj) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(classObj);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(saveObject, file);
            jaxbMarshaller.marshal(saveObject, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Serializable readObjectFromFile(File nameFile, Class classObj){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classObj);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object object = jaxbUnmarshaller.unmarshal(nameFile);
            return (Serializable) object;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
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
