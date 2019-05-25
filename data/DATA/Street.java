package DATA;

import java.util.ArrayList;
import java.util.List;

public class Street {

	double lonStart;
	double latStart;
	double lonEnd;
	double latEnd;
	double rate;
	double projectionLon;
	double projectionLat;
	public Street(double lonStart, double latStart, double lonEnd, double latEnd,double rate) {
		super();
		this.lonStart = lonStart;
		this.latStart = latStart;
		this.lonEnd = lonEnd;
		this.latEnd = latEnd;
		this.rate=rate;
	}
}
