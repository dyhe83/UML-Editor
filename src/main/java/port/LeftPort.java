package port;

import shape.UMLBasicObject;

import java.awt.*;

public class LeftPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.getX(), this.getY() + this.getHeight() / 2);
	}

	@Override
	public void calibrateBound(UMLBasicObject obj) {
		int x = obj.getX();
		int y = obj.getY() + obj.getHeight() / 2 - this.getHeight() / 2;
		this.setPosition(new Point(x, y));
	}
}