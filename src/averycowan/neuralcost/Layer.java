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
public class Layer {

    public Layer(int width, float[] x) {
        this.x = x;
        this.w = new float[width][x.length];
        this.b = new float[width];
        this.y = new float[width];
        for (float[] f : w) {
            Tensor.randfill(f);
            Tensor.normalize(f);
            Tensor.scale(f, 0.1f);
        }
        Tensor.randfill(b);
        Tensor.normalize(b);
        Tensor.scale(b, 0.1f);
    }

    public float[] x;

    public float[][] w;
    public float[] b;

    public float[] y;

    public void calc() {
        for (int i = 0; i < w.length; i++) {
            y[i] = -b[i];
            for (int j = 0; j < w[i].length; j++) {
                y[i] += x[j] * w[i][j];
            }
        }
        Tensor.exp(y);
        Tensor.normalize(y);
    }
}
