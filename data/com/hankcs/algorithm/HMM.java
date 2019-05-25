package com.hankcs.algorithm;

import DATA.Gdistance;
import DATA.Ppoint;
import DATA.Street;
import DATA.pointToLine.*;
import DATA.Gdistance.*;
import DATA.Ppoint.*;
import DATA.States.*;
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class HMM {

	public double observations;
	public int states;
	public double pi[];
	public double a[][];
	public double b[][];

	public HMM(double observations, int states) {
		this.observations = observations;
		this.states = states;

		pi = new double[2];
		a = new double[2][2];
		b = new double[2][4];
	}

	public static double b(double sigma, double x) {
		
		return (1.0 / (sqrt(2.0 * PI) * sigma)) * exp(-0.5 * (pow(x / sigma, 2)));
	}
	
	public static double pi(double sigma, double x) {
		return 1.0 / (sqrt(2.0 * PI) * sigma) * exp(-0.5 * pow(x / sigma, 2));
	}

	public static double a(double gps, double shadowDistance) {
		return gps/shadowDistance;
	}

	public static void main(String[] args) {
		System.out.println(HMM.b(4.07, 0.01));
	}
	//public static double GetSigma(double x) {
		// double
		// x[]={0.6391395156184588,0.6391500216326351,0.6391759759205592,0.6392176702227509};//测量点到路段的距离;
		// double x1[]={2.2815,3.0484,4.1989}//GPS测量点之间的距离
		// double x2[]={1.2699,0.6841,0.2777}//投影点之间的距离
		// double (x1-x2)={1.0116,2.3643,3.9212}
		//double Sigma = 0.9476230619861829;// 高斯误差

		//return Sigma;
	//}

	/*public static double GetBeta(double x1, double x2) {
		double Beta = 0.0;
		Beta = 1 / Math.log(2) * 2.3643;// x2表示投影点的距离，x1表示GPS点之间的距离，median(x2-x1)=2.3643。
										// Beta=3.4109
		return Beta;
	}*/

	/*public static void main(String[] args) {
		
		List<List<double[]>> projects=Ppoint.getProjection();
        List<List<Double>> distance=new ArrayList<>();
        for(List<double[]> l : projects){
        	List<Double> distance_re=new ArrayList<>();
        	for(int i=0;i<l.size()-1;i++){
        		double[] a=l.get(i);
        		double[] b=l.get(i+1);
        		double di=Gdistance.getDistance(a[0], a[1], b[0], b[1]);
        		distance_re.add(di);
        	}
        	distance.add(distance_re);
        }
        
        for(List<Double> d : distance){
        	for(int i=0;i<d.size();i++){
        		double l=d.get(i);
        		System.out.println(l);
        		Double re=HMM.b(20, l);
        		System.out.println(re);
        	    d.set(i, re);
        	}
        }
        

        
		double obzervations[][] = { { 47.667483, -122.107083 },
				{ 47.6675, -122.107066 }, { 47.667516, -122.107033 },
				{ 47.667533, -122.106983 } };

		 re-estimation of initial state probabilities 

		for (int i = 0; i < 2; i++) {
			double pi[][] = {};
			System.out.println("pi=" + pi);
		}

		 re-estimation of transition probabilities 
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				double b[][] = {};
				double Sigma = 0.9476230619861829;

				System.out.println("b=" + b);

			}

		}

		 re-estimation of emission probabilities 
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 4; k++) {
				double Beta = 0.0;// 需要吧beta的值代入，计算beta的算法
				double a[][] = {};
				System.out.println("a=" + a);

			}

		}
	}*/

}
