package shape;

import lombok.Getter;
import lombok.Setter;
import port.Port;

import java.awt.*;

@Getter
@Setter
public class Shape {
	private int paintPriority = Integer.MAX_VALUE;

	private Point position = new Point();
	private Dimension size = new Dimension(100, 60);
	private String name = "";
	private boolean selected = false;

	public Shape() {

	}

	public Shape(Point position) {
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

	public Port getClosestPort(Point point) {
		return null;
	}

	public boolean isInside(Point mousePosition) {
		Rectangle rectangle = new Rectangle(this.getPosition(), this.getSize());
		return rectangle.contains(mousePosition.x, mousePosition.y);
	}

	public void paint(Graphics g) {
		this.paintShape(g);
		if (this.selected) {
			this.paintPort(g);
		}
		this.paintName(g);
	}

	public void repaint(Graphics g) {
		this.paint(g);
	}

	protected void paintShape(Graphics g) {

	}

	protected void paintPort(Graphics g) {

	}

	protected void paintName(Graphics g) {
		g.drawString(this.name, this.getX(), this.getY() + this.getHeight() / 2);
	}
}