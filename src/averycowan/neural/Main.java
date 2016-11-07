/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package averycowan.neural;

import averycowan.neural.util.MNIST;
import averycowan.neural.util.Tensor;
import java.util.Arrays;

/**
 *
 * @author averycowan
 */
public class Main {

    private static int[] widths = null;
    private static double[][] inputs = null;
    private static double[][] outputs = null;

    public static void main(String[] args) {
        System.out.println("Generating Dataset");
        generateMNIST();
        MNIST.print(0);
        System.out.println("Constructing Network");
        Network nn = new Network(inputs, outputs, widths);
        System.out.println("Training");
        int i = 0;
        while (i < 59000) {
            nn.runTrainingStep(i++);
        }
        int correct = 0;
        int wrong = 0;
        i = 59000;
        System.out.println("Testing");
        while (i < nn.inputs.length) {
            nn.runTrainingStep(i);
            if (Tensor.greatest(nn.y_) == Tensor.greatest(nn.out)) {
                correct++;
            } else {
                wrong++;
            }
            i++;
        }
        System.out.println("Accuracy: " + unsig((correct * 100d) / (wrong + correct), false) + "%");
    }

    public static void generateSimple() {
        widths = new int[]{64, 7};
        inputs = new double[50000][7];
        outputs = new double[inputs.length][7];
        Tensor.randfill(inputs);
        for (int i = 0; i < outputs.length; i++) {
            outputs[i][0] = inputs[i][0];
            outputs[i][1] = inputs[i][0];
            outputs[i][2] = inputs[i][2] / 10d;
            outputs[i][3] = inputs[i][3] * 2d;
            outputs[i][4] = inputs[i][4] - 3d;
            outputs[i][5] = inputs[i][5] - inputs[i][4];
            outputs[i][6] = 0;
            Tensor.sigmoid(outputs[i]);

        }
    }

    public static void generateMNIST() {
        widths = new int[]{256, 10};
        inputs = new double[MNIST.DATA.length][];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = MNIST.DATA[i].clone();
        }
        //Tensor.sigmoid(inputs);
        outputs = new double[MNIST.LABELS.length][];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = MNIST.LABELS[i].clone();
        }
    }

    public static void print(double[] arr, boolean unsig) {
        System.out.print("[" + unsig(arr[0], unsig));
        for (int i = 1; i < arr.length; i++) {
            System.out.print(", " + unsig(arr[i], unsig));
        }
        System.out.println("]");
    }

    public static String unsig(double sig, boolean unsig) {
        return String.format("%1$,.7f", sig);
    }
}
