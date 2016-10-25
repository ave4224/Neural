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
public class Tensor {

    public static float[] randfill(float[] f, float max) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += (f[i] = (float) Math.random() * max);
        }
        return f;
    }

    public static float[] randfill(float[] f) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += (f[i] = (float) Math.random());
        }
        return f;
    }

    public static float[] exp(float[] f) {
        for (int i = 0; i < f.length; i++) {
            f[i] = (float) Math.exp(f[i]);
        }
        return f;
    }

    public static float[] normalize(float[] f) {
        float sum = 0;
        for (int i = 0; i < f.length; i++) {
            sum += f[i];
        }
        for (int i = 0; i < f.length; i++) {
            f[i] /= sum;
        }
        return f;
    }

    public static float[] scale(float[] a, float s) {
        for (int i = 0; i < a.length; i++) {
            a[i] *= s;
        }
        return a;
    }
}
