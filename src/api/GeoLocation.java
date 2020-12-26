package api;

public class GeoLocation implements geo_location{
	private double x,y,z ;
	
	
	
	
	public GeoLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	 public GeoLocation() {
		 this.x = 0;
		 this.y = 0; 
		 this.z = 0; 
	}

	@Override
	public double x() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double y() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double z() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public double distance(geo_location g) {
		return  Math.sqrt(Math.pow(this.x-g.x(), 2)+Math.pow(this.y-g.y(), 2)+Math.pow(this.z - g.z(), 2));
	}

}
