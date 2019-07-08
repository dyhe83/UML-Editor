package view;

import shape.GroupObject;
import shape.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Dong_JMenuBar extends JMenuBar {
	private static final long serialVersionUID = 332118958862894912L;
	private ArrayList<JMenu> menuList;
	private ArrayList<String> menuNameList;

	public Dong_JMenuBar() {
		menuList = new ArrayList<JMenu>();
		menuNameList = new ArrayList<String>();
		menuList.clear();
		menuNameList.clear();
	}

	public void addNewMenu(String menuName) {
		menuNameList.add(menuName);
		menuList.add(new JMenu(menuName));
	}

	public void addNewMenuItem(String menuName, String[] menuItems) {
		int index = menuNameList.indexOf(menuName);
		if (index < 0) {
			System.out.println("Error in ToolBar: " + menuName + " not found.");
		}
		for (String itemName : menuItems) {
			JMenu menu = menuList.get(index);
			Action action = new AbstractAction(itemName) {
				private static final long serialVersionUID = 8901907119923615116L;

				public void actionPerformed(ActionEvent e) {
					JMenuItem menuItem = ((JMenuItem) e.getSource());
					JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(menu);
					CanvasPanel canvasPanel = (CanvasPanel) jFrame.getContentPane().getComponent(1);
					if (menuItem.getName().equals("Edit Name")) {
						ArrayList<Shape> selectedShape = canvasPanel.getSelectedShapes();
						if (selectedShape.size() == 1) {
							String objName = JOptionPane.showInputDialog("");
							if (objName != null) {
								objName = "       " + objName;
								selectedShape.get(0).setName(objName);
							}
						}
					} else if (menuItem.getName().equals("Group")) {
						if (canvasPanel.getSelectedShapes().size() > 0) {
							canvasPanel.addShape(new GroupObject(canvasPanel));
						}
					} else if (menuItem.getName().equals("UnGroup")) {
						if (canvasPanel.getSelectedShapes().size() == 1) {
							canvasPanel.removeGroup();
						}
					}
					canvasPanel.repaint();
				}
			};

			JMenuItem item = menu.add(action);
			item.setName(itemName);
			menu.add(item);
			// menu.addMouseListener(this);
			menuList.set(index, menu);
		}
	}

	public void bind() {
		for (JMenu menu : menuList) {
			add(menu);
		}
	}

}
