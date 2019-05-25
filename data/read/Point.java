package read;

	import java.util.Date;

	public class Point {

		/**
		 * 属性
		 */
		private long gps_id;
		private double vehiclepositioninfo_lat;
		private double vehiclepositioninfo_lon;
		private int vehiclepositioninfo_direction;
		private Date vehiclepositioninfo_receivetime;
		private int is_overdue_max;

		/**
		 * 构造方法
		 */
		public Point() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Point(long gps_id, double vehiclepositioninfo_lat,
				double vehiclepositioninfo_lon, int vehiclepositioninfo_direction,
				Date vehiclepositioninfo_receivetime, int is_overdue_max) {
			super();
			this.gps_id = gps_id;
			this.vehiclepositioninfo_lat = vehiclepositioninfo_lat;
			this.vehiclepositioninfo_lon = vehiclepositioninfo_lon;
			this.vehiclepositioninfo_direction = vehiclepositioninfo_direction;
			this.vehiclepositioninfo_receivetime = vehiclepositioninfo_receivetime;
			this.is_overdue_max = is_overdue_max;
		}
		
		
		/**
		 * get set方法
		 * @return
		 */
		public long getGps_id() {
			return gps_id;
		}
		public void setGps_id(long gps_id) {
			this.gps_id = gps_id;
		}
		public double getVehiclepositioninfo_lat() {
			return vehiclepositioninfo_lat;
		}
		public void setVehiclepositioninfo_lat(double vehiclepositioninfo_lat) {
			this.vehiclepositioninfo_lat = vehiclepositioninfo_lat;
		}
		public double getVehiclepositioninfo_lon() {
			return vehiclepositioninfo_lon;
		}
		public void setVehiclepositioninfo_lon(double vehiclepositioninfo_lon) {
			this.vehiclepositioninfo_lon = vehiclepositioninfo_lon;
		}
		public int getVehiclepositioninfo_direction() {
			return vehiclepositioninfo_direction;
		}
		public void setVehiclepositioninfo_direction(int vehiclepositioninfo_direction) {
			this.vehiclepositioninfo_direction = vehiclepositioninfo_direction;
		}
		public Date getVehiclepositioninfo_receivetime() {
			return vehiclepositioninfo_receivetime;
		}
		public void setVehiclepositioninfo_receivetime(
				Date vehiclepositioninfo_receivetime) {
			this.vehiclepositioninfo_receivetime = vehiclepositioninfo_receivetime;
		}
		public int getIs_overdue_max() {
			return is_overdue_max;
		}
		public void setIs_overdue_max(int is_overdue_max) {
			this.is_overdue_max = is_overdue_max;
		}
		
		
		
	}


