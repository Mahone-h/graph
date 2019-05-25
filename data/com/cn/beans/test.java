package com.cn.beans;

import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class test {

	public static void main(String[] args) {
		// 提示用户输入积分上下限
		System.out.println("请输入积分上下限：");
		// 定义积分上下限a,b,有控制台输入
		double a = -10000;   //下
		double b = 2;
		double sum = 0;
		// 求出区间差，分成10000个区间，区间越小，误差越小
		double e = cha(a, b, 1000000.0);
		// 求和，循环从第一个区间叠加到第10000个
		for (int j = 1; j <= 1000000; j++) {
			double x = zhongjian(a, b, 1000000.0, j);
			sum = sum + f(x);
		}
		System.out.print("f(x)=2*x*x+x的定积分：");
		System.out.println(sum * e);
	}

	// 定义被积函数，可以修改
	public static double f(double x) {
		return 1.0 / (sqrt(2.0 * PI) * 10) * exp(-0.5 * pow(x / 10, 2));
	}

	// 定义第i个区间的中点值，即定义积分变量
	public static double zhongjian(double a, double b, double n, int i) {
		return a + i * (b - a) / n;
	}

	// 定义每个小区间的间隔差，即将范围分成n个等区间
	public static double cha(double a, double b, double n) {
		return (b - a) / n;
	}

}
