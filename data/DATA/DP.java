package DATA;

public class DP {
	public static double[]DP(double lat, double lon){
		
         double L = 6381372 * Math.PI * 2;//�����ܳ�  
         double W=L;// ƽ��չ����x������ܳ�  
         double H=L/2;// y��Լ�����ܳ�һ
         double mill=2.3;// ����ͶӰ�е�һ����������Χ��Լ������2.3֮��  
         double x = lon * Math.PI / 180;// �����ȴӶ���ת��Ϊ����  
         double y = lat * Math.PI / 180;// ��γ�ȴӶ���ת��Ϊ����  
         y=1.25 * Math.log( Math.tan( 0.25 * Math.PI + 0.4 * y ) );// ����ͶӰ��ת��  
         // ����תΪʵ�ʾ���  
         x = ( W / 2 ) + ( W / (2 * Math.PI) ) * x;  
         y = ( H / 2 ) - ( H / ( 2 * mill ) ) * y;  
         double[] result=new double[2];  
         result[0]=x;  
         result[1]=y;  
         return result;  
    }
	
	public static void main(String[] args){
		double lat[] = {47.65,47.6675,47.6675,47.66751667};
		double lon[] = {-122.1070833,-122.1070667,-122.1070667,-122.1070333};
		 for (int i = 0; i < lat.length; i++) {
	            double[] result = DP(lat[i], lon[i]);
	            System.out.println(result[0] + ":" + result[1]);
	}
}
}
