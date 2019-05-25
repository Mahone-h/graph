package DATA;

public class States {
	private double r1;
	private double r2;
	
	public States(double r1, double r2){
		this.r1=r1;
		this.r2=r2;
	}
	
	public double getr1(){return r1;}
	public double getr2(){return r2;}
	
	public void setr1(double r1){this.r1=r1;}
	public void setr2(double r2){this.r2=r2;}
	
	
	
	public static void main(String[] args){
		double r1[][]={{47.877409,-122.711338},{47.876503,-122.711078}};
		double r2[][]={{47.881301,-122.716309},{47.881650,-122.715329}};
		
		for(int x= 0; x<r1.length; x++){
			for(int y=0; y<r1[x].length; y++){
			System.out.print(r1[x][y]);
				
			
			for(int i= 0; i<r2.length; i++){
				for(int j=0; j<r2[x].length; j++){
					//System.out.println(r2[i][j]);
				}
				
			}
			}
			
		}
	}}	
		
	
	

		
	
	

