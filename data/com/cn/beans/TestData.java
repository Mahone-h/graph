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
	private double lon2D;
	private double lat2D;

	List<RoadPoint> roadPoints;
	List<FixData> roadsDatas = new ArrayList<>();
	List<measureP> mpsList;
	Map<Long, FixData> roadDates;
	Map<Long, Double> mpList;

	public Map<Long, Double> getMpList() {
		return mpList;
	}

	public void setMpList(Map<Long, Double> mpList) {
		this.mpList = mpList;
	}




	public double getLon2D() {
		return lon2D;
	}

	public void setLon2D(double lon2D) {
		this.lon2D = lon2D;
	}

	public double getLat2D() {
		return lat2D;
	}

	public void setLat2D(double lat2D) {
		this.lat2D = lat2D;
	}



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
	public TestData(double lon, double lat,double lon2D, double lat2D) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.lon2D = lon2D;
		this.lat2D = lat2D;
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

