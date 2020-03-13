package shape;

import lombok.Getter;
import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends BasicObject {
	@Getter
	private ArrayList<Shape> children = new ArrayList<>();

	public GroupObject(CanvasPanel canvasPanel) {
		this.setPaintPriority(1);

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
			width = Math.max(width, shape.getX() + shape.getWidth());
			height = Math.max(height, shape.getY() + shape.getHeight());
		}

		this.setX(x);
		this.setY(y);
		this.setWidth(width - x);
		this.setHeight(height - y);
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
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		for (Shape shape : this.children) {
			shape.repaint(g);
		}
	}

	@Override
	protected void paintPort(Graphics g) {
		// GroupObject shouldn't display 'port'
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		for (Shape shape : this.children) {
			shape.move(dx, dy);
		}
	}
}