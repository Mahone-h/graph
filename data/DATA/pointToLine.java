package DATA;


import com.cn.beans.TestData;

public class pointToLine {
	public static double PointToLine(double x1, double y1, double x2,
			double y2, TestData t) {
 		double space = 0.0;

		double x_0 = t.getLat();
		double y_0 = t.getLon();
        double a, b, c;
		
		
		a = Gdistance.getDistance(x1, y1, x2, y2);// 线段的长度
//		System.out.println("a="+a);

		b = Gdistance.getDistance(x1, y1, x_0, y_0);// (x1,y1)到点的距离
//		System.out.println("b="+b);

       	c = Gdistance.getDistance(x2, y2, x_0, y_0);// (x2,y2)到点的距离
//		System.out.println("c="+c);

//		if (c <= 0.000001 || b <= 0.000001) {
//			space = 0;
//			return space;
//		}
//		if (a <= 0.000001) {
//			space = b;
//			return space;
//		}
//		if (c * c >= a * a + b * b) {
//			space = b;
//			return space;
//		}
//
//		if (b * b >= a * a + c * c) {
//
//			space = c;
//
//			return space;
//
//		}

		double p = (a + b + c) / 2;// 半周长

		double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积

		space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
		//System.out.println(a+"---"+b+"---"+c+"---"+space);
        return space;
	
		
	}

	// 计算两点之间的距离

//	public static double lineSpace(double x1, double y1, double x2, double y2) {
//
//		double lineLength = 0.0;
//
//		lineLength = Math.sqrt((x1 - x2) * (x1 - x2)* + (y1 - y2)
//
//		* (y1 - y2));
//
//		return lineLength;

//	}

	
	public static void main(String[] args) {
		/*double[][] arr = { { 47.667483, -122.107083 },
				{ 47.6675, -122.107066 }, { 47.667516, -122.107033 },
				{ 47.667533, -122.106983 } };
		for (int x = 0; x < arr.length; x++) {

			double space = PointToLine(47.881301,-122.716309,47.881650,-122.715329, x, arr);

			System.out.println("space=" + space);
*/
		
	TestData a=new TestData(-122.1066,47.66751667);
	double space = PointToLine(47.6666790246964,-122.105779051781,47.6675292849541,-122.105398178101, a);
	System.out.println(space);
	}
}


