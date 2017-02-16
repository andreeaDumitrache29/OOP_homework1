/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Class used for representing a rectangle in the quadtree
 */

public class Rectangle extends GeometricObject {
	private double x1, y1, x2, y2;

	/**
	 * implicit constructor
	 */
	public Rectangle() {

	}

	/**
	 * 
	 * @param x1
	 *            left abscissa
	 * @param y1
	 *            left ordinate
	 * @param x2
	 *            right abscissa
	 * @param y2
	 *            right ordinate
	 */
	public Rectangle(double x1, double y1, double x2, double y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * 
	 * @return left abscissa
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * 
	 * @return left ordinate
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * 
	 * @return right abscissa
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * 
	 * @return right ordinate
	 */
	public double getY2() {
		return y2;
	}

	/**
	 * check if point of coordinate (x,y) belongs to the rectangle
	 */
	@Override
	boolean VeirfyPoint(double x, double y) {
		return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}

	/**
	 * Creates phaser rectangle for the given rectangle
	 */
	@Override
	void CreatePhaserRectangle() {
		xl = x1;
		yl = y1;
		xr = x2;
		yr = y2;
	}

}