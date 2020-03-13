package view;

import lombok.Getter;
import shape.UMLGroup;
import shape.UMLShape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class CanvasPanel extends JPanel {
	private static List<UMLShape> umlShapes = new ArrayList<>();

	public static void setShapesSelected(Point pressedPoint, Point releasedPoint) {
		Point minPoint = new Point(Math.min(pressedPoint.x, releasedPoint.x),
				Math.min(pressedPoint.y, releasedPoint.y));
		Point maxPoint = new Point(Math.max(pressedPoint.x, releasedPoint.x),
				Math.max(pressedPoint.y, releasedPoint.y));
		Rectangle rectangle = new Rectangle(minPoint.x, minPoint.y, maxPoint.x - minPoint.x, maxPoint.y - minPoint.y);
		for (UMLShape UMLShape : umlShapes) {
			UMLShape.setSelected(false);
			if (rectangle.contains(new Rectangle(UMLShape.getPosition(), UMLShape.getSize()))) {
				UMLShape.setSelected(true);
			}
		}
	}

	public void setShapesSelected(Point point) {
		this.setAllShapesSelectStatus(false);
		for (UMLShape umlShape : umlShapes) {
			if (umlShape.isInside(point)) {
				umlShape.setSelected(true);
				break;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.reSortShapeList();
		for (UMLShape umlShape : umlShapes) {
			umlShape.repaint(g);
		}
	}

	public List<UMLShape> getShapesAtPoint(Point point) {
		List<UMLShape> umlShapes = new ArrayList<>();
		for (UMLShape umlShape : CanvasPanel.umlShapes) {
			if (umlShape.isInside(point)) {
				umlShapes.add(umlShape);
			}
		}
		return umlShapes;
	}

	public void setAllShapesSelectStatus(boolean selected) {
		for (UMLShape UMLShape : CanvasPanel.umlShapes) {
			UMLShape.setSelected(selected);
		}
	}

	public List<UMLShape> getSelectedShapes() {
		List<UMLShape> selectedUMLShapeList = new ArrayList<>();
		for (UMLShape umlShape : umlShapes) {
			if (umlShape.isSelected()) {
				selectedUMLShapeList.add(umlShape);
			}
		}
		return selectedUMLShapeList;
	}

	public void addShape(UMLShape umlShape) {
		umlShapes.add(umlShape);
	}

	public void addShape(Collection<UMLShape> umlShapes) {
		CanvasPanel.umlShapes.addAll(umlShapes);
	}

	public void removeShape(UMLShape umlShape) {
		umlShapes.remove(umlShape);
	}

	public void removeShape(Collection<UMLShape> umlShapes) {
		CanvasPanel.umlShapes.removeAll(umlShapes);
	}

	public void removeSelectedShape() {
		umlShapes.removeAll(this.getSelectedShapes());
	}

	private void reSortShapeList() {
		umlShapes.sort((shape1, shape2) -> {
			int p1 = shape1.getPaintPriority();
			int p2 = shape2.getPaintPriority();
			return Integer.compare(p1, p2);
		});
	}

	public void removeGroup() {
		List<UMLShape> selectedUMLShapes = this.getSelectedShapes();
		if (selectedUMLShapes.size() == 1) {
			UMLShape umlShape = selectedUMLShapes.get(0);
			if (umlShape instanceof UMLGroup) {
				UMLGroup group = ((UMLGroup) umlShape);
				this.addShape(group.getChildren());
				umlShapes.remove(group);
			}
		}
	}
}