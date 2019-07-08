package port;

import shape.BasicObject;

import java.awt.*;

public class LeftPort extends Port {
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	@Override
	public Point getBorderPoint() {
		return new Point(x, y + height / 2);
	}

	@Override
	public void calibrateBound(BasicObject obj) {
		super.calibrateBound(obj);
		this.x = obj.getX();
		this.y = obj.getY() + obj.getHeight() / 2 - this.height / 2;
	}
}