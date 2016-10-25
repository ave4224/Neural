/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neuralbase;

/**
 *
 * @author avecowa
 */
public class Sigmoid implements Neuron {

    public static final int INCREMENT = 20;//1 means set weight to max/min
    public static final float BIAS = 7f;
    public final Neuron[] in;
    public final float[] weight;
    public float bias;
    public float out;

    public void eval() {
        float sum = 0;
        for (int i = 0; i < in.length; i++) {
            sum += in[i].out() * weight[i];
        }
        out = (float) (1.0 / (1.0 + Math.exp(sum - bias)));
    }

    public Sigmoid(Neuron[] base, int links) {
        in = new Neuron[links];
        weight = new float[links];
        bias = links / BIAS;
        for (int i = 0; i < links; i++) {
            int random = (int) (Math.random() * base.length);
            //System.out.println(random + " " + base.length);
            in[i] = base[random];
            weight[i] = 0.5f;
        }
    }

    public float out() {
        return out;
    }

    @Override
    public void feedback(byte correct) {
        for (int i = 0; i < in.length; i++) {
            //System.out.print(weight[i] + "->");
            if (correct == in[i].out()) {
                weight[i] += (1f - weight[i]) / INCREMENT;
                //System.out.print(weight[i] + " ");
            } else {
                weight[i] -= weight[i] / INCREMENT;
                //System.out.print(weight[i] + " ");
            }
            in[i].feedback(correct);
        }
    }

    @Override
    public void propagate() {
    }

    public String toString() {
        boolean a = false;
        String str = in[0].out() + "";
        for (int i = 1; i < in.length; i++) {
            a |= in[i].out() != 0;
            str += in[i].out();
        }
        return str + "";
    }
}
