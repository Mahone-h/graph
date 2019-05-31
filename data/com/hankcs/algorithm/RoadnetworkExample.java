package com.hankcs.algorithm;

import DATA.Viterbi;

import static com.hankcs.algorithm.RoadnetworkExample.Point.*;
import static com.hankcs.algorithm.RoadnetworkExample.Road.*;




public class RoadnetworkExample
{
    static enum Road
    {
        a,
        b,
    }
    static enum Point
    {
        z1,
        z2,
        z3,
    }
    static int[] states = new int[]{a.ordinal(),b.ordinal()};
    static int[] observations = new int[]{z1.ordinal(),z2.ordinal(),z3.ordinal()};
    static double[] start_probability = new double[]{0.6,0.4};
    static double[][] transititon_probability = new double[][]{
        {0.7,0.3},
        {0.4,0.6},
    };
    static double[][] emission_probability = new double[][]{
        {0.1,0.4,0.5},
        {0.6,0.3,0.1},
    };
    public static void main(String[] args)
    {
        int[] result = Viterbi.compute(observations, states, start_probability, transititon_probability, emission_probability);
        for(int r : result)
        {
            System.out.print(r+",");
        }
        System.out.println();
    }
}