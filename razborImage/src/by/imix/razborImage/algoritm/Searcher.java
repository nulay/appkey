package by.imix.razborImage.algoritm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 04.01.14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class Searcher {
    private static final Logger _log = LoggerFactory.getLogger(Searcher.class);
    private List seartherObject;//разыскиваемые объекты

    public void equilsTwoOb(BufferedImage bi1,BufferedImage bi2){
        BufferedImage r1=bi1.getSubimage(bi1.getWidth()/2,0,1,bi1.getHeight());
        BufferedImage r2=bi2.getSubimage(bi2.getWidth()/2,0,1,bi2.getHeight());
        popixSravn(r1,r2);
    }

    public int popixSravn(BufferedImage bi1,BufferedImage bi2){
        int c=0;
        for(int i=140;i<450;i++){
            int[] p1=bi1.getRaster().getPixel(0,i,new int[3]);
            for (int y=(i-20<140)?140:i-20;y<((i+20>450)?450:i+20);y++){
                int[] p2=bi2.getRaster().getPixel(0,y,new int[3]);
                if(p1[0]==p2[0] && p1[1]==p2[1] && p1[2]==p2[2]){
                    c++;
                }
            }
        }
        return c;
    }


    public List searthSection(){
        return null;
    }
}
