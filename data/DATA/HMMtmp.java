package DATA;

import java.util.Arrays;

import DATA.States;
import DATA.Ppoint;
import DATA.Gdistance;
import DATA.pointToLine;
import DATA.DP;
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class HMMtmp {
	public double observations;
	public double States;
	public double Gdistance;
	public double start_p[];
	public double trans_p[][];
	public double emit_p[][];

	public HMMtmp(double observations, int states, double[] start_p,
			double[][] trans_p, double[][] emit_p) {
		this.observations = observations;
		this.States = states;

		start_p = new double[2];
		trans_p = new double[2][2];
		emit_p = new double[2][4];
	}

	static double[] start_p(double sigma, double[] d, int i) {
		double[] temp = new double[d.length];
		temp[i] = 1.0 / (sqrt(2.0 * PI) * sigma)
				* exp(-0.5 * pow(d[i] / sigma, 2));
		return temp;
	}

	static double emit_p(double sigma, double[][] d1, int i, int j) {
		double[][] tempE = new double[d1.length][d1[i].length];
		tempE[i][j] = 1.0 / (sqrt(2.0 * PI) * sigma)
				* exp(-0.5 * pow(d1[i][j] / sigma, 2));
		return tempE[i][j];
	}

	static double trans_p(double beta, double[][] d2, int i, int k) {
		double[][] tempT = new double[d2.length][d2[i].length];
		tempT[i][k] = 1.0 / beta * exp(-d2[i][k] / beta);
		return tempT[i][k];
	}

	/*
	 * public static double GetSigma(double[] x) { double[]
	 * tempX=start_p(0.9476230619861829,x); //double[]
	 * d={0.6391395156184588,0.6448493625684949};//第一个GPS点到两个道路的距离 //double[]
	 * d1=
	 * {0.6391395156184588,0.6391500216326351,0.6391759759205592,0.6392176702227509
	 * };//测量点到路段r1的距离;
	 * //double[]d1={0.6448493625684949,0.6448597519375775,0.6448855662611742
	 * ,0.6449270853398836}GPs点到r2的距离 // double
	 * x1[]={2.2815,3.0484,4.1989}//GPS测量点之间的距离
	 * 
	 * 
	 * double(x1-x2)={0.4564,0.1528,0.0046}在r2上的投影点的距离 double
	 * (x1-x2)={1.0116,2.3643,3.9212}在r1上的距离 double beta=1.05893816 double Sigma
	 * = 0.91682386;// 高斯误差
	 * 
	 * 
	 * }
	 */

	public static void main(String[] args) {
		// double obzervations[][] = { { 47.667483, -122.107083 },{ 47.6675,
		// -122.107066 }, { 47.667516, -122.107033 },
		// { 47.667533, -122.106983 } };

		/* re-estimation of initial state probabilities */

		double[] d = { 0.6391395156184588, 0.6448493625684949 };
		for (int i = 0; i < d.length; i++) {

			double[] temp = start_p(0.91682386, d, i);

			System.out.println("temp=" + Arrays.toString(temp));
		}

		/* re-estimation of transition probabilities */
		double[][] d2 = { { 1.0116, 2.3643,  },
				{  0.1528, 0.0046 } };
		for (int i = 0; i < d2.length; i++) {
			for (int k = 0; k < d2[i].length; k++) {
				double tempT = trans_p(1.05893816, d2, i, k);
				//double Beta = 0.0;// 需要吧beta的值代入，计算beta的算法
				//double Sigma = 0.9476230619861829;

				System.out.println("tempT=" + tempT);

			}

		}

		/* re-estimation of emission probabilities */
		double[][] d1 = {
				{ 0.6391395156184588, 0.6391500216326351, 0.6391759759205592,
						0.6392176702227509 },
				{ 0.6448493625684949, 0.6448597519375775, 0.6448855662611742,
						0.6449270853398836 } };

		for (int i = 0; i < d1.length; i++) {
			for (int j = 0; j < d1[i].length; j++) {
				double tempE = emit_p(0.9476230619861829, d1, i, j);

				System.out.println("tempE=" + tempE);
			}
		}
	}
}
			

		
	




