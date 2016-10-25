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
    public float[][] y;
    public float[] x_ = null;
    public float[] y_ = null;
    public float[] in = null;
    public float[] out = null;

    public float[][] b;
    public float[][][] w;

    public float crossEntropy;

    public float[][] inputs;
    public float[][] outputs;

    public Network(float[][] inputs, float[][] outputs, int[] layers) {
        this.b = new float[layers.length][];
        this.w = new float[layers.length][][];
        this.x = new float[layers.length][];
        this.y = new float[layers.length][];

        this.inputs = inputs;
        this.outputs = outputs;

        //Create Bias Variables
        for (int i = 0; i < layers.length; i++) {
            b[i] = new float[layers[i]];
        }
        Tensor.scale(b, Tensor.randfill(b));

        //Create Weight Variables
        w[0] = new float[layers[0]][inputs[0].length];
        for (int i = 1; i < layers.length; i++) {
            w[i] = new float[layers[i]][layers[i - 1]];
            Tensor.scale(w[i], Tensor.randfill(w[i]));
        }

        //Create X Variables
        for (int i = 0; i < layers.length; i++) {
            x[i] = new float[layers[i]];
        }

        //Create Y Variables
        y[0] = new float[inputs[0].length];
        for (int i = 1; i < layers.length; i++) {
            y[i + 1] = new float[layers[i]];
        }
    }

    public void calcX() {
        crossEntropy = Tensor.dotProduct(
                y_,
                Tensor.log(out.clone())
        );
    }

    public void runTraining() {
        for (int test = 0; test < inputs.length; test++) {
            x_ = inputs[test];
            y_ = outputs[test];
            y[0] = x_;
            run();
            calcX();
            // TODO: backpropogate
        }
    }

    public void run() {
        for (int layer = 0; layer < x.length; layer++) {
            for (int neuron = 0; neuron < x[layer].length; neuron++) {
                x[layer][neuron] = Tensor.dotProduct(y[layer], w[layer][neuron]) - b[layer][neuron];
            }
            y[layer + 1] = x[layer].clone();
            //Tensor.exp(y[layer]);
            //Tensor.normalize(y[layer]);
        }
        out = y[y.length - 1];
    }
}
