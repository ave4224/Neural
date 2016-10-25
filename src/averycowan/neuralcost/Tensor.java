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

    public static float randfill(float[] f, float max) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += (f[i] = (float) Math.random() * max);
        }
        return sum;
    }

    public static float randfill(float[] f) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += (f[i] = (float) Math.random());
        }
        return sum;
    }

    public static float randfill(float[][] f, float max) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i], max);
        }
        return sum;
    }

    public static float randfill(float[][] f) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i]);
        }
        return sum;
    }

    public static float randfill(float[][][] f, float max) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i], max);
        }
        return sum;
    }

    public static float randfill(float[][][] f) {
        float sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i]);
        }
        return sum;
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

    public static float[][] scale(float[][] a, float s) {
        for (int i = 0; i < a.length; i++) {
            scale(a[i], s);
        }
        return a;
    }

    public static float[][][] scale(float[][][] a, float s) {
        for (int i = 0; i < a.length; i++) {
            scale(a[i], s);
        }
        return a;
    }

    public static float sigmoid(float f) {
        return (float) (1f / (1f + Math.exp(-f)));
    }

    public static float[] sigmoid(float[] f) {
        for (int i = 0; i < f.length; i++) {
            f[i] = (float) (1f / (1f + Math.exp(-f[i])));
        }
        return f;
    }

    public static float[] log(float[] f) {
        for (int i = 0; i < f.length; i++) {
            f[i] = (float) Math.log(f[i]);
        }
        return f;
    }

    public static float dotProduct(float[] a, float[] b) {
        float sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
