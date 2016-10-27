/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.util;

/**
 *
 * @author averycowan
 */
public class Tensor {

    public static double randfill(double[] f, double max) {
        double sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += (f[i] = (double) Math.random() * max);
        }
        return sum;
    }

    public static double randfill(double[] f) {
        double sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += (f[i] = (double) Math.random());
        }
        return sum;
    }

    public static double randfill(double[][] f, double max) {
        double sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i], max);
        }
        return sum;
    }

    public static double randfill(double[][] f) {
        double sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i]);
        }
        return sum;
    }

    public static double randfill(double[][][] f, double max) {
        double sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i], max);
        }
        return sum;
    }

    public static double randfill(double[][][] f) {
        double sum = 0f;
        for (int i = 0; i < f.length; i++) {
            sum += randfill(f[i]);
        }
        return sum;
    }

    public static double[] exp(double[] f) {
        for (int i = 0; i < f.length; i++) {
            f[i] = (double) Math.exp(f[i]);
        }
        return f;
    }

    public static double[] normalize(double[] f) {
        double sum = 0;
        for (int i = 0; i < f.length; i++) {
            sum += f[i];
        }
        for (int i = 0; i < f.length; i++) {
            f[i] /= sum;
        }
        return f;
    }

    public static double[] scale(double[] a, double s) {
        for (int i = 0; i < a.length; i++) {
            a[i] *= s;
        }
        return a;
    }

    public static double[][] scale(double[][] a, double s) {
        for (int i = 0; i < a.length; i++) {
            scale(a[i], s);
        }
        return a;
    }

    public static double[][][] scale(double[][][] a, double s) {
        for (int i = 0; i < a.length; i++) {
            scale(a[i], s);
        }
        return a;
    }

    public static double sigmoid(double f) {
        return (double) (1f / (1f + Math.exp(-f)));
    }

    public static double[] sigmoid(double[] f) {
        for (int i = 0; i < f.length; i++) {
            f[i] = (double) (1f / (1f + Math.exp(-f[i])));
        }
        return f;
    }

    public static double[][] sigmoid(double[][] f) {
        for (int i = 0; i < f.length; i++) {
            sigmoid(f[i]);
        }
        return f;
    }

    public static double[] log(double[] f) {
        for (int i = 0; i < f.length; i++) {
            f[i] = (double) Math.log(f[i]);
        }
        return f;
    }

    public static double dotProduct(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public static int greatest(double[] d) {
        int ind = 0;
        double max = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i] > max) {
                ind = i;
                max = d[i];
            }
        }
        return ind;
    }
}
