/**
 * 
 * @author Dumirache Daniela Andreea
 *
 */
public abstract class GeometricObject {
	private int ID;
	protected double xl, yl, xr, yr;

	/**
	 * implicit constructor
	 */
	public GeometricObject() {
		super();
	}

	/**
	 * 
	 * @param iD
	 *            id of the figure
	 * @param xl
	 *            abscissa of the leftmost point
	 * @param yl
	 *            ordinate of the leftmost point
	 * @param xr
	 *            abscissa of the rightmost point
	 * @param yr
	 *            ordinate of the rightmost point
	 */
	public GeometricObject(int iD, double xl, double yl, double xr, double yr) {
		super();
		ID = iD;
		this.xl = xl;
		this.yl = yl;
		this.xr = xr;
		this.yr = yr;
	}

	/**
	 * 
	 * @return id of the figure
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param ID
	 *            new id
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * 
	 * @return abscissa of the leftmost point
	 */
	public double getXl() {
		return xl;
	}

	/**
	 * 
	 * @return ordinate of the leftmost point
	 */
	public double getYl() {
		return yl;
	}

	/**
	 * 
	 * @return abscissa of the rightmost point
	 */
	public double getXr() {
		return xr;
	}

	/**
	 * 
	 * @return ordinate of the rightmost point
	 */
	public double getYr() {
		return yr;
	}

	/**
	 * 
	 * @param x
	 *            abscissa of point to be tested
	 * @param y
	 *            ordinate of point to be tested
	 * @return whether or not the point belongs in a specific type of figure
	 *         <p>
	 *         implemented by each subclass
	 */
	abstract boolean VeirfyPoint(double x, double y);

	/**
	 * Creates phaser rectangle for a given figure;
	 * <p>
	 * implemented correspondingly by each subclass
	 */
	abstract void CreatePhaserRectangle();

	/**
	 * Displays the Id and coordinates for an object
	 */
	@Override
	public String toString() {
		return "GeometricObject [ID=" + ID + ", xl=" + xl + ", yl=" + yl + ", xr=" + xr + ", yr=" + yr + "]";
	}

	/**
	 * 
	 * @param xmin
	 *            abscissa of the leftmost point
	 * @param xmax
	 *            abscissa of the rightmost point
	 * @param ymin
	 *            ordinate of the leftmost point
	 * @param ymax
	 *            ordinate of the rightmost point
	 * @return if current object is in collision with a phaser rectangle given
	 *         given by its coordinates
	 */
	public boolean Collision(double xmin, double xmax, double ymin, double ymax) {

		if (this.xl < xmin && this.xr < xmin)
			return false;
		if (this.yl < ymin && this.yr < ymin)
			return false;
		if (this.xl > xmax && this.xr > xmax)
			return false;
		if (this.yl > ymax && this.yr > ymax)
			return false;

		return true;
	}
}