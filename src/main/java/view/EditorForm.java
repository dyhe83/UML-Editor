package view;

import mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Properties;

public class EditorForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String configFileName = "src/main/resources/config.properties";

	public CanvasPanel canvasPanel;
	private JPanel leftButtonPanel;

	public EditorForm() {
		this.getContentPane().setLayout(null);
		this.initMenuBar();
		this.initLeftButtonPanel();
		this.initPanel();
		this.initFrame();
		((imgButton) this.leftButtonPanel.getComponent(0)).actionPerformed(null);
	}

	private void initFrame() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		this.setTitle("Simple UML Editor");
		this.setBounds(width / 10, height / 10, 960, 640);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initLeftButtonPanel() {
		this.leftButtonPanel = new JPanel();
		this.leftButtonPanel.setLayout(null);
		this.leftButtonPanel.setBounds(0, 0, 120, 640);
		this.getContentPane().add(this.leftButtonPanel);

		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(configFileName);
			properties.load(inputStream);
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("config file not found:" + configFileName);
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		this.leftButtonPanel.add(new imgButton(0, new File(properties.getProperty("img.select")), new SelectMode()));
		this.leftButtonPanel.add(new imgButton(1, new File(properties.getProperty("img.association")), new AssociationMode()));
		this.leftButtonPanel.add(new imgButton(2, new File(properties.getProperty("img.generalization")), new GeneralizationMode()));
		this.leftButtonPanel.add(new imgButton(3, new File(properties.getProperty("img.composition")), new CompositionMode()));
		this.leftButtonPanel.add(new imgButton(4, new File(properties.getProperty("img.basic_class")), new BasicClassMode()));
		this.leftButtonPanel.add(new imgButton(5, new File(properties.getProperty("img.use_case")), new UseCaseMode()));
	}

	private void initMenuBar() {
		Dong_JMenuBar menuBar = new Dong_JMenuBar();
		menuBar.addNewMenu("File");
		menuBar.addNewMenuItem("File", new String[]{"New", "Save", "Open"});
		menuBar.addNewMenu("Edit");
		menuBar.addNewMenuItem("Edit", new String[]{"Edit Name", "Group", "UnGroup"});
		menuBar.setVisible(true);
		menuBar.bind();
		this.setJMenuBar(menuBar);
	}

	private void initPanel() {
		this.canvasPanel = new CanvasPanel();
		this.canvasPanel.setLayout(null);
		this.canvasPanel.setBounds(150, 15, 750, 540);
		this.canvasPanel.setBackground(Color.WHITE);
		this.canvasPanel.setVisible(true);
		this.canvasPanel.addMouseListener(new MouseListener() {
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

		});
		this.getContentPane().add(this.canvasPanel);
	}

}