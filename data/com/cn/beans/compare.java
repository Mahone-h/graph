package com.cn.beans;

import java.util.Comparator;

public class compare implements Comparator<FixData>{

	@Override
	public int compare(FixData list1, FixData list2) {
		double a=(double) list1.getNodes().get(0);
		double b=(double) list2.getNodes().get(0);
		if(a>b){
			return 1;
			}else if(a<b){
			return -1;
			}else{
			return 0;
			}		
	}

}
