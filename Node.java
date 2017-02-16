/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Class used for storing an element of a quadtree
 *
 */
public class Node {
	/**
	 * <p>
	 * maximum coordinates for a Quadrant
	 * <p>
	 * array of GeometricObjects and number of elements introduced in the array
	 * <p>
	 * the four sons: top left quadrant, top right quadrant, bottom left
	 * quadrant, bottom right quadrant
	 */
	double xmin, ymin, xmax, ymax;
	int size = 0;
	GeometricObject[] figures;
	Node TopLeft, TopRight, BottomLeft, BottomRight;

	/**
	 * implicit constructor
	 */
	public Node() {
	}

	/**
	 * initialize the margins of the quadrant and create a new array of 5
	 * geometric objects
	 * 
	 * @param xmin
	 *            left abscissa
	 * @param ymin
	 *            left ordinate
	 * @param xmax
	 *            right abscissa
	 * @param ymax
	 *            right ordinate
	 */
	public Node(double xmin, double ymin, double xmax, double ymax) {
		super();
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
		figures = new GeometricObject[5];
		size = 0;
	}

	/**
	 * 
	 * @return left ordinate
	 */
	public double getXmin() {
		return xmin;
	}

	/**
	 * 
	 * @param xmin
	 *            new left abscissa
	 */
	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	/**
	 * 
	 * @return left ordinate
	 */
	public double getYmin() {
		return ymin;
	}

	/**
	 * 
	 * @param ymin
	 *            new left ordinate
	 */
	public void setYmin(double ymin) {
		this.ymin = ymin;
	}

	/**
	 * 
	 * @return right abscissa
	 */
	public double getXmax() {
		return xmax;
	}

	/**
	 * 
	 * @param xmax
	 *            new right abscissa
	 */
	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

	/**
	 * 
	 * @return right ordinate
	 */
	public double getYmax() {
		return ymax;
	}

	/**
	 * 
	 * @param ymax
	 *            new right ordinate
	 */
	public void setYmax(double ymax) {
		this.ymax = ymax;
	}

	/**
	 * 
	 * @return number of elements in the geometric object array
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 *            new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 
	 * @return array of geometric objects
	 */
	public GeometricObject[] getFigures() {
		return figures;
	}

	/**
	 * 
	 * @param figures
	 *            new array of geometric objects
	 */
	public void setFigures(GeometricObject[] figures) {
		this.figures = figures;
	}

	/**
	 * 
	 * @return second son
	 */
	public Node getTopLeft() {
		return TopLeft;
	}

	/**
	 * 
	 * @param topLeft
	 *            new second son
	 */
	public void setTopLeft(Node topLeft) {
		TopLeft = topLeft;
	}

	/**
	 * 
	 * @return first son
	 */
	public Node getTopRight() {
		return TopRight;
	}

	/**
	 * 
	 * @param topRight
	 *            new first son
	 */
	public void setTopRight(Node topRight) {
		TopRight = topRight;
	}

	/**
	 * 
	 * @return third son
	 */
	public Node getBottomLeft() {
		return BottomLeft;
	}

	/**
	 * 
	 * @param bottomLeft
	 *            new third son
	 */
	public void setBottomLeft(Node bottomLeft) {
		BottomLeft = bottomLeft;
	}

	/**
	 * 
	 * @return fourth son
	 */
	public Node getBottomRight() {
		return BottomRight;
	}

	/**
	 * 
	 * @param bottomRight
	 *            new fourth son
	 */
	public void setBottomRight(Node bottomRight) {
		BottomRight = bottomRight;
	}

	/**
	 * 
	 * @param x
	 *            new object to be added to the array of geometric objects
	 *            <P>
	 *            the method reallocates the array, if
	 *            necessary, and adds a new element to the array
	 */

	public void addFigure(GeometricObject x) {
		if (size == figures.length) {
			GeometricObject[] copy = new GeometricObject[2 * figures.length];
			System.arraycopy(figures, 0, copy, 0, figures.length);
			figures = copy;
		}
		figures[size++] = x;
	}

	/**
	 * 
	 * @param x
	 *            object to be removed from the array
	 *            <P>
	 *            method checks for the position at which the given elements
	 *            lies in the array and from there on replaces the current
	 *            element with the following one, decrementing the size at the
	 *            end
	 */
	public void RemoveFigure(GeometricObject x) {
		if (size == 0)
			return;
		for (int i = 0; i < size; i++) {
			if (figures[i].getID() == x.getID()) {
				for (int j = i; j < size - 1; j++) {
					figures[j] = figures[j + 1];
				}
			}
		}
		figures[size - 1] = null;
		size--;
	}

	/**
	 * 
	 * @return true if the current node is a leaf, false if it is not
	 */
	public boolean isLeaf() {
		return (this.TopLeft == null && this.TopRight == null && this.BottomLeft == null && this.BottomRight == null);
	}

	/**
	 * display figures in the array of current node
	 */
	public void DisplayFigures() {
		for (int i = 0; i < figures.length; ++i) {
			if (figures[i] != null)
				System.out.println(figures[i].toString());
			else
				break;
		}
	}

	/**
	 * 
	 * @param node
	 *            node with which the current one is going to be tested
	 * @return true if the current node and the one given as parameter have
	 *         identical arrays of geometric objects
	 */
	public boolean IdenticalArrays(Node node) {
		if (this.size != node.getSize())
			return false;
		for (int i = 0; i < this.size; ++i) {
			if (this.figures[i].getID() != node.getFigures()[i].getID()) {
				return false;
			}
		}
		return true;
	}
}