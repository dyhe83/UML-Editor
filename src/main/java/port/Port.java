package port;

import shape.UMLBasicObject;
import shape.UMLShape;

import java.awt.*;

public abstract class Port extends UMLShape {
	public Port() {
		this.setWidth(10);
		this.setHeight(10);
	}

	public Point getBorderPoint() {
		return null;
	}

	public Point getMidPoint() {
		int x = this.getX() + this.getWidth() / 2;
		int y = this.getY() + this.getHeight() / 2;
		return new Point(x, y);
	}

	public abstract void calibrateBound(UMLBasicObject obj);

	@Override
	public void paint(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}