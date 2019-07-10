package port;

import shape.BasicObject;

import java.awt.*;

public class UpPort extends Port {
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	@Override
	public Point getBorderPoint() {
		return new Point(x + width / 2, y);
	}

	public void calibrateBound(BasicObject obj) {
		this.x = obj.getX() + obj.getWidth() / 2 - this.width / 2;
		this.y = obj.getY();
	}
}