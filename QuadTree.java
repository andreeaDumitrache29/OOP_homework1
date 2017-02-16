/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class QuadTree {
	/**
	 * root of the tree
	 */
	public static Node root;

	/**
	 * 
	 * @param xmin
	 *            left abscissa for root
	 * @param ymin
	 *            left ordinate for root
	 * @param xmax
	 *            right abscissa for root
	 * @param ymax
	 *            right ordinate for root
	 */
	public QuadTree(double xmin, double ymin, double xmax, double ymax) {
		root = new Node(xmin, ymin, xmax, ymax);
	}

	/**
	 * 
	 * @return tree root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * 
	 * @param x
	 *            abscissa of point to be tested
	 * @param y
	 *            ordinate of point to be tested
	 * @param xc
	 *            abscissa of center point
	 * @param xmax
	 *            abscissa of right point
	 * @param yc
	 *            ordinate of center point
	 * @param ymax
	 *            ordinate of left point
	 * @return if point belongs to first Quadrant
	 */
	public boolean TestFirstQuadrant(double x, double y, double xc, double xmax, double yc, double ymax) {
		if (x >= xc && x <= xmax && y >= yc && y <= ymax)
			return true;
		return false;
	}

	/**
	 * 
	 * @param x
	 *            abscissa of point to be tested
	 * @param y
	 *            ordinate of point to be tested
	 * @param xc
	 *            abscissa of center point
	 * @param xmin
	 *            abscissa of the leftmost point
	 * @param yc
	 *            ordinate of center point
	 * @param ymax
	 *            ordinate of the leftmost point
	 * @return if point belongs to second quadrant
	 */
	public boolean TestSecondQuadrant(double x, double y, double xc, double xmin, double yc, double ymax) {
		if (x >= xmin && x <= xc && y >= yc && y <= ymax)
			return true;
		return false;
	}

	/**
	 * 
	 * @param x
	 *            abscissa of point to be tested
	 * @param y
	 *            ordinate of point to be tested
	 * @param xc
	 *            abscissa of center point
	 * @param xmin
	 *            abscissa of the leftmost point
	 * @param yc
	 *            ordinate of center point
	 * @param ymin
	 *            ordinate of the leftmost point
	 * @return if point belongs to the third quadrant
	 */
	public boolean TestThirdQuadrant(double x, double y, double xc, double xmin, double yc, double ymin) {
		if (x >= xmin && x <= xc && y >= ymin && y <= yc)
			return true;
		return false;
	}

	/**
	 * 
	 * @param x
	 *            abscissa of point to be tested
	 * @param y
	 *            ordinate of point of be tested
	 * @param xc
	 *            abscissa of center point
	 * @param xmax
	 *            abscissa of the rightmost point
	 * @param yc
	 *            ordinate of center point
	 * @param ymin
	 *            ordinate of the leftmost point
	 * @return check if point belongs to the fourth quadrant
	 */
	public boolean TestFourthQuadrant(double x, double y, double xc, double xmax, double yc, double ymin) {
		if (x >= xc && x <= xmax && y >= ymin && y <= yc)
			return true;
		return false;
	}

	/**
	 * 
	 * @param node
	 *            parent node
	 *            <p>
	 *            the method initializes the four sons of the parent node with
	 *            the appropriate coordinates, checks to which of the four sons
	 *            the figures from the parent node belong, it inserts them
	 *            correspondingly and then erases figures from parent node
	 */
	public void Split(Node node) {
		Node C1 = new Node((node.getXmax() - node.getXmin()) / 2 + node.getXmin(),
				(node.getYmax() - node.getYmin()) / 2 + node.getYmin(), node.getXmax(), node.getYmax());
		Node C2 = new Node(node.getXmin(), (node.getYmax() - node.getYmin()) / 2 + node.getYmin(),
				(node.getXmax() - node.getXmin()) / 2 + node.getXmin(), node.getYmax());
		Node C3 = new Node(node.getXmin(), node.getYmin(), (node.getXmax() - node.getXmin()) / 2 + node.getXmin(),
				(node.getYmax() - node.getYmin()) / 2 + node.getYmin());
		Node C4 = new Node((node.getXmax() - node.getXmin()) / 2 + node.getXmin(), node.getYmin(), node.getXmax(),
				(node.getYmax() - node.getYmin()) / 2 + node.getYmin());

		node.setTopRight(C1);
		node.setTopLeft(C2);
		node.setBottomLeft(C3);
		node.setBottomRight(C4);
		int n = node.getSize();

		for (int i = 0; i < n; ++i) {

			if (node.getFigures()[i].Collision(node.getTopRight().getXmin(), node.getTopRight().getXmax(),
					node.getTopRight().getYmin(), node.getTopRight().getYmax()) == true) {

				node.getTopRight().addFigure(node.getFigures()[i]);
			}
			if (node.getFigures()[i].Collision(node.getTopLeft().getXmin(), node.getTopLeft().getXmax(),
					node.getTopLeft().getYmin(), node.getTopLeft().getYmax()) == true) {

				node.getTopLeft().addFigure(node.getFigures()[i]);
			}
			if (node.getFigures()[i].Collision(node.getBottomRight().getXmin(), node.getBottomRight().getXmax(),
					node.getBottomRight().getYmin(), node.getBottomRight().getYmax()) == true) {

				node.getBottomRight().addFigure(node.getFigures()[i]);
			}
			if (node.getFigures()[i].Collision(node.getBottomLeft().getXmin(), node.getBottomLeft().getXmax(),
					node.getBottomLeft().getYmin(), node.getBottomLeft().getYmax()) == true) {

				node.getBottomLeft().addFigure(node.getFigures()[i]);
			}

		}
		for (int i = 0; i < n; i++) {
			node.RemoveFigure(node.getFigures()[0]);
		}
	}

	/**
	 * 
	 * @param node
	 *            node which is tested for insert; starts from tree root
	 * @param x
	 *            object to be inserted
	 * @return true or false, depending on whether the insertion has ended
	 *         successfully or not
	 *         <p>
	 *         if the node is a leaf with no objects in it, insert x and return
	 *         true
	 *         <p>
	 *         if node to be tested is not a leaf, then check to which of the
	 *         for children the object belongs and call insert on the
	 *         appropriate node
	 *         <p>
	 *         if node to be tested has objects in its array, then check if x
	 *         collides with any of the figures already belonging to the node.
	 *         if not, then split the node and call insert on the appropriate
	 *         children; else insert directly into the node
	 */
	public boolean Insert(Node node, GeometricObject x) {

		if (node.getSize() == 0 && node.isLeaf() == true) {
			node.addFigure(x);
			return true;

		}
		if (node.isLeaf() == false) {
			if (x.Collision(node.getTopRight().getXmin(), node.getTopRight().getXmax(), node.getTopRight().getYmin(),
					node.getTopRight().getYmax()) == true) {
				Insert(node.getTopRight(), x);
			}
			if (x.Collision(node.getTopLeft().getXmin(), node.getTopLeft().getXmax(), node.getTopLeft().getYmin(),
					node.getTopLeft().getYmax()) == true) {
				Insert(node.getTopLeft(), x);
			}
			if (x.Collision(node.getBottomRight().getXmin(), node.getBottomRight().getXmax(),
					node.getBottomRight().getYmin(), node.getBottomRight().getYmax()) == true) {
				Insert(node.getBottomRight(), x);
			}
			if (x.Collision(node.getBottomLeft().getXmin(), node.getBottomLeft().getXmax(),
					node.getBottomLeft().getYmin(), node.getBottomLeft().getYmax()) == true) {
				Insert(node.getBottomLeft(), x);
			}
			return true;
		} else if (node.getSize() > 0) {

			boolean ok = false;
			for (int i = 0; i < node.getSize(); ++i) {
				if (x.Collision(node.getFigures()[i].getXl(), node.getFigures()[i].getXr(),
						node.getFigures()[i].getYl(), node.getFigures()[i].getYr()) == true) {
					ok = true;
					break;
				}
			}

			if (ok == false) {
				Split(node);

				if (x.Collision(node.getTopRight().getXmin(), node.getTopRight().getXmax(),
						node.getTopRight().getYmin(), node.getTopRight().getYmax()) == true) {

					Insert(node.getTopRight(), x);
				}
				if (x.Collision(node.getTopLeft().getXmin(), node.getTopLeft().getXmax(), node.getTopLeft().getYmin(),
						node.getTopLeft().getYmax()) == true) {
					Insert(node.getTopLeft(), x);
				}
				if (x.Collision(node.getBottomRight().getXmin(), node.getBottomRight().getXmax(),
						node.getBottomRight().getYmin(), node.getBottomRight().getYmax()) == true) {
					Insert(node.getBottomRight(), x);
				}
				if (x.Collision(node.getBottomLeft().getXmin(), node.getBottomLeft().getXmax(),
						node.getBottomLeft().getYmin(), node.getBottomLeft().getYmax()) == true) {
					Insert(node.getBottomLeft(), x);
				}

			} else {
				node.addFigure(x);

			}
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @param root
	 *            display tree starting from root
	 */
	public void display(Node root) {
		if (root != null) {
			root.DisplayFigures();
			display(root.getTopLeft());
			display(root.getTopRight());
			display(root.getBottomLeft());
			display(root.getBottomRight());
		}
	}

	/**
	 * 
	 * @param x
	 *            abscissa of point to be tested
	 * @param y
	 *            ordinate of point to be tested
	 * @param node
	 *            node to be tested
	 * @param rez
	 *            array which contains the ID-s of the figures to which the
	 *            point belongs
	 *            <p>
	 *            if node to be tested has no figures in its array and no
	 *            children, then return
	 *            <p>
	 *            if node to be tested is not a leaf: check to which of the four
	 *            children the point belongs and call SearchPoint on the
	 *            appropriate son
	 *            <p>
	 *            if node to be tested has figures in its array: for each
	 *            instance of geometric objects (circle, rhombus, rectangle and
	 *            triangle), call the corresponding VerifyPoint method from each
	 *            class to check if the point belongs to the figure; if the
	 *            result is positive, add the id of the figure to the rez array
	 */
	public void SearchPoint(double x, double y, Node node, Array rez) {
		if (node.getSize() == 0 && node.isLeaf() == true) {
			return;
		}
		if (node.isLeaf() == false) {
			double xc = (node.getXmax() - node.getXmin()) / 2 + node.getXmin();
			double yc = (node.getYmax() - node.getYmin()) / 2 + node.getYmin();
			if (TestFirstQuadrant(x, y, xc, node.getXmax(), yc, node.getYmax()) == true) {

				SearchPoint(x, y, node.getTopRight(), rez);
			}
			if (TestSecondQuadrant(x, y, xc, node.getXmin(), yc, node.getYmax()) == true) {

				SearchPoint(x, y, node.getTopLeft(), rez);
			}
			if (TestThirdQuadrant(x, y, xc, node.getXmin(), yc, node.getYmin()) == true) {

				SearchPoint(x, y, node.getBottomLeft(), rez);
			}
			if (TestFourthQuadrant(x, y, xc, node.getXmax(), yc, node.getYmin()) == true) {

				SearchPoint(x, y, node.getBottomRight(), rez);
			}
		}
		if (node.getSize() > 0) {
			for (int i = 0; i < node.getSize(); ++i) {

				if (node.getFigures()[i].VeirfyPoint(x, y) == true) {
					rez.add(node.getFigures()[i].getID());
				}
			}
			return;
		}
		return;
	}

	/**
	 * 
	 * @param x
	 *            figure to be tested
	 * @param node
	 *            node to be tested
	 * @param v
	 *            array to store the id-s of the figures which collide with the
	 *            given figure x
	 *            <p>
	 *            if node is leaf and has no children return;
	 *            <p>
	 *            if node has children, but no figures in its array (thus, it is
	 *            a parent for other nodes): check to which of the four sons the
	 *            figure x belongs and continue searching in the appropriate
	 *            quadrants
	 *            <p>
	 *            if node to be tested has figures: for each figure in the array
	 *            of figures check if x and the current figure collide by
	 *            calling the Collision method from the Geometric object class;
	 *            if the answer is true, add the id of the current figure
	 */
	public void TestCollision(GeometricObject x, Node node, Array v) {

		if (node.getSize() == 0 && node.isLeaf() == true) {
			return;
		}
		if (node.isLeaf() == false) {
			if (x.Collision(node.getTopRight().getXmin(), node.getTopRight().getXmax(), node.getTopRight().getYmin(),
					node.getTopRight().getYmax()) == true) {

				TestCollision(x, node.getTopRight(), v);
			}
			if (x.Collision(node.getTopLeft().getXmin(), node.getTopLeft().getXmax(), node.getTopLeft().getYmin(),
					node.getTopLeft().getYmax()) == true) {
				TestCollision(x, node.getTopLeft(), v);
			}
			if (x.Collision(node.getBottomRight().getXmin(), node.getBottomRight().getXmax(),
					node.getBottomRight().getYmin(), node.getBottomRight().getYmax()) == true) {
				TestCollision(x, node.getBottomRight(), v);
			}
			if (x.Collision(node.getBottomLeft().getXmin(), node.getBottomLeft().getXmax(),
					node.getBottomLeft().getYmin(), node.getBottomLeft().getYmax()) == true) {
				TestCollision(x, node.getBottomLeft(), v);
			}

		}
		if (node.getSize() > 0) {
			for (int i = 0; i < node.getSize(); ++i) {

				if (node.getFigures()[i].Collision(x.getXl(), x.getXr(), x.getYl(), x.getYr()) == true) {
					v.add(node.getFigures()[i].getID());

				}
			}
		} else
			return;
	}

	/**
	 * 
	 * @param x
	 *            object to be deleted
	 * @param node
	 *            node to be tested
	 * @param parent
	 *            parent of node to be tested
	 *            <p>
	 *            if node to be tested is null return
	 *            <p>
	 *            if node to be tested has no objects in its array: search in
	 *            the children to which the figure x belongs
	 *            <p>
	 *            if the node has figures in its node: search amongst them for
	 *            the figure to be deleted and erase it if it is found.
	 * 
	 */
	public void Delete(GeometricObject x, Node node, Node parent) {
		if (node == null)
			return;
		if (node.getSize() == 0 && node.isLeaf() == false) {
			if (x.Collision(node.getTopRight().getXmin(), node.getTopRight().getXmax(), node.getTopRight().getYmin(),
					node.getTopRight().getYmax()) == true) {

				Delete(x, node.getTopRight(), node);
			}
			if (x.Collision(node.getTopLeft().getXmin(), node.getTopLeft().getXmax(), node.getTopLeft().getYmin(),
					node.getTopLeft().getYmax()) == true) {
				Delete(x, node.getTopLeft(), node);
			}
			if (x.Collision(node.getBottomRight().getXmin(), node.getBottomRight().getXmax(),
					node.getBottomRight().getYmin(), node.getBottomRight().getYmax()) == true) {
				Delete(x, node.getBottomRight(), node);
			}
			if (x.Collision(node.getBottomLeft().getXmin(), node.getBottomLeft().getXmax(),
					node.getBottomLeft().getYmin(), node.getBottomLeft().getYmax()) == true) {
				Delete(x, node.getBottomLeft(), node);
			}
		}
		if (node.getSize() == 0 && node.isLeaf() == true) {
			return;
		}
		if (node.getSize() != 0) {
			for (int i = 0; i < node.getSize(); ++i) {
				if (node.getFigures()[i].getID() == x.getID()) {
					node.RemoveFigure(node.getFigures()[i]);
				}
			}
		}
		return;
	}

	/**
	 * 
	 * @param parent
	 *            node from which the remake of the tree starts
	 *            <p>
	 *            if node to be tested or its children are null return;
	 *            <p>
	 *            if none of the children of the node have figures in their
	 *            arrays, then erase the nodes
	 *            <p>
	 *            if only one of the four children has figures in its array and
	 *            the others do not: copy the figures in the array of the parent
	 *            and erase the nodes
	 *            <p>
	 *            if all four nodes contain the same figures: copy them into the
	 *            parent and erase the nodes
	 *            <p>
	 *            continue searching in the children of the node
	 */
	void Remake(Node parent) {
		if (parent != null && parent.getBottomLeft() != null && parent.getBottomRight() != null
				&& parent.getTopLeft() != null && parent.getTopRight() != null) {
			int i;
			if (parent.getBottomLeft().getSize() == 0 && parent.getBottomRight().getSize() == 0
					&& parent.getTopLeft().getSize() == 0 && parent.getTopRight().getSize() == 0) {

				if (parent.getBottomLeft().isLeaf() == true && parent.getBottomRight().isLeaf() == true
						&& parent.getTopLeft().isLeaf() == true && parent.getTopRight().isLeaf() == true) {

					parent.setBottomLeft(null);
					parent.setBottomRight(null);
					parent.setTopLeft(null);
					parent.setTopRight(null);
				}
				return;
			}
			if (parent.getBottomLeft().getSize() != 0 && parent.getBottomRight().getSize() == 0
					&& parent.getTopLeft().getSize() == 0 && parent.getTopRight().getSize() == 0) {

				if (parent.getBottomLeft().isLeaf() == true && parent.getBottomRight().isLeaf() == true
						&& parent.getTopLeft().isLeaf() == true && parent.getTopRight().isLeaf() == true) {
					for (i = 0; i < parent.getBottomLeft().getSize(); ++i) {
						parent.addFigure(parent.getBottomLeft().getFigures()[i]);
					}

					parent.setBottomLeft(null);
					parent.setBottomRight(null);
					parent.setTopLeft(null);
					parent.setTopRight(null);
				}
				return;
			}
			if (parent.getBottomLeft().getSize() == 0 && parent.getBottomRight().getSize() != 0
					&& parent.getTopLeft().getSize() == 0 && parent.getTopRight().getSize() == 0) {

				if (parent.getBottomLeft().isLeaf() == true && parent.getBottomRight().isLeaf() == true
						&& parent.getTopLeft().isLeaf() == true && parent.getTopRight().isLeaf() == true) {

					for (i = 0; i < parent.getBottomRight().getSize(); ++i) {
						parent.addFigure(parent.getBottomRight().getFigures()[i]);
					}

					parent.setBottomLeft(null);
					parent.setBottomRight(null);
					parent.setTopLeft(null);
					parent.setTopRight(null);
				}
			} else if (parent.getBottomLeft().getSize() == 0 && parent.getBottomRight().getSize() == 0
					&& parent.getTopLeft().getSize() != 0 && parent.getTopRight().getSize() == 0) {

				if (parent.getBottomLeft().isLeaf() == true && parent.getBottomRight().isLeaf() == true
						&& parent.getTopLeft().isLeaf() == true && parent.getTopRight().isLeaf() == true) {

					for (i = 0; i < parent.getTopLeft().getSize(); ++i) {
						parent.addFigure(parent.getTopLeft().getFigures()[i]);
					}

					parent.setBottomLeft(null);
					parent.setBottomRight(null);
					parent.setTopLeft(null);
					parent.setTopRight(null);
				}
			} else if (parent.getBottomLeft().getSize() == 0 && parent.getBottomRight().getSize() == 0
					&& parent.getTopLeft().getSize() == 0 && parent.getTopRight().getSize() != 0) {

				if (parent.getBottomLeft().isLeaf() == true && parent.getBottomRight().isLeaf() == true
						&& parent.getTopLeft().isLeaf() == true && parent.getTopRight().isLeaf() == true) {

					for (i = 0; i < parent.getTopRight().getSize(); ++i) {
						parent.addFigure(parent.getTopRight().getFigures()[i]);
					}

					parent.setBottomLeft(null);
					parent.setBottomRight(null);
					parent.setTopLeft(null);
					parent.setTopRight(null);
				}
			} else if (parent.getBottomLeft().IdenticalArrays(parent.getBottomRight())
					&& parent.getBottomRight().IdenticalArrays(parent.getTopLeft())
					&& parent.getTopLeft().IdenticalArrays(parent.getTopRight())) {
				if (parent.getBottomLeft().isLeaf() == true && parent.getBottomRight().isLeaf() == true
						&& parent.getTopLeft().isLeaf() == true && parent.getTopRight().isLeaf() == true) {

					for (i = 0; i < parent.getTopRight().getSize(); ++i) {
						parent.addFigure(parent.getTopRight().getFigures()[i]);
					}

					parent.setBottomLeft(null);
					parent.setBottomRight(null);
					parent.setTopLeft(null);
					parent.setTopRight(null);
				}
			}
		}
		if (parent != null && parent.getTopLeft() != null)
			Remake(parent.getTopLeft());
		if (parent != null && parent.getTopRight() != null)
			Remake(parent.getTopRight());
		if (parent != null && parent.getBottomLeft() != null)
			Remake(parent.getBottomLeft());
		if (parent != null && parent.getBottomRight() != null)
			Remake(parent.getBottomRight());
	}
}