package com.cn.beans;

import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class test {

	public static void main(String[] args) {
		// ��ʾ�û��������������
		System.out.println("��������������ޣ�");
		// �������������a,b,�п���̨����
		double a = -10000;   //��
		double b = 2;
		double sum = 0;
		// ��������ֳ�10000�����䣬����ԽС�����ԽС
		double e = cha(a, b, 1000000.0);
		// ��ͣ�ѭ���ӵ�һ��������ӵ���10000��
		for (int j = 1; j <= 1000000; j++) {
			double x = zhongjian(a, b, 1000000.0, j);
			sum = sum + f(x);
		}
		System.out.print("f(x)=2*x*x+x�Ķ����֣�");
		System.out.println(sum * e);
	}

	// ���屻�������������޸�
	public static double f(double x) {
		return 1.0 / (sqrt(2.0 * PI) * 10) * exp(-0.5 * pow(x / 10, 2));
	}

	// �����i��������е�ֵ����������ֱ���
	public static double zhongjian(double a, double b, double n, int i) {
		return a + i * (b - a) / n;
	}

	// ����ÿ��С����ļ���������Χ�ֳ�n��������
	public static double cha(double a, double b, double n) {
		return (b - a) / n;
	}

}
