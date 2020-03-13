package port;

import shape.BasicObject;
import shape.Shape;

import java.awt.*;

public class Port extends Shape {

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

	public void calibrateBound(BasicObject obj) {

	}
}