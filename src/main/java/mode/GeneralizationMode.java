package mode;

import port.Port;
import shape.GeneralizationLine;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class GeneralizationMode extends ConnectionLineMode {

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		this.pressedPort = super.getPort(canvasPanel, e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);

		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		Port releasedPort = super.getPort(canvasPanel, e.getPoint());
		if (this.pressedPort != null && releasedPort != null) {
			canvasPanel.addShape(new GeneralizationLine(this.pressedPort, releasedPort));
		}
	}
}
