package view;

import shape.UMLGroup;
import shape.UMLShape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DongJMenuBar extends JMenuBar {
	private List<JMenu> menuList = new ArrayList<>();
	private List<String> menuNameList = new ArrayList<>();

	public void addNewMenu(String menuName) {
		this.menuNameList.add(menuName);
		this.menuList.add(new JMenu(menuName));
	}

	public void addNewMenuItem(String menuName, String[] menuItems) {
		int index = this.menuNameList.indexOf(menuName);
		if (index < 0) {
			System.err.println("Error in ToolBar: " + menuName + " not found.");
		}
		for (String itemName : menuItems) {
			JMenu menu = this.menuList.get(index);
			Action action = new AbstractAction(itemName) {
				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem menuItem = ((JMenuItem) e.getSource());
					JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(menu);
					CanvasPanel canvasPanel = (CanvasPanel) jFrame.getContentPane().getComponent(1);
					switch (menuItem.getName()) {
						case "Edit Name":
							List<UMLShape> selectedUMLShape = canvasPanel.getSelectedShapes();
							if (selectedUMLShape.size() == 1) {
								String objName = JOptionPane.showInputDialog("");
								if (objName != null) {
									objName = String.format("%16s", objName);
									selectedUMLShape.get(0).setName(objName);
								}
							}
							break;
						case "Group":
							if (!canvasPanel.getSelectedShapes().isEmpty()) {
								canvasPanel.addShape(new UMLGroup(canvasPanel));
							}
							break;
						case "UnGroup":
							if (canvasPanel.getSelectedShapes().size() == 1) {
								canvasPanel.removeGroup();
							}
							break;
						default:
							System.err.println("exception: DongJMenuBar unknown menu bar");
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