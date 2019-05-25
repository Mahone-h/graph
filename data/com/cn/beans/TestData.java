package com.cn.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试数据
 */

public class TestData {

	private double lon;
	private double lat;
	List<RoadPoint> roadPoints;
	List<FixData> roadsDatas= new ArrayList<>();
	List<measureP> mpsList;
	Map<Long,FixData> roadDates;

	public Map<Long, FixData> getRoadDates() {
		return roadDates;
	}

	public void setRoadDates(Map<Long, FixData> roadDates) {
		this.roadDates = roadDates;
	}

	public List<measureP> getMpsList() {
		return mpsList;
	}

	public void setMpsList(List<measureP> mpsList) {
		this.mpsList = mpsList;
	}

	public List<FixData> getRoadsDatas() {
		return roadsDatas;
	}

	public void setRoadsDatas(List<FixData> list) {
		this.roadsDatas = list;
	}



	public List<RoadPoint> getRoadPoints() {
		return roadPoints;
	}

	public void setRoadPoints(List<RoadPoint> roadPoints) {
		this.roadPoints = roadPoints;
	}

	public TestData(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	
}
