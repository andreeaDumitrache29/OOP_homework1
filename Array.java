
/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         This class is used for creating the array in which the results of the
 *         SearchPoint and TestCollision methods will be stored
 */
public class Array {
	/**
	 * v = array of int size = number of elements introduced in the array
	 */
	int[] v;
	int size;

	/**
	 * Class constructor. Creates implicit array of dimension 5
	 */
	public Array() {
		size = 0;
		v = new int[5];
	}

	/**
	 * 
	 * @param x
	 *            element to be added to the array
	 *            <p>
	 *            Function checks for duplicates in the array, reallocates the
	 *            array, if necessary, and adds the elements to the array,
	 *            increasing the variable size
	 */
	public void add(int x) {
		int j;
		for (j = 0; j < v.length; ++j) {
			if (v[j] == x) {
				return;
			}
		}
		/**
		 * check if the number of elements has reached the allocated limit If
		 * yes, reallocate the array.
		 * <p>
		 * make an ordered insertion of the element in array
		 */

		if (size == v.length) {
			int[] copy = new int[2 * v.length];
			System.arraycopy(v, 0, copy, 0, v.length);
			v = copy;
		}
		int pos = 0;
		while (v[pos] < x && pos < size)
			pos++;

		int temp1 = v[pos];
		int temp2;

		for (j = pos + 1; j <= size; j++) {
			temp2 = v[j];
			v[j] = temp1;
			temp1 = temp2;
		}
		v[pos] = x;
		size++;
	}

	/**
	 * 
	 * @return array of int
	 */
	public int[] getV() {
		return v;
	}

	/**
	 * 
	 * @param v
	 *            new array of int
	 */
	public void setV(int[] v) {
		this.v = v;
	}

	/**
	 * 
	 * @return number of elements introduced in the array
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 *            number of elements introduced
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 
	 * @return length of the array
	 */
	public int getLength() {
		return v.length;
	}

	/**
	 * makes all elements of the array 0
	 */
	public void Reinitialize() {
		for (int k = 0; k < v.length; ++k) {
			v[k] = 0;
		}
	}

	/**
	 *
	 * @param pos
	 *            position to be check in the array
	 * @return true or false: number at given position is zero
	 */
	public boolean isZero(int pos) {
		if (pos >= 0 && pos < v.length)
			return (v[pos] == 0);
		return false;
	}

	/**
	 * 
	 * @param pos
	 *            position to be checked in the array
	 * @return element at position pos in the array
	 */
	public int getElement(int pos) {
		return v[pos];
	}
}