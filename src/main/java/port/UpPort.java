package port;

import shape.UMLBasicObject;

import java.awt.*;

public class UpPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.getX() + this.getWidth() / 2, this.getY());
	}

	@Override
	public void calibrateBound(UMLBasicObject obj) {
		int x = obj.getX() + obj.getWidth() / 2 - this.getWidth() / 2;
		int y = obj.getY();
		this.setPosition(new Point(x, y));
	}
}