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

		leftButtonPanel.add(new imgButton(0, new File(properties.getProperty("img.select")), new SelectMode()));
		leftButtonPanel.add(new imgButton(1, new File(properties.getProperty("img.association")), new AssociationMode()));
		leftButtonPanel.add(new imgButton(2, new File(properties.getProperty("img.generalization")), new GeneralizationMode()));
		leftButtonPanel.add(new imgButton(3, new File(properties.getProperty("img.composition")), new CompositionMode()));
		leftButtonPanel.add(new imgButton(4, new File(properties.getProperty("img.basic_class")), new BasicClassMode()));
		leftButtonPanel.add(new imgButton(5, new File(properties.getProperty("img.use_case")), new UseCaseMode()));
	}

	private void initMenuBar() {
		Dong_JMenuBar menuBar = new Dong_JMenuBar();
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

		});
		this.getContentPane().add(canvasPanel);
	}

}