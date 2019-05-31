package com.cn.dataprocess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DATA.Viterbi;
import org.junit.Test;

import DATA.Gdistance;
import DATA.Ppoint;
import DATA.pointToLine;

import com.cn.beans.FixData;
import com.cn.beans.Node;
import com.cn.beans.RoadPoint;
import com.cn.beans.RoadsData;
import com.cn.beans.TestData;
import com.cn.beans.measureP;
import com.hankcs.algorithm.HMM;


import javax.swing.*;

/**ObtainedData类
 * 
 * 
 * @author Lenovo `
 *ObtainedData
 */
public class ObtainedData {
	/**声名静态类成员measureRate ，表示一个二维数组*/
	public static double[][] measureRate = null;

	public static List<TestData> getTestDatas() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(
						"C:/Users/Mahone/Desktop/gps/gps_data.txt")));
		String line = null;
		List<TestData> testDatas = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			String[] strs = line.split(";");
			testDatas.add(new TestData(Double.parseDouble(strs[0]), Double
					.parseDouble(strs[1])));
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
					.get(i).getTwo_Way(), nodes, data.get(i).getNodes(),data.get(i).getNodes2D()));
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
	 * @throws
	 */
	public static List<FixData> getLonRange(double lon, double param,
			List<FixData> list) throws Exception {

		double a = lon + param;
		double b = lon - param;
		// tag=1
		int start = 0, end = 0, tag = 0;
		List<FixData> fixDatas = new ArrayList<>();
//		分类得到list列表中的第一个元素
		fixDatas = getSort(list, 0);
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
			if ((double) fixDatas.get(i).getNodes().get(0) >= a && start != 0
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
			double param1, double param2, List<FixData> list) throws Exception {
 
		double a = lat + param2;
		double b = lat - param2;
		// tag=1
		int start = 0, end = 0, tag = 0;
		List<FixData> fixDatas = getSort(getLonRange(lon, param1, list), 3);

		List<FixData> latResult = new ArrayList<>();
		// for(int j=0;j<fixDatas.size();j++){
		// System.out.println(fixDatas.get(j).getNodes().get(0)+"----"+fixDatas.get(j).getNodes().get(3));
		// }
		for (int i = 0; i < fixDatas.size(); i++) {

			if ((double) fixDatas.get(i).getNodes().get(2) >= b && start == 0) {
				start = i;
				tag = 1;
			}
			if ((double) fixDatas.get(i).getNodes().get(2) >= a && start != 0
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
		List<TestData> testDatas = ObtainedData.getTestDatas();
		List<TestData> tmpTestDatas = new ArrayList<>();
		/*
		 * Fixdata(道路数据)
		 */
		List<FixData> list = getFixdata();
		//List<FixData> meaList = new ArrayList<>();
		/*
		 * 将list（道路数据）中所有的元素都放在meaList中
		 */
		//meaList.addAll(list);
		// 对testDatas遍历，并把遍历的结果放入tmpTestDatas中
		int i = 0;
		while (i+1 < testDatas.size()) {
			tmpTestDatas.add(testDatas.get(i));
			i = getI(testDatas, i);
		}

		//定义一个数组型链表，链表中存放的是List<FixData>，这个数组型链表为fixdatas
		List<List<FixData>> fixdatas = new ArrayList<>();

		// 2.获得系统的时间，单位为毫秒

		//long ti = System.currentTimeMillis();
		//对GPS点进行处理，点与点的距离为200m
		for (TestData testData : tmpTestDatas) {
			// System.out.println(testData.getLat()+"----------------"+testData.getLon());
			fixdatas.add(getLatRange(testData.getLat(), testData.getLon(),
					0.001798,0.00263732, list));
		}

		List<RoadPoint> d = null;
		// 定义一个finalTestDatas，存储最终的测量数据结果
		//
		List<TestData> finalTestDatas = new ArrayList<>();
		

		for (int j = 0; j < tmpTestDatas.size(); j++) {

			TestData t = tmpTestDatas.get(j);
//			定义一个动态数组，d表示道路信息 点到线最短的那段
			d = new ArrayList<>();
//

			//测量点对应的候选路段
			List<FixData> fs = fixdatas.get(j);
//         	tmpfs 候选路段
			List<FixData> tmpfs = new ArrayList<>();

			//
			for (FixData f : fs) {
				RoadPoint roadPoint = minDistance(t, f.getOriNodes());

				if (roadPoint != null) {
					d.add(roadPoint);
					tmpfs.add(f);
				}
			}
			// 测量点和道路处理结果
			if (d.size() != 0) {
				// RoadPoint minroads = getMinRoads(d);
				t.setRoadPoints(d);
				t.setRoadsDatas(tmpfs);
				finalTestDatas.add(t);
				
			}
		}

		/*
		 * System.out.println("finalTestDatas="+finalTestDatas.size());
		 * System.out.println("finalFixdatas="+finalFixdatas.size());
		 */
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

		
		// 声名 index_fixDataMap对象，并且值是FixData，类型为Long
		/**
		 *  key:路ID
		 *  value:路数据
		 *
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

		// 对finalTestDatas进行遍历，结果存储在t中。对二维数组进行表示，其中k表示finalTestDatas的大小，col表示list的指针
		for (int k = 0; k < finalTestDatas.size(); k++) {
			TestData t = finalTestDatas.get(k);
			for (int ll = 0; ll < t.getMpsList().size(); ll++) { 
				int col = indexs.get(t.getMpsList().get(ll).getID());
				// System.out.println(t.getMpsList().get(ll).getP());
				measureRate[k][col] = t.getMpsList().get(ll).getP();
			}

		}


		
		/**
		 * 计算GPS点间的距离，
		 */
		List<Double> testData_distanceDoubles = new ArrayList<>();
		for (int ll = 0; ll < finalTestDatas.size() - 1; ll++) {
			TestData node1 = finalTestDatas.get(ll);
			TestData node2 = finalTestDatas.get(ll + 1);

			double distance = Gdistance.getDistance(node1.getLon(),
					node1.getLat(), node2.getLon(), node2.getLat());
			testData_distanceDoubles.add(distance);
		}

		
		// 1，计算投影点  2计算投影点间的距离p1  3.计算测量点间距离与投影点间距离p1的差值
		//1.计算投影点
		/** 转移概率的表示*/
		double tranP[][]=new double[index_fixDataMap.size()][index_fixDataMap.size()];	
//		对finalTestDatas遍历，把数据结果存储在data中
		 for(int k=0;k<finalTestDatas.size();k++){
			 TestData data=finalTestDatas.get(k);
//			 对RoadPoints进行遍历，把数据结果存储在roadPoint中
			 for(int jj=0;jj<data.getRoadPoints().size();jj++){
				 RoadPoint roadPoint=data.getRoadPoints().get(jj);
//				 计算每条道路的斜率
				 double	 kk=Ppoint.slope(roadPoint.getLatStart(),roadPoint.getLonStart(),
						 roadPoint.getLatEnd(),roadPoint.getLonEnd());	
//				 计算GPS点到每条道路的投影点
				 Node node=Ppoint.cacul_shadow(roadPoint.getLatStart(),
						 roadPoint.getLonStart(),	 roadPoint.getLatEnd(),roadPoint.getLonEnd(), data,	 kk);
				 System.out.println(node.getLat()+"------"+node.getLon());
				 roadPoint.setCaculNode(node);
			 }			 
		 }
//		 设置计数器
		 int count=0;
		 HashMap<Double, Long> distance=new HashMap<>();
//		 获取所有的GPS点
		 for(int k=0;k<finalTestDatas.size()-1;k++){
			 TestData data=finalTestDatas.get(k);	
			 TestData nextData=finalTestDatas.get(k+1);
			 //获取每个GPS点所有的路段，比如这个GPS点为a
			 for (int j = 0; j < data.getRoadPoints().size(); j++) {
				FixData roadsData=data.getRoadsDatas().get(j);
				RoadPoint roadPoint=data.getRoadPoints().get(j);	
				//获取下一个GPS点所有的路段,比如这个GPS点为b
				for (int l = 0; l < nextData.getRoadPoints().size(); l++) {
					FixData nextRoadsData=nextData.getRoadsDatas().get(l);	
					//对GPS点对应的道路进行遍历
					for (int m = 0; m < data.getRoadPoints().size(); m++) {
						
						if (indexs.get(nextRoadsData.getEdge_ID())==indexs.get(data.getRoadsDatas().get(m).getEdge_ID())) {						
							//System.out.println(distance);
//							获取a到b转移时，判断最小的投影点间的距离
							for (int g = 0; g < nextData.getRoadPoints().size(); g++) {	
								FixData currentRoadsData=nextData.getRoadsDatas().get(g);	
								RoadPoint currentRoadPoint=nextData.getRoadPoints().get(g);
								distance.put(Gdistance.getDistance(data.getRoadPoints().get(m).getCaculNode().getLon(),
										data.getRoadPoints().get(m).getCaculNode().getLat(),
										currentRoadPoint.getCaculNode().getLon(), 
										currentRoadPoint.getCaculNode().getLat()), currentRoadsData.getEdge_ID());								
							}
							if(distance.size()>0){
								//得到投影点间最小的distance，存放在key下			
								Double key=Collections.min(distance.keySet());	
								//System.out.println(key);
								if (key==0.0) {						
//									System.out.println(data.getRoadsDatas().get(m).getEdge_ID());
//									转移概率的表示
									tranP[indexs.get(data.getRoadsDatas().get(m).getEdge_ID())][indexs.get(distance.get(key))] =1;						
								}else {
									double testdata=testData_distanceDoubles.get(k);
									if (testdata==0.0) {
										tranP[indexs.get(data.getRoadsDatas().get(m).getEdge_ID())][indexs.get(distance.get(key))] =1.0;
									}else {
//										计算转移概率的公式
										double p=HMM.a(testdata, key);						
										tranP[indexs.get(data.getRoadsDatas().get(m).getEdge_ID())][indexs.get(distance.get(key))] =p;
									}												
								}						
//								System.out.println(indexs.get(roadsData.getEdge_ID())+"-----"+indexs.get(distance.get(key)));
								count++;
							}
							
							distance.clear();
						}		
						
					}
				}	
				
			}			 
		 }
		 System.out.println(count);
		 count=0;
		 for (int j = 0; j <index_fixDataMap.size() ; j++) {
			for (int j2 = 0; j2 < index_fixDataMap.size(); j2++) {				
					
						//System.out.println(tranP[j][j2]+"----------------");
						count++;
														
			}
		}

		 
		//double tranP[][]=new double[index_fixDataMap.size()][index_fixDataMap.size()];	    
		//measureRate = new double[finalTestDatas.size()][indexs.size()];
//		 计算初始状态转移概率矩阵
		 double[] start=new double[indexs.size()];
		 
//		 int a[][]={{1,2},{2,3,4}};
//         System.out.println("**"+a.length);
//         System.out.println("***"+a[0].length);		 
//		 System.out.println("////*" + measureRate.length);
//		 System.out.println("******" + measureRate[0].length);
//		 初始状态转移概率矩阵是测量概率矩阵的第一行，也就是第一个GPS点匹配哪条路的概率
		 for(int kk=0;kk<measureRate[0].length;kk++){
			 start[kk]=measureRate[0][kk];
		 }
		 
		int[] states=new int[index_fixDataMap.size()];
		
		for(int kk = 0;kk<index_fixDataMap.size();kk++){
			states[kk]=kk;
		}
		
		int[] obs=new int[finalTestDatas.size()];
		for(int kk=0;kk<finalTestDatas.size();kk++){
			obs[kk]=kk;
		}
		
		/*System.out.println(obs.length);
		System.out.println(states.length);
		System.out.println(start.length);
		System.out.println(tranP.length+";"+tranP[0].length);
		System.out.println(measureRate.length+";"+measureRate[0].length);
		System.out.println("####################################################################");*/
		long tii=System.currentTimeMillis();
//		把HMM模型代入Viterbi算法，包括观测量（测量点数据），状态（道路段），初始概率矩阵，转移概率矩阵和测量概率矩阵
		int[] result = Viterbi.compute(obs, states, start, tranP, measureRate);
	
//		System.out.println((System.currentTimeMillis()-tii)/1000);
		/*for(int kk=0;kk<result.length;kk++){
			System.out.println(result[kk]);
		}*/
		 
	}

	// @Test
	// public void aa() {
	// System.out.println(HMM.b(4.07,0.01));
	// }
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

	private static RoadPoint minDistance(TestData t, List<Node> oriNodes) {

		RoadPoint roadPoint = new RoadPoint();
		double distance = 0;

		for (int i = 0; i < oriNodes.size() - 1; i++) {
			double d = pointToLine.PointToLine(oriNodes.get(i).getLat(),
					oriNodes.get(i).getLon(), oriNodes.get(i + 1).getLat(),
					oriNodes.get(i + 1).getLon(), t);
			if (distance < d) {
				distance = d;
				roadPoint.setLonStart(oriNodes.get(i).getLon());
				roadPoint.setLatStart(oriNodes.get(i).getLat());
				roadPoint.setLonEnd(oriNodes.get(i + 1).getLon());
				roadPoint.setLatEnd(oriNodes.get(i + 1).getLat());
				roadPoint.setMindistance(distance);
			}
		}

		if (distance > 100) {
			return null;
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
		double distance = Gdistance.getDistance(testDatas.get(i).getLon(),
				testDatas.get(i).getLat(), testDatas.get(m).getLon(),
				testDatas.get(m).getLat());
		if(distance==0.0){
			while (m < testDatas.size()) {
				m++;
				distance = Gdistance.getDistance(testDatas.get(i).getLon(),
						testDatas.get(i).getLat(), testDatas.get(m).getLon(),
						testDatas.get(m).getLat());
				if(distance!=0.0){
					break;
				}
			}
		}
		return m;
	}
}
