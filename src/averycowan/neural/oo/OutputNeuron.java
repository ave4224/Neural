/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.oo;

import static averycowan.neural.oo.Perceptron.INCREMENT;

/**
 *
 * @author Avery Cowan
 */
public class OutputNeuron extends Perceptron {

    public OutputNeuron(Neuron[] base, int links) {
        super(base, links);
    }

    public float read() {
        float sum = 0;
        for (int i = 0; i < in.length; i++) {
            sum += in[i].out() * weight[i];
        }
        return sum / in.length;
    }

    @Override
    public void feedback(byte correct) {
        new Thread() {
            public void run() {
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
        }.start();
    }
}
