package view;

import shape.GroupObject;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = -8315032390183761978L;
	private static ArrayList<Shape> shapeSet;

	public CanvasPanel() {
		shapeSet = new ArrayList<Shape>();
	}

	public static void setSelected(Point pressedPoint, Point releasedPoint) {
		Point minPoint = new Point(Math.min(pressedPoint.x, releasedPoint.x),
				Math.min(pressedPoint.y, releasedPoint.y));
		Point maxPoint = new Point(Math.max(pressedPoint.x, releasedPoint.x),
				Math.max(pressedPoint.y, releasedPoint.y));
		Rectangle rectangle = new Rectangle(minPoint.x, minPoint.y, maxPoint.x - minPoint.x, maxPoint.y - minPoint.y);
		for (Shape shape : shapeSet) {
			if (rectangle.contains(new Rectangle(shape.getPosition(), shape.getSize()))) {
				shape.setSelected(true);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		reSortShapeSet();
		for (Shape shape : shapeSet) {
			shape.repaint(g);
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapeSet;
	}

	public ArrayList<Shape> getShapes(Point point) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for (Shape shape : shapeSet) {
			if (shape.isInside(point)) {
				shapes.add(shape);
			}
		}
		return shapes;
	}

	public void setAllShapesSelectStatus(boolean selected) {
		for (Shape shape : CanvasPanel.shapeSet) {
			shape.setSelected(selected);
		}
	}

	public ArrayList<Shape> getSelectedShapes() {
		ArrayList<Shape> selectedShapeList = new ArrayList<Shape>();
		for (Shape shape : shapeSet) {
			if (shape.getSelected()) {
				selectedShapeList.add(shape);
			}
		}
		return selectedShapeList;
	}

	public void addShape(Shape shape) {
		shapeSet.add(shape);
	}

	public void addShape(Collection<Shape> shapes) {
		shapeSet.addAll(shapes);
	}

	public void removeShape(Shape shape) {
		shapeSet.remove(shape);
	}

	public void removeShape(Collection<Shape> shapes) {
		shapeSet.removeAll(shapes);
	}

	public void removeSelectedShape() {
		shapeSet.removeAll(getSelectedShapes());
	}

	public void setSelected(Point point) {
		setAllShapesSelectStatus(false);
		for (Shape shape : shapeSet) {
			boolean insided = shape.isInside(point);
			if (insided) {
				shape.setSelected(true);
				break;
			}
		}
	}

	private void reSortShapeSet() {
		shapeSet.sort(new Comparator<Shape>() {
			@Override
			public int compare(Shape shape1, Shape shape2) {
				int p1 = shape1.getPaintPriority();
				int p2 = shape2.getPaintPriority();
				if (p1 < p2) {
					return -1;
				} else if (p1 > p2) {
					return 1;
				} else {
					return 0;
				}
			}
		});
	}

	public void removeGroup() {
		ArrayList<Shape> selectedShapes = this.getSelectedShapes();
		if (selectedShapes.size() == 1) {
			Shape shape = selectedShapes.get(0);
			if (shape instanceof GroupObject) {
				GroupObject group = ((GroupObject) shape);
				addShape(group.getChilds());
				shapeSet.remove(group);
			}
		}
	}

}