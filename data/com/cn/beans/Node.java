package com.cn.beans;

public class Node {

	public Node() {
		super();
	}
	double lon;
	double lat;
	double lon2D;
	double lat2D;
	public Node(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}

	public Node(Node to2D) {

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
	@Override
	public String toString() {
		return "Node [lon=" + lon + ", lat=" + lat + "]";
	}
	
}
