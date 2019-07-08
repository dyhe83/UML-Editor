package view;

import mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class EditorForm extends JFrame {
	private static final long serialVersionUID = 1L;
	public JPanel leftButtonPanel;
	public Dong_JMenuBar menuBar;
	public CanvasPanel canvasPanel;

	public EditorForm() {
		this.getContentPane().setLayout(null);
		initMenuBar();
		initLeftButtonPanel();
		initPanel();
		initFrame();
		((imgButton) leftButtonPanel.getComponent(0)).actionPerformed(null);
	}

	private void initFrame() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		setTitle("Simple UML Editor");
		setBounds(width / 10, height / 10, 960, 640);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initLeftButtonPanel() {
		leftButtonPanel = new JPanel();
		leftButtonPanel.setLayout(null);
		leftButtonPanel.setBounds(0, 0, 120, 640);
		this.getContentPane().add(leftButtonPanel);
		leftButtonPanel.add(new imgButton(0, new File(".\\img\\select.png"), new SelectMode()));
		leftButtonPanel.add(new imgButton(1, new File(".\\img\\aline.png"), new AssociationMode()));
		leftButtonPanel.add(new imgButton(2, new File(".\\img\\gline.png"), new GeneralizationMode()));
		leftButtonPanel.add(new imgButton(3, new File(".\\img\\cline.png"), new CompositionMode()));
		leftButtonPanel.add(new imgButton(4, new File(".\\img\\class.png"), new BasicClassMode()));
		leftButtonPanel.add(new imgButton(5, new File(".\\img\\ucase.png"), new UseCaseMode()));
	}

	private void initMenuBar() {
		menuBar = new Dong_JMenuBar();
		menuBar.addNewMenu("File");
		menuBar.addNewMenuItem("File", new String[]{"New", "Save", "Open"});
		menuBar.addNewMenu("Edit");
		menuBar.addNewMenuItem("Edit", new String[]{"Edit Name", "Group", "UnGroup"});
		menuBar.setVisible(true);
		menuBar.bind();
		setJMenuBar(menuBar);
	}

	private void initPanel() {
		canvasPanel = new CanvasPanel();
		canvasPanel.setLayout(null);
		canvasPanel.setBounds(150, 15, 750, 540);
		canvasPanel.setBackground(Color.WHITE);
		canvasPanel.setVisible(true);
		canvasPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mode.getCurrentMode().mouseClicked(e);
				CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
				canvasPanel.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Mode.getCurrentMode().mousePressed(e);
				CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
				canvasPanel.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Mode.getCurrentMode().mouseReleased(e);
				CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
				canvasPanel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Mode.getCurrentMode().mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Mode.getCurrentMode().mouseExited(e);
			}

			// @Override
			// public void mouseDragged(MouseEvent arg0) {
			// Mode.getCurrentMode().mouseDragged(arg0);
			// }
			//
			// @Override
			// public void mouseMoved(MouseEvent arg0) {
			// Mode.getCurrentMode().mouseMoved(arg0);
			// }
		});
		this.getContentPane().add(canvasPanel);
	}

}