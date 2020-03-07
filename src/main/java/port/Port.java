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
		return new Point(this.x + this.width / 2, this.y + this.height / 2);
	}

	public void calibrateBound(BasicObject obj) {

	}
}