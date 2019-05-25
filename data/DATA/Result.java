package DATA;

import org.junit.Test;


public class Result {
	
	static enum States//枚举值是常量不是变量
    {
        r1,
        r2,
    }
    static enum Observations
    {
        Z1,
        Z2,
        Z3,
        Z4,
    }
    static int[] states = new int[]{Result.States.r1.ordinal(),Result.States.r2.ordinal()};
    static int[] observations = new int[]{Result.Observations.Z1.ordinal(),Result.Observations.Z2.ordinal(),Result.Observations.Z3.ordinal(),Result.Observations.Z4.ordinal()};
    static double[] start_probability = new double[]{0.3412672938015292,0.3397822749812881};
    
    static double[][] transititon_probability = new double[][]{
        {0.3632865916012296,0.10126899838347522},
        {0.8174527634742608,0.9402489067468585},
    };
/*    {0.3353460680391628,0.3353435604431364},
    {0.3353435604431364,0.3353274134071775},
    {0.33397993941126997,0.333977447695695},
    {0.33397125647069514,0.3339612983882448},*/
  
    static double[][] emission_probability = new double[][]{
    	{0.3353460680391628,0.3353435604431364,0.33397993941126997,0.33397125647069514},
    	{0.3353435604431364,0.3353274134071775,0.333977447695695,0.3339612983882448}
        
    };
    
    /*@Test
    public void dd(){//
    	double[][] datas=new double[Result.emission_probability.length][Result.emission_probability[0].length];

    	for(int j=0;j<Result.emission_probability[0].length;j++){
    		for(int i=0;i<Result.emission_probability.length;i++){
    			datas[i][j]=Result.emission_probability[i][j]*Result.start_probability[j];
    		}
    	}
    	
    }*/
    
    public static void main(String[] args)
    {
        int[] result = Viterbi.compute(observations, states, start_probability, transititon_probability, emission_probability);
        for(double r : result)
        {
            System.out.print(r+",");
        }
        System.out.println();
    }
}


