package mode;

import port.Port;
import shape.Shape;
import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class ConnectionLineMode extends Mode {
	protected Port pressedPort = null;

	protected Port getPort(CanvasPanel canvasPanel, Point point) {
		ArrayList<Shape> shapes = canvasPanel.getShapesAtPoint(point);
		if (shapes.isEmpty()) {
			return null;
		}
		return shapes.get(0).getClosestPort(point);
	}
}