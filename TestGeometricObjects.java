import java.io.*;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Main method. Declaration of variables. Read the input data from the
 *         "quadtree.in" file, call the appropriate method and print the result
 *         in the "quadtree.out" file
 */
public class TestGeometricObjects {
	public static void main(String[] args) throws IOException {
		FileReader file = new FileReader("quadtree.in");
		FileWriter log = new FileWriter("quadtree.out");
		BufferedReader sc = new BufferedReader(file);
		BufferedWriter writer = new BufferedWriter(log);
		String s = sc.readLine();
		String[] tokens = s.split(" ");
		/**
		 * coordinates for root quadrant
		 */
		double xmin = Double.parseDouble(tokens[0]);
		double ymin = Double.parseDouble(tokens[1]);
		double xmax = Double.parseDouble(tokens[2]);
		double ymax = Double.parseDouble(tokens[3]);
		double xc = (xmax - xmin) / 2 + xmin;
		double yc = (ymax - ymin) / 2 + ymin;
		QuadTree tree = new QuadTree(xmin, ymin, xmax, ymax);
		s = sc.readLine();

		int i;
		int print;
		double figura;
		double x1, y1, x2, y2, x3, y3, x4, y4;
		double R, x, y, xcerc, ycerc;
		int id;
		Array v = new Array();
		/**
		 * a is an array of geometric objects to which every figure that is
		 * inserted into the quadtree is added
		 */
		FigureArray a = new FigureArray();
		boolean ok;
		/**
		 * read from file as long as there are new lines
		 */
		while (s != null) {
			if (s.contentEquals("") || s.contentEquals("\n"))
				break;
			tokens = s.split(" ");
			switch (Integer.parseInt(tokens[0])) {
			case 11:
				figura = Double.parseDouble(tokens[1]);
				id = Integer.parseInt(tokens[2]);
				/**
				 * we have to insert an element into the quadtree: reads the
				 * appropriate coordinates for the figure, create the phaser
				 * rectangle and insert it into the tree
				 */
				if (Double.parseDouble(tokens[1]) == 1) {
					x1 = Double.parseDouble(tokens[3]);
					y1 = Double.parseDouble(tokens[4]);
					x2 = Double.parseDouble(tokens[5]);
					y2 = Double.parseDouble(tokens[6]);
					Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
					rectangle.CreatePhaserRectangle();
					rectangle.setID(Integer.parseInt(tokens[2]));
					tree.Insert(tree.getRoot(), rectangle);
					a.addFigure(rectangle);
				} else if (figura == 2) {
					x1 = Double.parseDouble(tokens[3]);
					y1 = Double.parseDouble(tokens[4]);
					x2 = Double.parseDouble(tokens[5]);
					y2 = Double.parseDouble(tokens[6]);
					x3 = Double.parseDouble(tokens[7]);
					y3 = Double.parseDouble(tokens[8]);
					Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
					triangle.CreatePhaserRectangle();
					triangle.setID(id);
					tree.Insert(tree.getRoot(), triangle);
					a.addFigure(triangle);
				} else if (figura == 4) {
					x1 = Double.parseDouble(tokens[3]);
					y1 = Double.parseDouble(tokens[4]);
					x2 = Double.parseDouble(tokens[5]);
					y2 = Double.parseDouble(tokens[6]);
					x3 = Double.parseDouble(tokens[7]);
					y3 = Double.parseDouble(tokens[8]);
					x4 = Double.parseDouble(tokens[9]);
					y4 = Double.parseDouble(tokens[10]);
					Rhombus rhombus = new Rhombus(x3, y3, x2, y2, x1, y1, x4, y4);
					rhombus.CreatePhaserRectangle();
					rhombus.setID(id);
					tree.Insert(tree.getRoot(), rhombus);
					a.addFigure(rhombus);

				} else {
					R = Double.parseDouble(tokens[3]);
					x = Double.parseDouble(tokens[4]);
					y = Double.parseDouble(tokens[5]);
					Circle circle = new Circle(R, x, y);
					circle.CreatePhaserRectangle();
					circle.setID(id);
					tree.Insert(tree.getRoot(), circle);
					a.addFigure(circle);
				}
				break;
			case 22:
				/**
				 * read the id of the figure to be deleted and call the Delete
				 * method. use the array a in order to find the object to be
				 * deleted so that it will not be mandatory to search the entire
				 * tree for the object;
				 * <p>
				 * after removal, call the remake methods on the subtrees to
				 * which the figure belongs;
				 */
				GeometricObject o = a.FindObject(Integer.parseInt(tokens[1]));
				tree.Delete(o, tree.getRoot(), null);
				a.RemoveFigure(o);
				if (o.Collision(xc, xmax, yc, ymax) == true)
					tree.Remake(tree.getRoot().getTopRight());
				if (o.Collision(xmin, xc, yc, ymax) == true)
					tree.Remake(tree.getRoot().getTopLeft());
				if (o.Collision(xmin, xc, ymin, yc) == true)
					tree.Remake(tree.getRoot().getBottomLeft());
				if (o.Collision(xc, xmax, ymin, yc) == true)
					tree.Remake(tree.getRoot().getBottomRight());

				break;
			case 33:
				/**
				 * read the coordinates of the point to be tested. Call the
				 * SearchPoint method. print into the file the resulting array
				 * from the method call
				 */
				tree.SearchPoint(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), tree.getRoot(), v);
				try {
					ok = false;
					print = 0;
					for (i = 0; i < v.getLength(); ++i) {
						if (v.isZero(i) == false) {
							ok = true;
							if (print != 0)
								writer.write(" ");
							writer.write("" + v.getElement(i));
							;
							print = 1;
						}
					}
					if (ok == false)
						writer.write("NIL");
					v.Reinitialize();
					writer.write('\n');

				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 44:
				/**
				 * read the coordinates of the figure to be tested call the
				 * TestCollision method and print the resulting array
				 */
				figura = Integer.parseInt(tokens[1]);
				if (figura == 1) {
					x1 = Double.parseDouble(tokens[2]);
					y1 = Double.parseDouble(tokens[3]);
					x2 = Double.parseDouble(tokens[4]);
					y2 = Double.parseDouble(tokens[5]);
					Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
					rectangle.CreatePhaserRectangle();
					rectangle.setID(-1);
					tree.TestCollision(rectangle, tree.getRoot(), v);
					try {
						ok = false;
						print = 0;
						for (i = 0; i < v.getLength(); ++i) {
							if (v.isZero(i) == false) {
								ok = true;
								if (print != 0)
									writer.write(' ');
								writer.write("" + v.getElement(i));
								print = 1;
							}
						}
						if (ok == false)
							writer.write("NIL");
						v.Reinitialize();
						writer.write('\n');
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (figura == 2) {
					x1 = Double.parseDouble(tokens[2]);
					y1 = Double.parseDouble(tokens[3]);
					x2 = Double.parseDouble(tokens[4]);
					y2 = Double.parseDouble(tokens[5]);
					x3 = Double.parseDouble(tokens[6]);
					y3 = Double.parseDouble(tokens[7]);
					Triangle trig = new Triangle(x1, y1, x2, y2, x3, y3);
					trig.CreatePhaserRectangle();
					trig.setID(-1);
					tree.TestCollision(trig, tree.getRoot(), v);
					try {
						ok = false;
						print = 0;
						for (i = 0; i < v.getLength(); ++i) {
							if (v.isZero(i) == false) {
								ok = true;
								if (print != 0)
									writer.write(' ');
								writer.write("" + v.getElement(i));
								print = 1;
							}
						}
						if (ok == false)
							writer.write("NIL");
						v.Reinitialize();
						writer.write('\n');
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (figura == 4) {
					x1 = Double.parseDouble(tokens[2]);
					y1 = Double.parseDouble(tokens[3]);
					x2 = Double.parseDouble(tokens[4]);
					y2 = Double.parseDouble(tokens[5]);
					x3 = Double.parseDouble(tokens[6]);
					y3 = Double.parseDouble(tokens[7]);
					x4 = Double.parseDouble(tokens[8]);
					y4 = Double.parseDouble(tokens[9]);
					Rhombus rb = new Rhombus(x3, y3, x2, y2, x1, y1, x4, y4);
					rb.CreatePhaserRectangle();
					rb.setID(-1);
					tree.TestCollision(rb, tree.getRoot(), v);
					try {
						ok = false;
						print = 0;
						for (i = 0; i < v.getLength(); ++i) {
							if (v.isZero(i) == false) {
								ok = true;
								if (print != 0)
									writer.write(' ');
								writer.write("" + v.getElement(i));
								print = 1;
							}
						}
						if (ok == false)
							writer.write("NIL");
						v.Reinitialize();
						writer.write('\n');
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					R = Double.parseDouble(tokens[2]);
					xcerc = Double.parseDouble(tokens[3]);
					ycerc = Double.parseDouble(tokens[4]);
					Circle circle = new Circle(R, xcerc, ycerc);
					circle.CreatePhaserRectangle();
					circle.setID(-1);
					tree.TestCollision(circle, tree.getRoot(), v);
					try {
						ok = false;
						print = 0;
						for (i = 0; i < v.getLength(); ++i) {
							if (v.isZero(i) == false) {
								ok = true;
								if (print != 0)
									writer.write(' ');
								writer.write("" + v.getElement(i));
								print = 1;
							}
						}
						if (ok == false)
							writer.write("NIL");
						v.Reinitialize();
						writer.write('\n');
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				break;
			default:
				break;
			}
			s = sc.readLine();
		}

		sc.close();
		writer.close();
	}
}