package com.hankcs.algorithm;

public class LSUM {
	
	public static void main (String[] args){
		
		int sum = 0;
		int[] [] arr = {{47,-122},{47,-12},{47,-122}};
		for(int x = 0; x<arr.length; x++){
			for(int y = 0; y<arr[x].length; y++){
				sum += arr[x][y];
			}
		}
		System.out.println("sum="+sum);
	}

}
