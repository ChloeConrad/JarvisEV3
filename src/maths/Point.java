package maths;

public class Point {
	private double x;
	private double y;
	
	public Point() {
		this.x=0;
		this.y=0;
		
	}
	public Point(double x,double y) {
		this.x=x;
		this.y=y;
	}
	
	public Point (Point p) {
		this.x=p.x;
		this.y=p.y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public double distance(Point p) {
		double d;
		d= Math.sqrt((this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y));
		System.out.println(d);
		return d;
	}
}
