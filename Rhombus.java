/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Class used for representing a rhombus in the quadtree
 */
public class Rhombus extends GeometricObject {
	private double x1, y1, x2, y2, x3, y3, x4, y4;

	/**
	 * implicit constructor
	 */
	public Rhombus() {

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
	 *            bottom abscissa
	 * @param y3
	 *            bottom ordinate
	 * @param x4
	 *            right abscissa
	 * @param y4
	 *            right ordinate
	 */
	public Rhombus(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
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
	 * @return bottom abscissa
	 */
	public double getX3() {
		return x3;
	}

	/**
	 * bottom ordinate
	 * 
	 * @return
	 */
	public double getY3() {
		return y3;
	}

	/**
	 * 
	 * @return right abscissa
	 */
	public double getX4() {
		return x4;
	}

	/**
	 * 
	 * @return right ordinate
	 */
	public double getY4() {
		return y4;
	}

	/**
	 * check if point is inside rhombus
	 * <P>
	 * create two rectangles using the diagonal of the rhombus check if the
	 * point belongs to any of the triangles
	 */
	@Override
	boolean VeirfyPoint(double x, double y) {

		Triangle A1 = new Triangle(x1, y1, x2, y2, x4, y4);
		Triangle A2 = new Triangle(x3, y3, x2, y2, x4, y4);
		return (A1.VeirfyPoint(x, y) || A2.VeirfyPoint(x, y));
	}

	/**
	 * create the phaser rectangle
	 */
	@Override
	void CreatePhaserRectangle() {
		xl = x2;
		yl = y3;
		xr = x4;
		yr = y1;
	}
}