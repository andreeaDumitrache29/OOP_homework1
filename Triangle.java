/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class Triangle extends GeometricObject {

	private double x1, y1, x2, y2, x3, y3;

	/**
	 * implicit constructor
	 */
	public Triangle() {

	}

	/**
	 * 
	 * @param x1
	 *            top abscissa
	 * @param y1
	 *            top ordinate
	 * @param x2
	 *            left abscissa
	 * @param y2
	 *            left ordinate
	 * @param x3
	 *            right abscissa
	 * @param y3
	 *            right ordinate
	 */
	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
	}

	/**
	 * 
	 * @return top abscissa
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * 
	 * @return top ordinate
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * 
	 * @return left abscissa
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * 
	 * @return left ordinate
	 */
	public double getY2() {
		return y2;
	}

	/**
	 * 
	 * @return right abscissa
	 */
	public double getX3() {
		return x3;
	}

	/**
	 * 
	 * @return right ordinate
	 */
	public double getY3() {
		return y3;
	}

	/**
	 * 
	 * @param x1
	 *            top abscissa
	 * @param y1
	 *            top ordinate
	 * @param x2
	 *            left abscissa
	 * @param y2
	 *            left ordinate
	 * @param x3
	 *            right abscissa
	 * @param y3
	 *            right ordinate
	 * @return triangle area
	 */
	double area(double x1, double y1, double x2, double y2, double x3, double y3) {
		return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}

	/**
	 * check if a point belongs to the triangle
	 */
	@Override
	boolean VeirfyPoint(double x, double y) {
		double A = area(x1, y1, x2, y2, x3, y3);
		double A1 = area(x, y, x2, y2, x3, y3);
		double A2 = area(x1, y1, x, y, x3, y3);
		double A3 = area(x1, y1, x2, y2, x, y);
		return (A == (A1 + A2 + A3));
	}

	/**
	 * create phaser rectangle
	 */
	@Override
	void CreatePhaserRectangle() {
		xl = x2;
		yl = y2;
		xr = x3;
		yr = y1;
	}

}