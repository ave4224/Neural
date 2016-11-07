/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.deprecated;

import averycowan.util.Binary;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 *
 * @author Avery Cowan
 */
public class NeuralBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
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
        Net net = new Net(new int[]{784, 2000, 500, 200, 10}, new int[]{20, 10, 8, 40});
        float[] result = null;
        boolean[] correct = new boolean[imgs];
        for (int i = 0; i < imgs; i++) {
            result = net.train(data[i], labels[i]);
            int h = 0;
            for (int j = 1; j < result.length; j++) {
                if (result[j] > result[h]) {
                    h = j;
                }
            }
            correct[i] = (h == labels[i]);
            if (i % 1000 == 0) {
                System.out.println(i);
            }
            //System.out.println(i + ":" + labels[i] + h + correct[i]);
        }
        System.out.println(net);
        System.out.println(labels[test] + ":");
        int yay = 0, aww = 0;
        for (int i = imgs - 10000; i < imgs; i++) {
            if (correct[i]) {
                yay++;
                System.out.print(1);
            } else {
                aww++;
                System.out.print(0);
            }
        }
        System.out.println("\n" + yay + "\t" + aww);
//for (int i = 0; i < pixels; i++) {
        //    System.out.print((i + 1) % cols == 0 ? ((data[test][i] >> 5) & 7) + "\n" : ((data[test][i] >> 5) & 7));
        //}
    }
}
