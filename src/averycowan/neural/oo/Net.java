/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.oo;

import averycowan.util.Pointer;
import java.util.Arrays;

/**
 *
 * @author Avery Cowan
 */
public class Net {

    public Neuron[][] neurons;
    public Pointer<byte[]> input = new Pointer<byte[]>(null);

    public Net(int[] sizes, int[] links) {
        if (sizes.length - 1 != links.length) {
            throw new IllegalArgumentException("r u dum || wut");
        }
        neurons = new Neuron[sizes.length][];
        neurons[0] = new InputNeuron[sizes[0]];
        for (int j = 0; j < sizes[0]; j++) {
            neurons[0][j] = new InputNeuron(input, j);
        }
        for (int i = 1; i < sizes.length - 1; i++) {
            neurons[i] = new Perceptron[sizes[i]];
            for (int j = 0; j < sizes[i]; j++) {
                neurons[i][j] = new Perceptron(neurons[i - 1], links[i - 1]);
            }
        }
        neurons[neurons.length - 1] = new OutputNeuron[sizes[sizes.length - 1]];
        for (int j = 0; j < sizes[sizes.length - 1]; j++) {
            neurons[neurons.length - 1][j] = new OutputNeuron(neurons[sizes.length - 2], links[sizes.length - 2]);
        }
    }

    public float[] train(byte[] question, byte answer) {
        input.$ = question;
        for (Neuron[] ns : neurons) {
            for (Neuron n : ns) {
                n.eval();
            }
        }
        float[] result = new float[neurons[neurons.length - 1].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = ((OutputNeuron) (neurons[neurons.length - 1][i])).read();
            neurons[neurons.length - 1][i].feedback(answer == i ? (byte) 1 : (byte) 0);
        }
        return result;
    }

    public String toString() {
        String str = "";
        for (Neuron[] ns : neurons) {
            for (Neuron n : ns) {
                str += n.toString() + " ";
            }
            str += "\n";
        }
        str += Arrays.toString(((Perceptron) neurons[neurons.length - 1][0]).weight) + "\n";
        return str;
    }
}
