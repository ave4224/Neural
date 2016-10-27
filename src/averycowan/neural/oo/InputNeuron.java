/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.oo;

import averycowan.util.Pointer;

/**
 *
 * @author Avery Cowan
 */
public class InputNeuron implements Neuron {

    private byte out = 0;
    public final Pointer<byte[]> data;
    public final int position;

    public InputNeuron(Pointer<byte[]> d, int p) {
        data = d;
        position = p;
    }

    @Override
    public float out() {
        return out;
    }

    @Override
    public void eval() {
        out = data.$[position] != 0 ? (byte) 1 : (byte) 0;
    }

    @Override
    public void feedback(byte correct) {
    }

    @Override
    public void propagate() {
    }

    public String toString() {
        return out() + "";
    }
}
