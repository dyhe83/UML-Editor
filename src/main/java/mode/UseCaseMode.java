package mode;

import shape.UseCase;
import view.CanvasPanel;

import java.awt.event.MouseEvent;

public class UseCaseMode extends BasicObjectMode {
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		canvasPanel.addShape(new UseCase(e.getPoint()));
	}
}