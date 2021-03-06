package shape;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

@Getter
@Setter
public abstract class UMLShape implements Serializable {
	private int paintPriority = Integer.MAX_VALUE;

	private Point position = new Point();
	private Dimension size = new Dimension(100, 60);
	private String name = "";
	private boolean selected = false;

	public UMLShape() {

	}

	public UMLShape(Point position) {
		this.setPosition(position);
	}

	public void move(int dx, int dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	public int getX() {
		return this.getPosition().x;
	}

	public void setX(int x) {
		this.getPosition().x = x;
	}

	public int getY() {
		return this.getPosition().y;
	}

	public void setY(int y) {
		this.getPosition().y = y;
	}

	public int getWidth() {
		return this.getSize().width;
	}

	public void setWidth(int width) {
		this.getSize().width = width;
	}

	public int getHeight() {
		return this.getSize().height;
	}

	public void setHeight(int height) {
		this.getSize().height = height;
	}

	public boolean isInside(Point mousePosition) {
		Rectangle rectangle = new Rectangle(this.getPosition(), this.getSize());
		return rectangle.contains(mousePosition.x, mousePosition.y);
	}

	public abstract void paint(Graphics g);

	public void repaint(Graphics g) {
		this.paint(g);
	}
}