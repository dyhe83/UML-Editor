package mode;

import lombok.Getter;
import lombok.Setter;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Mode implements MouseInputListener, Serializable {
	@Getter
	@Setter
	private static Mode currentMode;

	@Override
	public void mouseClicked(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// default implementation ignored
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// default implementation ignored
	}
}