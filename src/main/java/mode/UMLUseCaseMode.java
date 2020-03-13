package mode;

import shape.UMLUseCase;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class UMLUseCaseMode extends Mode {
	@Override
	public void mouseClicked(MouseEvent e) {
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.addShape(new UMLUseCase(e.getPoint()));
	}
}