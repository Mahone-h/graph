package com.cn.beans;


import org.junit.Test;

public class POINT{
	public int x;
	public int y;



	public POINT(int x, int y){
		this.x = x;
		this.y = y;
	}
	public boolean isInTriangle(POINT A, POINT B, POINT C, POINT P) {
		/*利用叉乘法进行判断,假设P点就是M点*/
		int a = 0, b = 0, c = 0;

		POINT MA = new POINT(P.x - A.x,P.y - A.y);
		POINT MB = new POINT(P.x - B.x,P.y - B.y);
		POINT MC = new POINT(P.x - C.x,P.y - C.y);

		/*向量叉乘*/
		a = MA.x * MB.y - MA.y * MB.x;
		b = MB.x * MC.y - MB.y * MC.x;
		c = MC.x * MA.y - MC.y * MA.x;

		if((a <= 0 && b <= 0 && c <= 0)||
				(a > 0 && b > 0 && c > 0))
			return true;
		return false;
	}



}

