package com.cn.dataprocess;

import DATA.Gdistance;
import DATA.Ppoint;
import DATA.pointToLine;
import com.cn.beans.*;
import com.hankcs.algorithm.HMM;
import com.hankcs.algorithm.Viterbi;
import com.sun.javafx.collections.MappingChange;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.*;

/**ObtainedData类
 * 
 * 
 * @author Lenovo `
 *ObtainedData
 */
public class ObtainedData2 {
	/**声名静态类成员measureRate ，表示一个二维数组*/
	public static double[][] measureRate = null;

	public static List<TestData> getTestDatas() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(
						"C:/Users/Mahone/Desktop/gps/gps_data2.txt")));
		String line = null;
		List<TestData> testDatas = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			String[] strs = line.split(";");
			testDatas.add(new TestData(Double.parseDouble(strs[1]), Double
					.parseDouble(strs[0])));
		}
		reader.close();
		//System.out.println(testDatas.size());
		return testDatas;


	}


	public static List<RoadsData> getRoadsData() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(
						"C:/Users/Mahone/Desktop/gps/road_network.txt")));
		String line = null;
		List<RoadsData> roadsDatas = new ArrayList<>();
		Node node = null;
		List<Node> nodes = null;
		int count = 0;

		try {
			while ((line = reader.readLine()) != null) {

				String[] strs = line.split(";");
				String[] n = strs[6].replaceAll("LINESTRING", "")
						.replaceAll("\\(", "").replaceAll("\\)", "").split(",");
				nodes = new ArrayList<>();
				count++;
				for (String s : n) {
					String[] tmps = s.trim().split(" ");
					node = new Node(Double.parseDouble(tmps[0]),
							Double.parseDouble(tmps[1]));
					nodes.add(node);
				}

				RoadsData roadsData = new RoadsData(Long.parseLong(strs[0]),
						Long.parseLong(strs[1]), Long.parseLong(strs[2]),
						Integer.parseInt(strs[3]), nodes);
				roadsDatas.add(roadsData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(count);
		} finally {
			reader.close();
		}

		return roadsDatas;

	}
	/**对每个GPS点设置一个框，GPS点与道路之间的距离为100米之内，每个GPS点都要与道路进行对比，100米转化为经纬度分别为param1=0.0009009，param2=0.0009797，
	 * 每个GPS点筛选出来的框，有一个最大最小的经纬度，符合这个范围内的道路，就是每个GPS点到道路之间距离为100米之内的点
	 * 声名一个getFixdata的方法，返回一个FixData的动态链表
	 * @Title:             getFixdata
	 * @Description:     TODO
	 * @param:             data
	 * @param:             fix   
	 * @return:         List<FixData>   
	 * @throws
	 */
	public static List<FixData> getFixdata() throws Exception {

		// 获取原始的道路信息RoadsData，以动态链表的形式保存在data
		List<RoadsData> data = getRoadsData();
		/*List是一个接口，ArrayList是一个类，ArrayList继承并实现了List，此时List是一个对象，创建fix对象存储FixData列表*/
		List<FixData> fix = new ArrayList();
		/*
		 * 创建lons、lats对象,数据类型是Double的list动态链表
		 */
		List<Double> lons = new ArrayList(), lats = new ArrayList();
		// 创建nodes对象，对道路节点进行遍历
		List<Double> nodes = null;
		for (int i = 0; i < data.size(); i++) {
			List<Node> node = data.get(i).getNodes();
			// System.out.println(node.toString());
			// 加载道路的经纬度数据
			for (int j = 0; j < node.size(); j++) {
				lons.add(node.get(j).getLon());
				lats.add(node.get(j).getLat());
			}
			/*
			 * nodes的实例化
			 */
			nodes = new ArrayList();
			// 0 maxlon 1 minlon 2maxlat 3 minlat
			/*
			 * 在nodes对象中添加max(lons)、min(lons)、max(lats)、min(lats)集合
			 */
			nodes.add(Collections.max(lons));
			nodes.add(Collections.min(lons));
			nodes.add(Collections.max(lats));
			nodes.add(Collections.min(lats));
//			在fix对象中添加FixData类里存的道路信息属性
			fix.add(new FixData(data.get(i).getEdge_ID(), data.get(i)
					.getFrom_Node_ID(), data.get(i).getTo_Node_ID(), data
					.get(i).getTwo_Way(), nodes, data.get(i).getNodes()));
			/*
			 * 实现类的clear 方式将里面的所有元素都释放了并且清空里面的属性 
			 */
			lons.clear();
			lats.clear();
		}
		return fix;

	}






//对经纬度数据进行排序，并进行存储
	/**getSort方法是对FixData进行排序
	 * 
	 * @Title:             getSort
	 * @Description:     TODO
	 * @param:             @param list
	 * @param:             @param index
	 * @param:             @return
	 * @param:             @throws Exception   
	 * @return:         List<FixData>   
	 * @throws
	 */
	public static List<FixData> getSort(List<FixData> list, final int index)
			throws Exception {
		/*
		 * 对集合对象FixData进行排序，实现接口Comparator，并且由这个实现创建一个对象compare
		 */
		Comparator<FixData> compare = new Comparator<FixData>() {
			/**
			 * 
			 * @Title:             compare
			 * @Description:     TODO
			 * @param:             @param list1
			 * @param:             @param list2
			 * @param:             @return   
			 * @return:         int   
			 * @throws
			 */
			public int compare(FixData list1, FixData list2) {
				double a = (double) list1.getNodes().get(index);
				double b = (double) list2.getNodes().get(index);
				if (a > b) {
					return 1;
				} else if (a < b) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		/*
		 * List对象中的泛型类实现了Comparable接口,把compare的结果保存到list中
		 */
		Collections.sort(list, compare);
		return list;
	}
//获取经度的范围
	/**
	 * getLonRange方法是获取经度的范围
	 * @Title:             getLonRange
	 * @Description:     TODO
	 * @param:             @param lon是FixData道路的经度
	 * @param:             @param param（GPS点到道路的距离为200m,米换算成经度）
	 * @param:             @param list表示FixData的存储属性
	 * @param:             @return
	 * @param:             @throws Exception   
	 * @return:         List<FixData>
	 *     //0.5千米距离
	 * @throws
	 */
	public static List<FixData> getLonRange(double lat,double lon, double dis,
			List<FixData> list) throws Exception {


		double r = 6371;//地球半径千米

		double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(lat*Math.PI/180));
		dlng = dlng*180/Math.PI;//角度转为弧度
		double a = lon + dlng;
		double b = lon - dlng;
		// tag=1
		int start = 0, end = 0, tag = 0;
		List<FixData> fixDatas = new ArrayList<>();
//		分类得到list列表中的第一个元素
		fixDatas = getSort(list, 1);
		// for(int i=0;i<fixDatas.size();i++){
		// System.out.println(fixDatas.get(i).getNodes().get(0));
		// }
		//得到经度的分类结果，存放在lonResult对象中
		List<FixData> lonResult = new ArrayList<>();
		for (int i = 0; i < fixDatas.size(); i++) {
			if ((double) fixDatas.get(i).getNodes().get(0) >= b && start == 0) {
				start = i;
				tag = 1;
			}
			if ((double) fixDatas.get(i).getNodes().get(1) >= a && start != 0
					&& end == 0) {
				end = i;
				tag = 0;
			}
			if (tag == 1) {
				lonResult.add(fixDatas.get(i));
				// System.out.println(fixDatas.get(i).getNodes().get(3));
			}
		}
		// System.out.println(start+"----------------------"+end);
		return lonResult;
	}

	
/**getLatRange方法存放纬度的范围
 * 
 * @Title:             getLatRange
 * @Description:     TODO
 * @param:             @param lon
 * @param:             @param lat
 * @param:             @param param1
 * @param:             @param param2
 * @param:             @param list
 * @param:             @return
 * @param:             @throws Exception   
 * @return:         List<FixData>   
 * @throws
 */

	public static List<FixData> getLatRange(double lon, double lat,
			double dis, List<FixData> list) throws Exception {


		double r = 6371;//地球半径千米

		double dlat = dis/r;
		dlat = dlat*180/Math.PI;
		double a = lat + dlat;
		double b = lat - dlat;
		// tag=1
		int start = 0, end = 0, tag = 0;
		List<FixData> fixDatas = getSort(getLonRange(lat,lon, dis, list), 3);

		List<FixData> latResult = new ArrayList<>();

		for (int i = 0; i < fixDatas.size(); i++) {

			if ((double) fixDatas.get(i).getNodes().get(2) >= b && start == 0) {
				start = i;
				tag = 1;
			}
			if ((double) fixDatas.get(i).getNodes().get(3) >= a && start != 0
					&& end == 0) {
				end = i;
				tag = 0;
			}
			if (tag == 1) {
				latResult.add(fixDatas.get(i));
				// System.out.println(fixDatas.get(i).getNodes().get(0)
				// + "~~~~~~~~~~" + fixDatas.get(i).getNodes().get(3));
			}

		}
		// System.out.println(start + "----------------------" + end);
		return latResult;
	}

	// 47.66748333 -122.1070833
	//param1=0.0009009，param2=0.0009797
	@Test
	/**
	 * 
	 * @Title:             test
	 * @Description:     TODO
	 * @param:             @throws Exception   
	 * @return:         void   
	 * @throws
	 */
	public void test() throws Exception {
		//List<FixData> list = getFixdata();
		//List<FixData> list = getFixdata();
		//getLatRange(-122.1070833, 47.66748333,0.001798,0.00263732, list);

		//List<TestData> testDatas = ObtainedData.getTestDatas();
		//System.out.println(testDatas.size());
		//List<TestData> tmpTestDatas = new ArrayList<>();
		//int i = 0;
		//while (i+1 < testDatas.size()) {
		//	tmpTestDatas.add(testDatas.get(i));
		//	i = getI(testDatas, i);
		//	//double distance = Gdistance.getDistance(testDatas.get(i).getLon(),
		//	//		testDatas.get(i).getLat(), testDatas.get(i + 1).getLon(),
		//	//		testDatas.get(i + 1).getLat());
		//	//System.out.println("相邻点---"+distance);
		//	//if(distance==0.0){
		//	//	count++;
		//	//}
		//	//System.out.println("相邻两点---"+Gdistance.getDistance(testDatas.get(i-1).getLon(),
		//	//		testDatas.get(i-1).getLat(), testDatas.get(i+2).getLon(),
		//	//		testDatas.get(i+2).getLat())+"======");
		//
		//
		//}
		//System.out.println(tmpTestDatas.size());
	}




	public List<FixData> findNeighPosition(double longitude,double latitude){
		//先计算查询点的经纬度范围
		double r = 6371;//地球半径千米
		double dis = 0.2;//0.5千米距离
		double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
		dlng = dlng*180/Math.PI;//角度转为弧度
		double dlat = dis/r;
		dlat = dlat*180/Math.PI;
		double minlat =latitude-dlat;
		double maxlat = latitude+dlat;
		double minlng = longitude -dlng;
		double maxlng = longitude + dlng;

		//String hql = "from Property where longitude>=? and longitude =<? and latitude>=? latitude=<? and state=0";
		//Object[] values = {minlng,maxlng,minlat,maxlat};

		List<FixData> list = null;
		return list;
	}
/**
 * 
 * @Title:             main
 * @Description:     TODO
 * @param:             @param args
 * @param:             @throws Exception   
 * @return:         void   
 * @throws
 */
	public static void main(String[] args) throws Exception {

		// 0 maxlon 1 minlon 2maxlat 3 minlat
		// 47.66748333 -122.1070833
		// 1.lon 2.lat 3.lon 4.lat

		/**
		 * 测试点存放在testDatas对象中，数据类型是动态链表的TestData
		 */
		List<TestData> testDatas = ObtainedData2.getTestDatas();
		List<TestData> tmpTestDatas = new ArrayList<>();
		/**
		 * Fixdata(获取路网数据 添加最大最小)
		 */
		List<FixData> list = getFixdata();
		//List<FixData> meaList = new ArrayList<>();
		/*
		 * 将list（道路数据）中所有的元素都放在meaList中
		 */
		//meaList.addAll(list);
		//对testDatas遍历，并把遍历的结果放入tmpTestDatas中
		//去除测试数据重复
		int i = 0;
		while (i+1 < testDatas.size()) {
			tmpTestDatas.add(testDatas.get(i));
			i = getI(testDatas, i);
		}
		/**
		 * 每个测量点对应的候选路段
		 */
		List<List<FixData>> fixdatas = new ArrayList<>();

		// 2.获得系统的时间，单位为毫秒

		//long ti = System.currentTimeMillis();
		//对GPS点进行处理   获取候选路段
		for (TestData testData : tmpTestDatas) {
			// System.out.println(testData.getLat()+"----------------"+testData.getLon());
			fixdatas.add(getLatRange(testData.getLon(), testData.getLat(),
					0.2, list));
		}

		List<RoadPoint> d = null;
		// 定义一个finalTestDatas，存储最终的测量数据结果
		//
		List<TestData> finalTestDatas = new ArrayList<>();

		//tmpTestDatas 测试点
		for (int j = 0; j < tmpTestDatas.size(); j++) {
			//System.out.println("---"+j+"---");
			TestData t = tmpTestDatas.get(j);
//			定义一个动态数组，d表示道路信息 点到线最短的那段
			d = new ArrayList<>();
			//fixdatas测量点对应的候选路段list
			List<FixData> fs = fixdatas.get(j);
//         	tmpfs 候选路段
			List<FixData> tmpfs = new ArrayList<>();
			Map<Long, FixData> idMap = new HashMap<>();
			//
			for (FixData f : fs) {
				RoadPoint roadPoint = minDistance(t, f.getOriNodes(),f.getEdge_ID());
				if (roadPoint != null) {
					d.add(roadPoint);
					tmpfs.add(f);
					idMap.put(f.getEdge_ID(), f);
					//System.out.println(j+"--"+roadPoint.getEdge_ID()+"-----"+roadPoint.getMindistance());
				}
			}
			// 测量点和道路处理结果
			if (d.size() != 0) {
				// RoadPoint minroads = getMinRoads(d);
				t.setRoadPoints(d);
				t.setRoadsDatas(tmpfs);
				t.setRoadDates(idMap);
				finalTestDatas.add(t);
			}
		}

		//for (int q = 0; q < finalTestDatas.size(); q++) {
		//	for (int w = 0; w < finalTestDatas.get(q).getRoadPoints().size(); w++) {
		//		System.out.println(q+"---"+finalTestDatas.get(q).getRoadPoints().get(w).getEdge_ID()+
		//				"---"+finalTestDatas.get(q).getRoadPoints().get(w).getMindistance());
		//	}
		//
		//	System.out.println();
		//}
		//System.out.println(finalTestDatas.size());

		// 计算测量概率，结果保存procept

		double procept = 0;
		for (int k = 0; k < finalTestDatas.size(); k++) {
			TestData testdate = finalTestDatas.get(k);
			List<measureP> mpsList = new ArrayList<>();
			measureP p = null;
			for (int q = 0; q < testdate.getRoadPoints().size(); q++) {
				p = new measureP();
				RoadPoint roadPoint = testdate.getRoadPoints().get(q);
				// System.out.println(roadPoint.getMindistance());
				procept = HMM.b(10, roadPoint.getMindistance());
				p.setID(testdate.getRoadsDatas().get(q).getEdge_ID());
				p.setP(procept);
				mpsList.add(p);
			}
			testdate.setMpsList(mpsList);
		}

		/**
		 *  key:路ID
		 *  value:路数据		 *
		 */

		Map<Long, FixData> index_fixDataMap = new HashMap<>();
//		增强型的for循环，把finalTestDatas进行遍历
		for (TestData t : finalTestDatas) {
			for (int kk = 0; kk < t.getMpsList().size(); kk++) {
				index_fixDataMap.put(t.getRoadsDatas().get(kk).getEdge_ID(), t
						.getRoadsDatas().get(kk));
			}
		}

		/**
		 * key :路ID
		 * value: 计数
		 */
		Map<Long, Integer> indexs = new HashMap<>();
//      设置计数器
		int indexCount = 0;

		for (Long l : index_fixDataMap.keySet()) {
			indexs.put(l, indexCount++);
		}

		//测量概率表示
		measureRate = new double[finalTestDatas.size()][indexs.size()];
		// 对二维数组measureRate进行遍历
		for (int ii = 0; ii < finalTestDatas.size(); ii++) {
			for (int kk = 0; kk < indexs.keySet().size(); kk++) {
				measureRate[ii][kk] = 0;
			}
		}

		//对finalTestDatas进行遍历，结果存储在t中。对二维数组进行表示，其中k表示finalTestDatas的大小，col表示list的指针
		for (int k = 0; k < finalTestDatas.size(); k++) {
			TestData t = finalTestDatas.get(k);
			for (int ll = 0; ll < t.getMpsList().size(); ll++) {
				int col = indexs.get(t.getMpsList().get(ll).getID());
				// System.out.println(t.getMpsList().get(ll).getP());
				measureRate[k][col] = t.getMpsList().get(ll).getP();
			}
		}

		/**
		 * 计算初始状态转移概率矩阵
		 */

		double[] start=new double[indexs.size()];
		//初始状态转移概率矩阵是测量概率矩阵的第一行，也就是第一个GPS点匹配哪条路的概率
		for(int kk=0;kk<measureRate[0].length;kk++){
			start[kk]=measureRate[0][kk];
		}






		//Map<Long,Integer> same=new HashMap<>();
		//int flag = 0;
		//long nowId=0;
		//long pre = 0;
		//long next = 0;
		//long from = 0;
		//long to = 0;
		//List<Long> remove = new ArrayList<>();
		//List<Long> route = new ArrayList<>();
		//
		//for (int e = 0; e < finalTestDatas.size()-1; e++) {
		//	flag = 0;
		//	nowId=0;
		//	next = 0;
		//	List<FixData> roadsDatas = finalTestDatas.get(e).getRoadsDatas();
		//	Map<Long,FixData> mapDatas=finalTestDatas.get(e).getRoadDates();
		//	if (same.size()==0){
		//		if(from==0){
		//			for (int r = 0; r < roadsDatas.size(); r++) {
		//				nowId = roadsDatas.get(r).getEdge_ID();
		//				same.put(nowId, 0);
		//			}
		//		}
		//		else {
		//			for (int r = 0; r < roadsDatas.size(); r++) {
		//				if(roadsDatas.get(r).getFrom_Node_ID()==to||roadsDatas.get(r).getTo_Node_ID()==from||
		//						roadsDatas.get(r).getFrom_Node_ID()==pre) {
		//					nowId = roadsDatas.get(r).getEdge_ID();
		//					same.put(nowId, 0);
		//				}
		//			}
		//			if(same.size()==0){
		//				break;
		//			}
		//
		//			pre = 0;
		//		}
		//
		//	}
		//	for (int r = 0; r < finalTestDatas.get(e+1).getRoadsDatas().size(); r++) {
		//		next = finalTestDatas.get(e+1).getRoadsDatas().get(r).getEdge_ID();
		//		if (same.containsKey(next)){
		//			same.put(next, 1);
		//			flag++;
		//		}
		//	}
		//
		//
		//
		//	for (Map.Entry<Long, Integer> entry : same.entrySet()) {
		//		if(entry.getValue()==0){
		//			remove.add(entry.getKey());
		//			if (flag==0){
		//				System.out.println(e+"---"+same);
		//				pre = entry.getKey();
		//				if(from!=0){
		//					System.out.println(pre);
		//				}
		//				route.add(pre);
		//				to = mapDatas.get(pre).getTo_Node_ID();
		//				from = mapDatas.get(pre).getFrom_Node_ID();
		//			}
		//		}else{
		//			same.put(entry.getKey(), 0);
		//		}
		//	}
		//	for (Long aLong : remove) {
		//		same.remove(aLong);
		//	}
		//
		//
		//	remove.clear();
		//
		//
		//}
		//System.out.println(route);






	}

	
	/**
	 * 
	 * @Title:             getMinRoads
	 * @Description:     TODO
	 * @param:             @param d
	 * @param:             @return   
	 * @return:         RoadPoint   
	 * @throws
	 */
	public static RoadPoint getMinRoads(List<RoadPoint> d) {
		// TODO Auto-generated method stub
		RoadPoint finalRoadPoint = d.get(0);
		for (int i = 1; i < d.size(); i++) {
			if (finalRoadPoint.getMindistance() > d.get(i).getMindistance()) {
				finalRoadPoint = d.get(i);
			}
		}
		return finalRoadPoint;
	}
	/**
	 * 
	 * @Title:             minDistance获取GPS点到道路的最短距离
	 * @Description:     TODO
	 * @param:             @param t
	 * @param:             @param oriNodes
	 * @param:             @return   
	 * @return:         RoadPoint   
	 * @throws
	 */

	private static RoadPoint minDistance(TestData t, List<Node> oriNodes,long edge_ID) {

		RoadPoint roadPoint = new RoadPoint();
		double distance = 0;

		for (int i = 0; i < oriNodes.size() - 1; i++) {
			double d = pointToLine.PointToLine(oriNodes.get(i).getLat(),
					oriNodes.get(i).getLon(), oriNodes.get(i + 1).getLat(),
					oriNodes.get(i + 1).getLon(), t);
			//System.out.println(d);
			if (distance < d) {
				distance = d;
				roadPoint.setLonStart(oriNodes.get(i).getLon());
				roadPoint.setLatStart(oriNodes.get(i).getLat());
				roadPoint.setLonEnd(oriNodes.get(i + 1).getLon());
				roadPoint.setLatEnd(oriNodes.get(i + 1).getLat());
				roadPoint.setMindistance(distance);
				roadPoint.setEdge_ID(edge_ID);
			}
		}


		if (distance > 100) {
			return null;
		}else {
			//计算投影点
			//计算每条道路的斜率
			double	 kk=Ppoint.slope(roadPoint.getLatStart(),roadPoint.getLonStart(),
					roadPoint.getLatEnd(),roadPoint.getLonEnd());
            //计算GPS点到每条道路的投影点
			Node node=Ppoint.cacul_shadow(roadPoint.getLatStart(),
					roadPoint.getLonStart(),	 roadPoint.getLatEnd(),roadPoint.getLonEnd(), t,kk);
			Node a = new Node(roadPoint.getLonStart(), roadPoint.getLatStart());
			Node b = new Node(roadPoint.getLonEnd(), roadPoint.getLatEnd());
			Node c = new Node(t.getLon(), t.getLat());
			Node p=new Node(node.getLat(), node.getLon());
			boolean is = isInTriangle(a,b,c,p);
			System.out.println(is);
			if (is){
				roadPoint.setCaculNode(node);
			}
			else {
				double x=Gdistance.getDistance(a.getLat(),a.getLon(), c.getLat(),c.getLon());
				double y=Gdistance.getDistance(b.getLat(),b.getLon(), c.getLat(),c.getLon());
				if(x<y){
					roadPoint.setCaculNode(a);
				}else {
					roadPoint.setCaculNode(b);
				}
			}

		}
		return roadPoint;
	}
/**
 * 
 * @Title:             getI，筛选GPS点，GPS点间的距离小于300m,返回到m中
 * @Description:     TODO
 * @param:             @param testDatas
 * @param:             @param i
 * @param:             @return   
 * @return:         int   
 * @throws
 */
	private static int getI(List<TestData> testDatas, int i) {
		int m = i + 1;
		double distance = Gdistance.getDistance(testDatas.get(i).getLat(),
				testDatas.get(i).getLon(), testDatas.get(m).getLat(),
				testDatas.get(m).getLon());
		if(distance==0.0){
			while (m < testDatas.size()) {
				m++;
				distance = Gdistance.getDistance(testDatas.get(i).getLat(),
						testDatas.get(i).getLon(), testDatas.get(m).getLat(),
						testDatas.get(m).getLon());
				if(distance!=0.0){
					break;
				}
			}
		}
		return m;
	}

	/**
	 * 判断一个点在不在三角形内部
	 * @return
	 */

	@Test
	public void test1(){
		Node a = new Node(117.30365,39.005054);
		Node b = new Node(117.327509,39.012287);
		Node c = new Node(117.327572,38.996649);
		Node p = new Node(117.342448,39.011061);
		System.out.println(isInTriangle(a,b,c,p));
	}

	public static boolean isInTriangle(Node A,Node B,Node C,Node P){
		double a = 0, b = 0, c = 0;

		Node MA = new Node(P.getLat() - A.getLat(),P.getLon() - A.getLon());
		Node MB = new Node(P.getLat() - B.getLat(),P.getLon() - B.getLon());
		Node MC = new Node(P.getLat() - C.getLat(),P.getLon() - C.getLon());

		/*向量叉乘*/
		a = MA.getLat() * MB.getLon() - MA.getLon() * MB.getLat();
		b = MB.getLat() * MC.getLon() - MB.getLon() * MC.getLat();
		c = MC.getLat() * MA.getLon() - MC.getLon() * MA.getLat();

		if((a <= 0 && b <= 0 && c <= 0)||
				(a > 0 && b > 0 && c > 0))
			return true;
		return false;
		
	}
}
