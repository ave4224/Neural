/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.util;

import averycowan.util.Binary;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author averycowan
 */
public class MNIST {

    public static final double[][] LABELS;
    public static final double[][] DATA;
    public static final byte[][] LABEL_BYTES;
    public static final byte[][] DATA_BYTES;

    static {
        try {
            RandomAccessFile imgfile = new RandomAccessFile("data/train-images-idx3-ubyte", "r");
            byte[] imgdata = new byte[(int) imgfile.length()];
            imgfile.read(imgdata);
            RandomAccessFile lblfile = new RandomAccessFile("data/train-labels-idx1-ubyte", "r");
            byte[] labels = new byte[(int) lblfile.length()];
            lblfile.read(labels);
            int imgmagic = (Binary.mergeBytes(imgdata, 0));
            int imgs = (Binary.mergeBytes(imgdata, 4));
            int rows = (Binary.mergeBytes(imgdata, 8));
            int cols = (Binary.mergeBytes(imgdata, 12));
            int pixels = rows * cols;
            byte[][] data = new byte[imgs][pixels];
            int ind = 16;
            int test = 59999;
            for (int im = 0; im < imgs; im++) {
                for (int l = 0; l < pixels; l++) {
                    data[im][l] = imgdata[ind];
                    ind++;
                }
            }
            labels = Arrays.copyOfRange(labels, 8, labels.length);
            DATA_BYTES = data;
            LABEL_BYTES = new byte[labels.length][10];
            for (int i = 0; i < LABEL_BYTES.length; i++) {
                LABEL_BYTES[i][labels[i]] = 1;
            }
            DATA = uByteToDouble(DATA_BYTES);
            LABELS = uByteToDouble(LABEL_BYTES);
            Tensor.scale(DATA, 1.0d / 256d);
        } catch (IOException ex) {
            Logger.getLogger(MNIST.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("MNIST Files are missing!");
        }
    }

    public static void print(int image) {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                System.out.print((int) (MNIST.DATA[image][i * 28 + j] * 8));
            }
            System.out.println();
        }
    }

    public static double[][] uByteToDouble(byte[][] data) {
        double[][] f = new double[data.length][];
        for (int i = 0; i < data.length; i++) {
            f[i] = new double[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                f[i][j] = (double) Byte.toUnsignedInt(data[i][j]);
            }
        }
        return f;
    }
}
