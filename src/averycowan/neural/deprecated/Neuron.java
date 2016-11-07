/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averycowan.neural.deprecated;

/**
 *
 * @author Avery Cowan
 */
public interface Neuron {

    public float out();

    public void eval();

    public void feedback(byte correct);

    public void propagate();
}
