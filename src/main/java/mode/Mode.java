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
		// System.out.println(currentMode.getClass().toString() + "clicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println(currentMode.getClass().toString() + "enter");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println(currentMode.getClass().toString() + "exit");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println(currentMode.getClass().toString() + "pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println(currentMode.getClass().toString() + "release");
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// System.out.println(currentMode.getClass().toString() + "drag");
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// System.out.println(currentMode.getClass().toString() + "move");
	}
}