package shape;

import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends Shape {
	private ArrayList<Shape> children;

	public GroupObject(CanvasPanel canvasPanel) {
		this.paintPriority = 1;

		this.children = new ArrayList<>();
		this.children.addAll(canvasPanel.getSelectedShapes());
		canvasPanel.removeSelectedShape();
		this.setSelected(true);

		this.calculateBound();
	}

	public ArrayList<Shape> getChildren() {
		return this.children;
	}

	private void calculateBound() {
		this.x = Integer.MAX_VALUE;
		this.y = Integer.MAX_VALUE;
		this.width = Integer.MIN_VALUE;
		this.height = Integer.MIN_VALUE;
		for (Shape shape : this.children) {
			this.x = Math.min(this.x, shape.x);
			this.y = Math.min(this.y, shape.y);
			this.width = Math.max(this.width, shape.x + shape.width);
			this.height = Math.max(this.height, shape.y + shape.height);
		}
		this.width -= this.x;
		this.height -= this.y;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
		for (Shape shape : this.children) {
			shape.setSelected(selected);
		}
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(new Color(50, 50, 255, 127));
		g.fillRect(this.x, this.y, this.width, this.height);
		for (Shape shape : this.children) {
			shape.repaint(g);
		}
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		for (Shape shape : this.children) {
			shape.move(dx, dy);
		}
	}

}