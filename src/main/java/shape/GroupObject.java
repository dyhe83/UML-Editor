package shape;

import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends Shape {
	private ArrayList<Shape> childs;

	public GroupObject(CanvasPanel canvasPanel) {
		paintPriority = 1;

		childs = new ArrayList<Shape>();
		childs.addAll(canvasPanel.getSelectedShapes());
		canvasPanel.removeSelectedShape();
		this.setSelected(true);

		this.calculateBound(canvasPanel);
	}

	public ArrayList<Shape> getChilds() {
		return childs;
	}

	private void calculateBound(CanvasPanel canvasPanel) {
		x = Integer.MAX_VALUE;
		y = Integer.MAX_VALUE;
		width = Integer.MIN_VALUE;
		height = Integer.MIN_VALUE;
		for (Shape shape : childs) {
			x = Math.min(x, shape.x);
			y = Math.min(y, shape.y);
			width = Math.max(width, shape.x + shape.width);
			height = Math.max(height, shape.y + shape.height);
		}
		width -= x;
		height -= y;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = true;
		for (Shape shape : childs) {
			shape.setSelected(selected);
		}
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(new Color(50, 50, 255, 127));
		g.fillRect(x, y, width, height);
		for (Shape shape : childs) {
			shape.repaint(g);
		}
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		for (Shape shape : childs) {
			shape.move(dx, dy);
		}
	}

}