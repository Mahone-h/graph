package com.cn.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 路网数据
 */

public class FixData{
	private long edge_ID;
	private long from_Node_ID;
	private long to_Node_ID;
	private int two_Way;
	List nodes=new ArrayList<>();
	List<Node> oriNodes=new ArrayList<>();
	
	
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
	public List getNodes() {
		return nodes;
	}
	public void setNodes(List nodes) {
		this.nodes = nodes;
	}
	public List<Node> getOriNodes() {
		return oriNodes;
	}
	public void setOriNodes(List<Node> oriNodes) {
		this.oriNodes = oriNodes;
	}
	public FixData(long edge_ID, long from_Node_ID, long to_Node_ID,
			int two_Way, List nodes, List<Node> oriNodes) {
		super();
		this.edge_ID = edge_ID;
		this.from_Node_ID = from_Node_ID;
		this.to_Node_ID = to_Node_ID;
		this.two_Way = two_Way;
		this.nodes = nodes;
		this.oriNodes = oriNodes;
	}
	
	

	
	
}
