package mode;

import port.Port;
import shape.CompositionLine;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class CompositionMode extends ConnectionLineMode {

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		this.pressedPort = super.getPort(canvasPanel, e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Port releasedPort = super.getPort((CanvasPanel) e.getSource(), e.getPoint());
		if (this.pressedPort != null && releasedPort != null) {
			CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
			canvasPanel.addShape(new CompositionLine(this.pressedPort, releasedPort));
		}
	}
}
