package port;

import shape.BasicObject;
import shape.Shape;

import java.awt.*;

public class Port extends Shape {

	public Port() {
		height = 10;
		width = 10;
	}

	public Point getBorderPoint() {
		return null;
	}

	public Point getMidPoint() {
		return new Point(x + width / 2, y + height / 2);
	}

	public void calibrateBound(BasicObject obj) {

	}

}