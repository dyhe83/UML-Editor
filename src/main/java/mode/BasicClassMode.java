package mode;

import shape.BasicClass;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class BasicClassMode extends BasicObjectMode {
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.addShape(new BasicClass(e.getPoint()));
	}
}
