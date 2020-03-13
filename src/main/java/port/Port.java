package port;

import shape.BasicObject;
import shape.Shape;

import java.awt.*;

public class Port extends Shape {

	public Port() {
		this.height = 10;
		this.width = 10;
	}

	public Point getBorderPoint() {
		return null;
	}

	public Point getMidPoint() {
		int x = this.getX() + this.width / 2;
		int y = this.getY() + this.height / 2;
		return new Point(x, y);
	}

	public void calibrateBound(BasicObject obj) {

	}
}