package mode;

import port.Port;
import shape.AssociationLine;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class AssociationMode extends ConnectionLineMode {

	public AssociationMode() {
		pressedPort = null;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		pressedPort = super.getPort(canvasPanel, e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		Port releasedPort = super.getPort(canvasPanel, e.getPoint());
		if (pressedPort != null && releasedPort != null) {
			canvasPanel.addShape(new AssociationLine(pressedPort, releasedPort));
		}
	}
}
