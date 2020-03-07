package view;

import mode.Mode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class imgButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static final Dimension imgSize = new Dimension(64, 64);
	private static final Dimension btnSize = new Dimension(80, 80);
	private static final Point position = new Point(15, 15);

	private Mode mode;

	public imgButton(int seq, File imgFile, Mode mode) {
		this.mode = mode;
		this.setName("select");
		this.setBackground(Color.WHITE);
		position.y = (btnSize.height + 10) * seq;
		this.setBounds(position.x, position.y, btnSize.width, btnSize.height);
		try {
			Image scaleImage = ImageIO.read(imgFile).getScaledInstance(imgSize.width, imgSize.height,
					Image.SCALE_DEFAULT);
			this.setIcon(new ImageIcon(scaleImage));
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null,
					"Error in ImageButton: Load image failed[" + imgFile.getAbsolutePath() + "]");
			exception.printStackTrace();
		}
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setCurrentMode(this.mode);
		JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Container contentPane = jFrame.getContentPane();
		JPanel leftButtonPanel = ((JPanel) contentPane.getComponent(0));
		for (Component c : leftButtonPanel.getComponents()) {
			c.setBackground(Color.WHITE);
		}
		this.setBackground(Color.GRAY);
		CanvasPanel canvasPanel = (CanvasPanel) contentPane.getComponent(1);
		canvasPanel.setAllShapesSelectStatus(false);
		canvasPanel.repaint();
	}
}