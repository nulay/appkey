package by.imix.razborImage.algoritm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Created by miha on 01.06.2016.
 */
public class SearchImgInImg {
//    public static String bigBmp = "examples/9.bmp";
//    public static String[] smallBmp = {"Cs/Ac.bmp", "Cs/Kc.bmp", "Cs/Qc.bmp", "Cs/Jc.bmp", "Cs/Tc.bmp", "Cs/9c.bmp"
//            , "Cs/8c.bmp", "Cs/7c.bmp", "Cs/6c.bmp", "Cs/5c.bmp", "Cs/4c.bmp", "Cs/3c.bmp", "Cs/2c.bmp"};

    public static int count = 0;
//    static MyImage bigImage = new MyImage(bigBmp);
//    static MyImage smallImage = new MyImage(smallBmp[count]);

    // The matching position if any or null
//    static MatrixPosition match = matrixMatch(bigImage.rgbData, smallImage.rgbData);

    public static MatrixPosition matrixMatchBI(BufferedImage big, BufferedImage small) {
        int width = big.getWidth();
        int height = big.getHeight();
        int[] pixels = new int[width * height];
        int[][] output = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                output[i][y]=big.getRGB(i, y);
            }
        }

        int widthS = small.getWidth();
        int heightS = small.getHeight();
        int[] pixelsS = new int[widthS * heightS];
        int[][] outputS = new int[widthS][heightS];

        for (int i = 0; i < widthS; i++) {
            for (int y = 0; y < heightS; y++) {
                outputS[i][y]=small.getRGB(i, y);
            }
        }


        return matrixMatch(output, outputS);
    }

    public static MatrixPosition matrixMatch(int[][] big, int[][] small) {
        int firstElem = small[0][0];

        __linsescan:
        for (int i = 0; i < big.length - small.length + 1; i++) {
            // Scan column by column
            __columnscan:
            for (int j = 0; j < big[0].length - small[0].length; j++) {
                if (big[i][j] != firstElem) continue __columnscan; // No first match
                // There is a match for the first element in small
                // Check if all the elements in small matches those in big
                for (int ii = 0; ii < small.length; ii++)
                    for (int jj = 0; jj < small[0].length; jj++)
                        // If there is at least one difference, there is no match
                        if (big[i + ii][j + jj] != small[ii][jj]) continue __columnscan;
                // If arrived here, then the small matches a region of big
                MatrixPosition result = new MatrixPosition();
                result.line = i;
                result.column = j;
                //System.out.println("Matching at line=" + result.line + ", column=" + result.column);
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        for (int x = 0; x < 13; x++) {
//            if (match == null) {
//                System.out.println("No Match Found!");
//                count++;
//            } else {
//                System.out.println("Matching at column(x)=" + match.column + " and line(y)=" + match.line);
//                count++;
//            }
//        }
    }

    static class MyImage {
        // Integers representing the image pixels
        int[][] rgbData = null;
        // The AWT image
        Image img = null;

        // Adapted from http://www.javaworld.com/javaworld/javatips/jw-javatip43.html
        MyImage(String bmpFileName) {
            // Just print the filename before loading
            java.io.File file = new java.io.File(bmpFileName);
            try {
                file.getCanonicalFile();
            } catch (Exception exc) {
            }

            // Load the image and the array of integers
            try {
                FileInputStream fs = new FileInputStream(bmpFileName);
                int bflen = 14;                 // 14 byte BITMAPFILEHEADER
                byte bf[] = new byte[bflen];
                fs.read(bf, 0, bflen);          // Read the file header
                int bilen = 40;                   // 40-byte BITMAPINFOHEADER
                byte bi[] = new byte[bilen];
                fs.read(bi, 0, bilen);          // Read the bitmap header

                // Interperet data.
                int nsize = (((int) bf[5] & 0xff) << 24)
                        | (((int) bf[4] & 0xff) << 16)
                        | (((int) bf[3] & 0xff) << 8)
                        | (int) bf[2] & 0xff;

                int nbisize = (((int) bi[3] & 0xff) << 24)
                        | (((int) bi[2] & 0xff) << 16)
                        | (((int) bi[1] & 0xff) << 8)
                        | (int) bi[0] & 0xff;

                int nwidth = (((int) bi[7] & 0xff) << 24)
                        | (((int) bi[6] & 0xff) << 16)
                        | (((int) bi[5] & 0xff) << 8)
                        | (int) bi[4] & 0xff;

                int nheight = (((int) bi[11] & 0xff) << 24)
                        | (((int) bi[10] & 0xff) << 16)
                        | (((int) bi[9] & 0xff) << 8)
                        | (int) bi[8] & 0xff;

                int nbitcount = (((int) bi[15] & 0xff) << 8) | (int) bi[14] & 0xff;

                int nsizeimage = (((int) bi[23] & 0xff) << 24)
                        | (((int) bi[22] & 0xff) << 16)
                        | (((int) bi[21] & 0xff) << 8)
                        | (int) bi[20] & 0xff;

                if (nbitcount != 24) {
                    System.err.println("Use only 24bit color .bmp files");
                    System.exit(1);
                }

                int npad = (nsizeimage / nheight) - nwidth * 3;
                int ndata[] = new int[nheight * nwidth];
                this.rgbData = new int[nheight][nwidth]; // [line][column]
                byte brgb[] = new byte[(nwidth + npad) * 3 * nheight];

                fs.read(brgb, 0, (nwidth + npad) * 3 * nheight); // Read the bitmap

                int nindex = 0; // current postion in brgb
                for (int j = 0; j < nheight; j++) { // by lines, from bottom to top
                    for (int i = 0; i < nwidth; i++) { // by columns
                        int rgbValue = (255 & 0xff) << 24
                                | (((int) brgb[nindex + 2] & 0xff) << 16)
                                | (((int) brgb[nindex + 1] & 0xff) << 8)
                                | (int) brgb[nindex] & 0xff;
                        ndata[nwidth * (nheight - j - 1) + i] = rgbValue;
                        this.rgbData[nheight - j - 1][i] = rgbValue;

                        nindex += 3;
                    }
                    nindex += npad;
                }
            } catch (Exception e) {
                System.out.println("Caught exception in loadbitmap!");
                System.exit(3);
            }
        }
    }

    public static class MatrixPosition {
        int line = -1;
        int column = -1;
    }
}
