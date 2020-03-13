package mode;

import port.Port;
import shape.AssociationLine;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class AssociationMode extends ConnectionLineMode {

	@Override
	public void mousePressed(MouseEvent e) {
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		this.pressedPort = this.getPort(canvasPanel, e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		Port releasedPort = this.getPort(canvasPanel, e.getPoint());
		if (this.pressedPort != null && releasedPort != null) {
			canvasPanel.addShape(new AssociationLine(this.pressedPort, releasedPort));
		}
	}
}