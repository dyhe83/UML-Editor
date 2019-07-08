package shape;

import port.Port;

import java.awt.*;

public class Shape {
	protected String name = "";
	protected int x = 0, y = 0, width = 100, height = 60;
	protected int paintPriority = Integer.MAX_VALUE;
	protected boolean selected = false;

	public Shape() {

	}

	public Shape(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty())
			return;
		this.name = name;
	}

	public int getPaintPriority() {
		return paintPriority;
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

	public boolean getSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Port getClosestPort(Point point) {
		return null;
	}

	public boolean isInside(Point mousePosition) {
		Rectangle rectangle = new Rectangle(x, y, width, height);
		return rectangle.contains(mousePosition.x, mousePosition.y);
	}

	public void paint(Graphics g) {
		paintShape(g);
		if (this.selected) {
			paintPort(g);
		}
		paintName(g);
	}

	public void repaint(Graphics g) {
		paint(g);
	}

	protected void paintShape(Graphics g) {

	}

	protected void paintPort(Graphics g) {

	}

	protected void paintName(Graphics g) {
		g.drawString(name, x, y + height / 2);
	}

}