package shape;

import lombok.Getter;
import view.CanvasPanel;

import java.awt.*;
import java.util.ArrayList;

public class UMLGroup extends UMLBasicObject {
	@Getter
	private ArrayList<UMLShape> children = new ArrayList<>();

	public UMLGroup(CanvasPanel canvasPanel) {
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

		for (UMLShape UMLShape : this.children) {
			x = Math.min(x, UMLShape.getX());
			y = Math.min(y, UMLShape.getY());
			width = Math.max(width, UMLShape.getX() + UMLShape.getWidth());
			height = Math.max(height, UMLShape.getY() + UMLShape.getHeight());
		}

		this.setX(x);
		this.setY(y);
		this.setWidth(width - x);
		this.setHeight(height - y);
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);

		for (UMLShape UMLShape : this.children) {
			UMLShape.setSelected(selected);
		}
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(new Color(50, 50, 255, 127));
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		for (UMLShape UMLShape : this.children) {
			UMLShape.repaint(g);
		}
	}

	@Override
	protected void paintPort(Graphics g) {
		// GroupObject shouldn't display 'port'
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		for (UMLShape UMLShape : this.children) {
			UMLShape.move(dx, dy);
		}
	}
}