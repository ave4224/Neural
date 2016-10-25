/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neuralcost;

/**
 *
 * @author averycowan
 */
public class Network {

    public float[][] x;
    public float[] x_ = null;
    public float[] y_ = null;
    public float[] y = null;

    public float[][] b;
    public float[][][] w;

    public float crossEntropy;

    public float[][] inputs;
    public float[][] outputs;

    public Network(float[][] inputs, float[][] outputs, int[] layers) {
        this.b = new float[layers.length][];
        this.w = new float[layers.length][][];
        this.x = new float[layers.length + 1][];
        this.y = new float[outputs[0].length];

        //Create Bias Variables
        for (int i = 0; i < layers.length; i++) {
            b[i] = new float[layers[i]];
        }

        //Create Weight Variables
        w[0] = new float[layers[0]][inputs[0].length];
        for (int i = 1; i < layers.length; i++) {
            w[i] = new float[layers[i]][layers[i - 1]];
        }

        //Create X Variables
        x[0] = new float[inputs[0].length];
        for (int i = 0; i < layers.length; i++) {
            x[i + 1] = new float[layers[i]];
        }
    }

    public void calcX() {
        y = x[x.length - 1].clone();
        Tensor.exp(y);
        Tensor.normalize(y);
        crossEntropy = 0f;
        for (int i = 0; i < y_.length; i++) {
            crossEntropy -= y_[i] * Math.log(y[i]);
        }
    }
}
