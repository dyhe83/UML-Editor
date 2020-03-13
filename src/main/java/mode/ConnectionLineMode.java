package mode;

import port.Port;
import shape.UMLBasicObject;
import shape.UMLShape;
import view.CanvasPanel;

import java.awt.*;
import java.util.List;

public class ConnectionLineMode extends Mode {
	protected Port pressedPort = null;

	protected Port getPort(CanvasPanel canvasPanel, Point point) {
		List<UMLShape> umlShapes = canvasPanel.getShapesAtPoint(point);
		if (umlShapes.isEmpty()) {
			return null;
		}

		if (umlShapes.get(0) instanceof UMLBasicObject) {
			UMLBasicObject basicObject = (UMLBasicObject) umlShapes.get(0);
			return basicObject.getClosestPort(point);
		}

		return null;
	}
}