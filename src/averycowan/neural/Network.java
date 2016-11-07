/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural;

import averycowan.neural.util.Tensor;
import java.util.Arrays;

/**
 *
 * @author averycowan
 */
public class Network {

    public static final double LEARNING_RATE = 0.5;

    public double[][] x;
    public double[][] y;
    public double[] x_ = null;
    public double[] y_ = null;
    public double[] in = null;
    public double[] out = null;

    public double[][] b;
    public double[][][] w;

    public double[][] inputs;
    public double[][] outputs;

    public Network(double[][] inputs, double[][] outputs, int[] layers) {
        this.b = new double[layers.length][];
        this.w = new double[layers.length][][];
        this.x = new double[layers.length][];
        this.y = new double[layers.length + 1][];

        this.inputs = inputs;
        this.outputs = outputs;

        //Create Bias Variables
        for (int i = 0; i < layers.length; i++) {
            b[i] = new double[layers[i]];
        }
        Tensor.scale(b, 1f / Tensor.randfill(b));

        //Create Weight Variables
        w[0] = new double[layers[0]][inputs[0].length];
        Tensor.scale(w[0], 1f / Tensor.randfill(w[0]));
        for (int i = 1; i < layers.length; i++) {
            w[i] = new double[layers[i]][layers[i - 1]];
            Tensor.scale(w[i], 1f / Tensor.randfill(w[i]));
        }

        //Create X Variables
        for (int i = 0; i < layers.length; i++) {
            x[i] = new double[layers[i]];
        }

        //Create Y Variables
        y[0] = new double[inputs[0].length];
        for (int i = 1; i < layers.length; i++) {
            y[i + 1] = new double[layers[i]];
        }
        System.out.println(Arrays.toString(w[0][0]));
    }

    public void runTraining() {
        for (int test = 0; test < inputs.length; test++) {
            runTrainingStep(test);
        }
    }

    public void runTrainingStep(int step) {
        x_ = inputs[step];
        y_ = outputs[step];
        y[0] = x_;
        run();
        backwards();
    }

    public void runTestingStep(int step) {
        x_ = inputs[step];
        y_ = outputs[step];
        y[0] = x_;
        run();
    }

    public void run() {
        for (int layer = 0; layer < x.length; layer++) {
            for (int neuron = 0; neuron < x[layer].length; neuron++) {
                x[layer][neuron] = Tensor.dotProduct(y[layer], w[layer][neuron]) + b[layer][neuron];
            }
            y[layer + 1] = x[layer].clone();
            Tensor.sigmoid(y[layer + 1]);

        }
        out = y[y.length - 1];
    }

    public void backwards() {
        int layer = x.length - 1;
        double[][] δ = new double[layer + 1][];
        δ[layer] = new double[x[layer].length];
        for (int neuron = 0; neuron < δ[layer].length; neuron++) {
            double o = y[layer + 1][neuron];
            double o_ = y_[neuron];
            δ[layer][neuron] = (o - o_) * o * (1f - o);
        }
        for (layer--; layer >= 0; layer--) {
            δ[layer] = new double[x[layer].length];
            for (int neuron = 0; neuron < δ[layer].length; neuron++) {
                double o = y[layer + 1][neuron];
                double sum = 0f;
                for (int neuron_ = 0; neuron_ < δ[layer + 1].length; neuron_++) {
                    sum += δ[layer + 1][neuron_] * w[layer + 1][neuron_][neuron];
                }
                δ[layer][neuron] = sum * o * (1 - o);
            }
        }

        for (layer = 0; layer < w.length; layer++) {
            for (int neuron = 0; neuron < w[layer].length; neuron++) {
                for (int neuron_ = 0; neuron_ < w[layer][neuron].length; neuron_++) {
                    double delta = LEARNING_RATE;
                    delta *= y[layer][neuron_];
                    delta *= δ[layer][neuron];
                    w[layer][neuron][neuron_] -= delta;
                }
            }
        }
    }
}
