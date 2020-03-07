package shape;

import lombok.Getter;
import lombok.Setter;
import port.Port;

import java.awt.*;

@Getter
public class Shape {
	public int paintPriority = Integer.MAX_VALUE;
	// TODO: should store position as Point type
	protected int x = 0, y = 0, width = 100, height = 60;
	@Setter
	private boolean selected = false;

	@Setter
	private String name = "";

	public Shape() {

	}

	public Shape(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public Point getPosition() {
		return new Point(this.x, this.y);
	}

	public Dimension getSize() {
		return (new Dimension(this.width, this.height));
	}

	public Port getClosestPort(Point point) {
		return null;
	}

	public boolean isInside(Point mousePosition) {
		Rectangle rectangle = new Rectangle(this.x, this.y, this.width, this.height);
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
		g.drawString(this.name, this.x, this.y + this.height / 2);
	}
}