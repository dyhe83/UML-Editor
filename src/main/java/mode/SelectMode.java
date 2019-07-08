package mode;

import shape.Shape;
import view.CanvasPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectMode extends Mode {

	private Shape pressedShape;
	private Point pressedPoint;

	public SelectMode() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		((CanvasPanel) e.getSource()).setSelected(e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		pressedPoint = e.getPoint();
		pressedShape = null;

		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.setAllShapesSelectStatus(false);
		ArrayList<Shape> shapes = canvasPanel.getShapes(pressedPoint);
		if (!shapes.isEmpty()) {
			pressedShape = shapes.get(0);
			pressedShape.setSelected(true);
		}
		canvasPanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Point releasedPoint = e.getPoint();
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.setAllShapesSelectStatus(false);
		if (pressedShape == null) {
			CanvasPanel.setSelected(pressedPoint, releasedPoint);
		} else {
			int dx = (releasedPoint.x - pressedPoint.x);
			int dy = (releasedPoint.y - pressedPoint.y);
			pressedShape.move(dx, dy);
			pressedShape.setSelected(true);
		}
		canvasPanel.repaint();
	}
}
