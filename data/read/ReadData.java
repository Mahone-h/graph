package read;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadData {
	//1375004735;39.8619;116.29702;294;Tue May 01 00:01:43 CST 2018;0
		public static List<Point> getDatas() throws Exception {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Lenovo/Desktop/txt/gps.txt")));
			String line=null;
			List<Point> datas=new ArrayList<Point>();
			Point p=null;
			while((line=reader.readLine())!=null){
				String[] str=line.split(";");
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
				p=new Point(Long.parseLong(str[0]), Double.parseDouble(str[1]), Double.parseDouble(str[2]), 
						  Integer.parseInt(str[3]), sdf.parse(str[4]), Integer.parseInt(str[5]));
				datas.add(p);
			}

			
			return datas;
			
		}
	}


