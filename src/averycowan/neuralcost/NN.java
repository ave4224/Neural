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
public class NN {

    public NN(int[] layerwidths, int inputwidth) {
        x = new float[inputwidth];
        layers = new Layer[layerwidths.length];
        layers[0] = new Layer(layerwidths[0], x);
        for (int i = 1; i < layerwidths.length; i++) {
            layers[i] = new Layer(layerwidths[i], layers[i - 1].y);
        }
        y_ = new float[layerwidths[layerwidths.length - 1]];
        y = layers[layerwidths.length - 1].y;
    }

    public void train(float[] input) {
        for (int i = 0; i < x.length; i++) {
            x[i] = input[i];
        }
        for (Layer l : layers) {
            l.calc();
        }
        calcX();
    }

    public Layer[] layers;

    public float[] x;

    public float[] y;

    public float[] y_;

    public float crossEntropy;

    public void calcX() {
        crossEntropy = 0f;
        for (int i = 0; i < y.length; i++) {
            crossEntropy -= y_[i] * Math.log(y[i]);
        }
    }
}
