/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Class used for representing a circle in the quadtree
 *
 */
public class Circle extends GeometricObject {
	private double radius, x, y;

	/**
	 * implicit constructor
	 */
	public Circle() {

	}

	/**
	 * 
	 * @param radius
	 *            radius of the circle
	 * @param x
	 *            abscissa of the center
	 * @param y
	 *            ordinate of the center
	 */
	public Circle(double radius, double x, double y) {
		super();
		this.radius = radius;
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius
	 *            new radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * 
	 * @return abscissa of center point
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @param x
	 *            new abscissa
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @return ordinate of center point
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 *            new ordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * check if point belongs inside the circle
	 */
	@Override
	boolean VeirfyPoint(double x, double y) {
		return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) <= radius * radius;
	}

	/**
	 * create the phaser rectangle for the circle
	 */
	@Override
	void CreatePhaserRectangle() {
		xl = x - radius;
		yl = y - radius;
		xr = x + radius;
		yr = y + radius;
	}

}