package read;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class test {
	public static void main(String[] args) throws Exception {
		/*String str="Tue May 01 00:01:43 CST 2018";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.ENGLISH);
		Date date = sdf.parse(str);
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-mm-dd HH:MM:ss");
		System.out.println(sdf1.format(date));*/
		List<Point> datas=ReadData.getDatas();
		System.out.println(datas.size());
	}
}



