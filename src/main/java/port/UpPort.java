package port;

import shape.BasicObject;

import java.awt.*;

public class UpPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.getX() + this.getWidth() / 2, this.getY());
	}

	@Override
	public void calibrateBound(BasicObject obj) {
		int x = obj.getX() + obj.getWidth() / 2 - this.getWidth() / 2;
		int y = obj.getY();
		this.setPosition(new Point(x, y));
	}
}