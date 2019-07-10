package shape;

import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends Shape {
	private ArrayList<Shape> children;

	public GroupObject(CanvasPanel canvasPanel) {
		paintPriority = 1;

		children = new ArrayList<>();
		children.addAll(canvasPanel.getSelectedShapes());
		canvasPanel.removeSelectedShape();
		this.setSelected(true);

		this.calculateBound();
	}

	public ArrayList<Shape> getChildren() {
		return children;
	}

	private void calculateBound() {
		x = Integer.MAX_VALUE;
		y = Integer.MAX_VALUE;
		width = Integer.MIN_VALUE;
		height = Integer.MIN_VALUE;
		for (Shape shape : children) {
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
		this.selected = selected;
		for (Shape shape : children) {
			shape.setSelected(selected);
		}
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(new Color(50, 50, 255, 127));
		g.fillRect(x, y, width, height);
		for (Shape shape : children) {
			shape.repaint(g);
		}
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		for (Shape shape : children) {
			shape.move(dx, dy);
		}
	}

}