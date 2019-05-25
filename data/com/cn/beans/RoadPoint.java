package com.cn.beans;

public class RoadPoint {

	private double lonStart;
	private double latStart;
	private double lonEnd;
	private double latEnd;
	private double mindistance;
	private Node caculNode;

	public long getEdge_ID() {
		return edge_ID;
	}

	public void setEdge_ID(long edge_ID) {
		this.edge_ID = edge_ID;
	}

	private long edge_ID;


	public Node getCaculNode() {
		return caculNode;
	}
	public void setCaculNode(Node caculNode) {
		this.caculNode = caculNode;
	}
	public RoadPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoadPoint(double lonStart, double latStart, double lonEnd,
			double latEnd, double mindistance) {
		super();
		this.lonStart = lonStart;
		this.latStart = latStart;
		this.lonEnd = lonEnd;
		this.latEnd = latEnd;
		this.mindistance = mindistance;
	}
	public double getLonStart() {
		return lonStart;
	}
	public void setLonStart(double lonStart) {
		this.lonStart = lonStart;
	}
	public double getLatStart() {
		return latStart;
	}
	public void setLatStart(double latStart) {
		this.latStart = latStart;
	}
	public double getLonEnd() {
		return lonEnd;
	}
	public void setLonEnd(double lonEnd) {
		this.lonEnd = lonEnd;
	}
	public double getLatEnd() {
		return latEnd;
	}
	public void setLatEnd(double latEnd) {
		this.latEnd = latEnd;
	}
	public double getMindistance() {
		return mindistance;
	}
	public void setMindistance(double mindistance) {
		this.mindistance = mindistance;
	}
	
	
	
}
