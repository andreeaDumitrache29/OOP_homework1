/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Class used for storing an array of geometric objects
 */
public class FigureArray {
	private GeometricObject[] figures;
	private int size;

	/**
	 * implicit constructor
	 */
	public FigureArray() {
		figures = new GeometricObject[10];
		size = 0;
	}

	/**
	 * 
	 * @param x
	 *            add element x to the array
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
	 *            remove element x from array
	 */
	public void RemoveFigure(GeometricObject x) {
		int pos = 0;
		for (int i = 0; i < size; i++) {
			if (figures[i].getID() == x.getID()) {
				pos = i;
				break;
			}
		}
		for (int i = pos; i < size - 1; i++) {
			figures[i] = figures[i + 1];
		}
		size--;

	}

	/**
	 * 
	 * @param id
	 *            id of an object
	 * @return element from array with the given id
	 */
	public GeometricObject FindObject(int id) {
		for (int i = 0; i < size; i++) {
			if (figures[i].getID() == id)
				return figures[i];
		}
		return null;
	}
}
