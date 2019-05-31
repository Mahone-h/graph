package DATA;

import java.util.ArrayList;
import java.util.List;

import com.cn.beans.Transform;
import com.cn.dataprocess.ObtainedData2;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.cn.beans.Node;
import com.cn.beans.TestData;

import static java.lang.Double.isInfinite;

public class Ppoint {



	public static Node cacul_shadow(double x1, double y1, double x2, double y2,
			TestData testData, double k) {
		

		double x4 = 0;
		double y4 = 0;
		double x3 = testData.getLon2D();
		double y3 = testData.getLat2D();
		//double x3 = testData.getLon();
		//double y3 = testData.getLat();

		if (k == 0) // 垂线斜率不存在情况
		{
			x4 = x3;
			y4 = y1;

			return new Node(x4, y4);

		}
		if(isInfinite(k)){
			x4 = x1;
			y4 = y3;
			//System.out.println(k);
			return new Node(x4, y4);
		}

		x4 = (k * x1 + x3 / k + y3 - y1) / (1 / k + k);
		y4 = -1 / k * (x4 - x3) + y3;



		return new Node(x4, y4);

	};

	public static double slope(double x1, double y1, double x2, double y2) {
		double k = 0;

		if (y1 == y2) {
			k = 0;
			return k;
		}


		k = (y2 - y1) / (x2 - x1);
		//System.out.println(k);
		return k;

	}


	public static void main(String[] args) {


		Node a = new Node(-122.105779051781,47.6666790246964);

		Node b = new Node(-122.105398178101,47.6675292849541);
		Node c = new Node(-122.1067333,47.66751667);

		double k = slope(a.getLon(),a.getLat(),b.getLon(),b.getLat());

		a = Transform.to2D(a.getLon(),a.getLat());
		b = Transform.to2D(b.getLon(),b.getLat());
		c = Transform.to2D(c.getLon(),c.getLat());
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		k = slope(a.getLon(),a.getLat(),b.getLon(),b.getLat());

		Node node= Ppoint.cacul_shadow(a.getLon(),a.getLat(),b.getLon(),b.getLat(),
				new TestData(c.getLon(),c.getLat()), k);
		System.out.println(node);

		System.out.println("测试点到垂足距离："+Gdistance.get2Ddistance(c.getLat(),c.getLon(),node.getLat(),node.getLon()));
		System.out.println("点到线距离"+pointToLine.PointToLine(a.getLon(),a.getLat(),b.getLon(),b.getLat(),
				new TestData(c.getLon(),c.getLat())));

		System.out.println(ObtainedData2.isInTriangle(a,b,c,
				node));
		System.out.println(Gdistance.getDistance(node.getLat(),node.getLon(),a.getLat(),a.getLon()));
		System.out.println(Gdistance.getDistance(node.getLat(),node.getLon(),b.getLat(),b.getLon()));
		System.out.println(Gdistance.getDistance(a.getLat(),a.getLon(),b.getLat(),b.getLon()));
		System.out.println(Gdistance.getDistance(node.getLat(),node.getLon(),a.getLat(),a.getLon())+Gdistance.getDistance(node.getLat(),node.getLon(),b.getLat(),b.getLon()));

	}
}



//	public static List<List<double[]>> getProjection() {
//		double[][] arr = { { 47.667483, -122.107083 },
//				{ 47.6675, -122.107066 }, { 47.667516, -122.107033 },
//				{ 47.667533, -122.106983 } };
//
//		List<Street> streets = new ArrayList<>();
//		streets.add(new Street(47.877409,-122.711338, 47.876503,-122.711078, slope(47.877409,-122.711338, 47.876503,-122.711078)));
//		streets.add(new Street(47.881301,-122.716309,47.881650,-122.715329, slope(47.881301,-122.716309,47.881650,-122.715329)));
//		List<List<double[]>> projects = new ArrayList<>();
//
//	
//		
//		
//		for (int i = 0; i < streets.size(); i++) {
//			List<double[]>  s=new ArrayList<>();
//			for (int x = 0; x < arr.length; x++) {
//				cacul_shadow(streets.get(i).lonStart, streets.get(i).latStart,
//						streets.get(i).lonEnd, streets.get(i).latEnd,testData.getLon(),testData.getLat(),
//						streets.get(i).rate,s);
////				cacul_shadow(streets.get(i).lonStart, streets.get(i).latStart,
////						streets.get(i).lonEnd, streets.get(i).latEnd, new TestData(arr[x][1], arr[x][0]),
////						streets.get(i).rate);
//			}
//			projects.add(s);
//		}
//		return projects;
//		
//	}
//
//	
//}

