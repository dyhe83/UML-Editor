package mode;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class Mode implements MouseInputListener {
	private static Mode currentMode;

	public static Mode getCurrentMode() {
		if (currentMode == null)
			currentMode = new SelectMode();
		return Mode.currentMode;
	}

	public static void setCurrentMode(Mode mode) {
		Mode.currentMode = mode;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}
}