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
		((CanvasPanel) e.getSource()).setShapesSelected(e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		this.pressedPoint = e.getPoint();
		this.pressedShape = null;

		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.setAllShapesSelectStatus(false);
		ArrayList<Shape> shapes = canvasPanel.getShapesAtPoint(this.pressedPoint);
		if (!shapes.isEmpty()) {
			this.pressedShape = shapes.get(0);
			this.pressedShape.setSelected(true);
		}
		canvasPanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Point releasedPoint = e.getPoint();
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.setAllShapesSelectStatus(false);
		if (this.pressedShape == null) {
			CanvasPanel.setShapesSelected(this.pressedPoint, releasedPoint);
		} else {
			int dx = (releasedPoint.x - this.pressedPoint.x);
			int dy = (releasedPoint.y - this.pressedPoint.y);
			this.pressedShape.move(dx, dy);
			this.pressedShape.setSelected(true);
		}
		canvasPanel.repaint();
	}
}
