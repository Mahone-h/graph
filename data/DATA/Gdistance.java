package DATA;

public class Gdistance {
	
		private static double EARTH_RADIUS = 6378137.0;
		private static double rad(double d)
		{
		   return d * Math.PI / 180.0;
		}
	 
		/**
		 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷纬锟斤拷之锟斤拷木锟斤拷锟�
		 * @param lat1
		 * @param lng1
		 * @param lat2
		 * @param lng2
		 * @return
		 */
		public static double getDistance(double lat1, double lng1,double lat2, double lng2)
		{
		   double radLat1 = rad(lat1);
		   double radLat2 = rad(lat2);
		   double a = radLat1 - radLat2;
		   double b = rad(lng1) - rad(lng2);
		   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
		    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		   s = s * EARTH_RADIUS;
		   s = Math.round(s * 10000d)/10000d;
			//double s = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lng2 - lng1, 2));

		   return s;
		}
		
		
		
		public static void main(String[] args) {
			System.out.println(Gdistance.getDistance(
					47.66751667,-122.1066,47.66707039478913,-122.1056037377332

					
					));
		}
	}


