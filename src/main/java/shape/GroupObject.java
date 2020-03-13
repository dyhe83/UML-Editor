package shape;

import lombok.Getter;
import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends Shape {
	@Getter
	private ArrayList<Shape> children = new ArrayList<>();

	public GroupObject(CanvasPanel canvasPanel) {
		this.paintPriority = 1;

		this.children.addAll(canvasPanel.getSelectedShapes());
		canvasPanel.removeSelectedShape();
		this.setSelected(true);

		this.calculateBound();
	}

	private void calculateBound() {
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;
		int width = Integer.MIN_VALUE;
		int height = Integer.MIN_VALUE;

		for (Shape shape : this.children) {
			x = Math.min(x, shape.getX());
			y = Math.min(y, shape.getY());
			width = Math.max(width, shape.getX() + shape.width);
			height = Math.max(height, shape.getY() + shape.height);
		}

		this.setX(x);
		this.setY(y);
		this.width = width - x;
		this.height = height - y;
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);

		for (Shape shape : this.children) {
			shape.setSelected(selected);
		}
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(new Color(50, 50, 255, 127));
		g.fillRect(this.getX(), this.getY(), this.width, this.height);
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