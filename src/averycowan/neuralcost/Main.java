/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neuralcost;

import java.util.Arrays;

/**
 *
 * @author averycowan
 */
public class Main {

    public static void main(String[] args) {
        int[] widths = {64, 7};
        double[][] inputs = new double[50000][7];
        double[][] outputs = new double[inputs.length][7];
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
        System.out.println("Generated Data");
        Network nn = new Network(inputs, outputs, widths);
        System.out.println("Constructed");
        nn.runTraining();
        System.out.println("[   x[0]   ,   x[1]   ,   x[2]   ,   x[3]   ,   x[4]   ,   x[5]   ,   x[6]   ]");
        print(nn.y_, true);
        print(nn.out, true);
        System.out.println("[   x[0]  ,   x[0]   , x[2] / 10, x[3] * 2 ,  x[4] - 3 , x[5]-x[4],     0    ]");
    }

    public static void print(double[] arr, boolean unsig) {
        System.out.print("[" + unsig(arr[0], unsig));
        for (int i = 1; i < arr.length; i++) {
            System.out.print(", " + unsig(arr[i], unsig));
        }
        System.out.println("]");
    }

    public static String unsig(double sig, boolean unsig) {
        return String.format("%1$,.7f", -Math.log((1.0d / sig) - 1));
    }
}
