package view;

import shape.GroupObject;
import shape.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Dong_JMenuBar extends JMenuBar {
	private static final long serialVersionUID = 332118958862894912L;
	private ArrayList<JMenu> menuList = new ArrayList<>();
	private ArrayList<String> menuNameList = new ArrayList<>();

	public void addNewMenu(String menuName) {
		this.menuNameList.add(menuName);
		this.menuList.add(new JMenu(menuName));
	}

	public void addNewMenuItem(String menuName, String[] menuItems) {
		int index = this.menuNameList.indexOf(menuName);
		if (index < 0) {
			System.out.println("Error in ToolBar: " + menuName + " not found.");
		}
		for (String itemName : menuItems) {
			JMenu menu = this.menuList.get(index);
			Action action = new AbstractAction(itemName) {
				private static final long serialVersionUID = 8901907119923615116L;

				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem menuItem = ((JMenuItem) e.getSource());
					JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(menu);
					CanvasPanel canvasPanel = (CanvasPanel) jFrame.getContentPane().getComponent(1);
					switch (menuItem.getName()) {
						case "Edit Name":
							ArrayList<Shape> selectedShape = canvasPanel.getSelectedShapes();
							if (selectedShape.size() == 1) {
								String objName = JOptionPane.showInputDialog("");
								if (objName != null) {
									objName = String.format("%16s", objName);
									selectedShape.get(0).setName(objName);
								}
							}
							break;
						case "Group":
							if (canvasPanel.getSelectedShapes().size() > 0) {
								canvasPanel.addShape(new GroupObject(canvasPanel));
							}
							break;
						case "UnGroup":
							if (canvasPanel.getSelectedShapes().size() == 1) {
								canvasPanel.removeGroup();
							}
							break;
					}
					canvasPanel.repaint();
				}
			};

			JMenuItem item = menu.add(action);
			item.setName(itemName);
			menu.add(item);

			this.menuList.set(index, menu);
		}
	}

	public void bind() {
		for (JMenu menu : this.menuList) {
			this.add(menu);
		}
	}
}