package com.hankcs.algorithm;

import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Test {

	//(1.0 / (sqrt(2.0 * PI) * sigma)) * exp(-0.5 * pow(x / sigma, 2)) 798651.0374
	public static void main(String[] args) {
		double d=sqrt(2* PI) * 20;
		double r= exp(-0.5 * pow( 1.2699/ 20, 2));
		System.out.println(d);
		System.out.println(r);
		System.out.println((1.0/d)*r);
				
	}
}


