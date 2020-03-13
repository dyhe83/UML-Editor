package shape;

import lombok.Getter;
import lombok.Setter;
import port.Port;

import java.awt.*;

@Getter
public class Shape {
	public int paintPriority = Integer.MAX_VALUE;

	@Setter
	protected Point position = new Point();

	protected int width = 100, height = 60;

	@Setter
	private boolean selected = false;

	@Setter
	private String name = "";

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

	public Dimension getSize() {
		return (new Dimension(this.width, this.height));
	}

	public Port getClosestPort(Point point) {
		return null;
	}

	public boolean isInside(Point mousePosition) {
		Rectangle rectangle = new Rectangle(this.getX(), this.getY(), this.width, this.height);
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
		g.drawString(this.name, this.getX(), this.getY() + this.height / 2);
	}
}