package DATA;

import com.cn.beans.Node;

public class Project {
	/**
	 * 求直线外一点到直线上的投影点
	 *
	 * @param pLine    线上一点  (x1,y1)线上一点
	 * @param k        斜率
	 * @param pOut     线外一点  (x3,y3)线外一点
	 * @param pProject 投影点     (x4,y4)投影点
	 */
	public static Node getProjectivePoint(double x1, double y1, double k, double arr[][]) {
		Node node=null;
		int x=0;
		double x4=0;
		double y4=0;
		double x3 = arr[x][0];
		double y3 = arr[x][1];
	    if (k == 0) {//垂线斜率不存在情况
	    	x4 = x3;
			y4 = y3;
	    } else {
	    	x4 = (k * x1 + x3 / k + y3 - y1) / (1 / k + k);
			y4 = -1 / k * (x4 - x3) + y3;
	    }
	    node=new Node(x4, y4);
		return node;
	}
	/**
	 * 求pOut在pLine以及pLine2所连直线上的投影点
	 *
	 * @param pLine
	 * @param pLine2
	 * @param pOut
	 * @param pProject
	 */
	public static void getProjectivePoint(double x1, double y1, double x2, double y2, double[][]arr, double x4, double y4) {
	    double k = 0;
	    try {
	        k = getSlope( x1, y1, x2, y2);
	    } catch (Exception e) {
	        k = 0;
	    }
	    getProjectivePoint(x1, y1, x2, y2, arr, x4, y4);
	}
	/**
	 * 通过两个点坐标计算斜率
	 * 已知A(x1,y1),B(x2,y2)
	 * 1、若x1=x2,则斜率不存在；
	 * 2、若x1≠x2,则斜率k=[y2－y1]/[x2－x1]
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @throws Exception 如果x1==x2,则抛出该异常
	 */
	public static double getSlope(double x1, double y1, double x2, double y2) throws Exception {
	    if (x1 == x2) {
	        throw new Exception("Slope is not existence,and div by zero!");
	    }
	    return (y2 - y1) / (x2 - x1);
	}

	public static void main(String[] args) throws Exception {
		double[][] arr = { { 47.667483, -122.107083 },
				{ 47.6675, -122.107066 }, { 47.667516, -122.107033 },
				{ 47.667533, -122.106983 } };
		for(int x=0; x<arr.length; x++){
			System.out.println(Project.getProjectivePoint(47.877409,-122.711338,-0.28697571743610806,arr));
			
		}
		
	}

}
