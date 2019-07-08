package mode;

import port.Port;
import shape.CompositionLine;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class CompositionMode extends ConnectionLineMode {

	public CompositionMode() {
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
		Port releasedPort = super.getPort((CanvasPanel) e.getSource(), e.getPoint());
		if (pressedPort != null && releasedPort != null) {
			CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
			canvasPanel.addShape(new CompositionLine(pressedPort, releasedPort));
		}
	}
}
