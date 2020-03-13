package mode;

import shape.UMLBasicClass;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class UMLBasicClassMode extends Mode {
	@Override
	public void mouseClicked(MouseEvent e) {
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.addShape(new UMLBasicClass(e.getPoint()));
	}
}