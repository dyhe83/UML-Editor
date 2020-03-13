package mode;

import shape.UMLShape;
import view.CanvasPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class SelectMode extends Mode {

	private UMLShape pressedUMLShape;
	private Point pressedPoint;

	@Override
	public void mouseClicked(MouseEvent e) {
		((CanvasPanel) e.getSource()).setShapesSelected(e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pressedPoint = e.getPoint();
		this.pressedUMLShape = null;

		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.setAllShapesSelectStatus(false);
		List<UMLShape> umlShapes = canvasPanel.getShapesAtPoint(this.pressedPoint);
		if (!umlShapes.isEmpty()) {
			this.pressedUMLShape = umlShapes.get(0);
			this.pressedUMLShape.setSelected(true);
		}
		canvasPanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point releasedPoint = e.getPoint();
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.setAllShapesSelectStatus(false);
		if (this.pressedUMLShape == null) {
			CanvasPanel.setShapesSelected(this.pressedPoint, releasedPoint);
		} else {
			int dx = (releasedPoint.x - this.pressedPoint.x);
			int dy = (releasedPoint.y - this.pressedPoint.y);
			this.pressedUMLShape.move(dx, dy);
			this.pressedUMLShape.setSelected(true);
		}
		canvasPanel.repaint();
	}
}