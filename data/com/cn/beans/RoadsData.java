package com.cn.beans;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoadsData implements Comparator<FixData>{

	private long edge_ID;
	private long from_Node_ID;
	private long to_Node_ID;
	private int two_Way;
	List<Node> nodes;
	List<Node> nodes2D;

	public List<Node> getNodes2D() {
		return nodes2D;
	}

	public void setNodes2D(List<Node> nodes2D) {
		this.nodes2D = nodes2D;
	}

	public RoadsData(long edge_ID, long from_Node_ID, long to_Node_ID,
					 int two_Way, List<Node> nodes, List<Node> nodes2D) {
		super();
		this.edge_ID = edge_ID;
		this.from_Node_ID = from_Node_ID;
		this.to_Node_ID = to_Node_ID;
		this.two_Way = two_Way;
		this.nodes = nodes;
		this.nodes2D = nodes2D;
	}

	public long getEdge_ID() {
		return edge_ID;
	}

	public void setEdge_ID(long edge_ID) {
		this.edge_ID = edge_ID;
	}

	public long getFrom_Node_ID() {
		return from_Node_ID;
	}

	public void setFrom_Node_ID(long from_Node_ID) {
		this.from_Node_ID = from_Node_ID;
	}

	public long getTo_Node_ID() {
		return to_Node_ID;
	}

	public void setTo_Node_ID(long to_Node_ID) {
		this.to_Node_ID = to_Node_ID;
	}

	public int getTwo_Way() {
		return two_Way;
	}

	public void setTwo_Way(int two_Way) {
		this.two_Way = two_Way;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	
	
	public RoadsData(long edge_ID, long from_Node_ID, long to_Node_ID,
			int two_Way, List<Node> nodes) {
		super();
		this.edge_ID = edge_ID;
		this.from_Node_ID = from_Node_ID;
		this.to_Node_ID = to_Node_ID;
		this.two_Way = two_Way;
		this.nodes = nodes;
	}

	@Override
	public int compare(FixData list1, FixData list2) {
		int a=(int) list1.getNodes().get(0);
		int b=(int) list2.getNodes().get(0);
		if(a>b){
			return 1;
			}else if(a<b){
			return -1;
			}else{
			return 0;
			}		
	}
	
}
