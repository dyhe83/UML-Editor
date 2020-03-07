package view;

import lombok.Getter;
import shape.GroupObject;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = -8315032390183761978L;
	private static ArrayList<Shape> shapeList;

	public CanvasPanel() {
		shapeList = new ArrayList<>();
	}

	public void setShapesSelected(Point point) {
		this.setAllShapesSelectStatus(false);
		for (Shape shape : shapeList) {
			if (shape.isInside(point)) {
				shape.setSelected(true);
				break;
			}
		}
	}

	public static void setShapesSelected(Point pressedPoint, Point releasedPoint) {
		Point minPoint = new Point(Math.min(pressedPoint.x, releasedPoint.x),
				Math.min(pressedPoint.y, releasedPoint.y));
		Point maxPoint = new Point(Math.max(pressedPoint.x, releasedPoint.x),
				Math.max(pressedPoint.y, releasedPoint.y));
		Rectangle rectangle = new Rectangle(minPoint.x, minPoint.y, maxPoint.x - minPoint.x, maxPoint.y - minPoint.y);
		for (Shape shape : shapeList) {
			shape.setSelected(false);
			if (rectangle.contains(new Rectangle(shape.getPosition(), shape.getSize()))) {
				shape.setSelected(true);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.reSortShapeList();
		for (Shape shape : shapeList) {
			shape.repaint(g);
		}
	}

	public ArrayList<Shape> getShapesAtPoint(Point point) {
		ArrayList<Shape> shapes = new ArrayList<>();
		for (Shape shape : shapeList) {
			if (shape.isInside(point)) {
				shapes.add(shape);
			}
		}
		return shapes;
	}

	public void setAllShapesSelectStatus(boolean selected) {
		for (Shape shape : CanvasPanel.shapeList) {
			shape.setSelected(selected);
		}
	}

	public ArrayList<Shape> getSelectedShapes() {
		ArrayList<Shape> selectedShapeList = new ArrayList<>();
		for (Shape shape : shapeList) {
			if (shape.isSelected()) {
				selectedShapeList.add(shape);
			}
		}
		return selectedShapeList;
	}

	public void addShape(Shape shape) {
		shapeList.add(shape);
	}

	public void addShape(Collection<Shape> shapes) {
		shapeList.addAll(shapes);
	}

	public void removeShape(Shape shape) {
		shapeList.remove(shape);
	}

	public void removeShape(Collection<Shape> shapes) {
		shapeList.removeAll(shapes);
	}

	public void removeSelectedShape() {
		shapeList.removeAll(this.getSelectedShapes());
	}

	private void reSortShapeList() {
		shapeList.sort((shape1, shape2) -> {
			int p1 = shape1.getPaintPriority();
			int p2 = shape2.getPaintPriority();
			return Integer.compare(p1, p2);
		});
	}

	public void removeGroup() {
		ArrayList<Shape> selectedShapes = this.getSelectedShapes();
		if (selectedShapes.size() == 1) {
			Shape shape = selectedShapes.get(0);
			if (shape instanceof GroupObject) {
				GroupObject group = ((GroupObject) shape);
				this.addShape(group.getChildren());
				shapeList.remove(group);
			}
		}
	}
}