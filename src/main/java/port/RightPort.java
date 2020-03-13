package port;

import shape.BasicObject;

import java.awt.*;

public class RightPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.getX() + this.width, this.getY() + this.height / 2);
	}

	@Override
	public void calibrateBound(BasicObject obj) {
		int x = obj.getX() + obj.getWidth() - this.width;
		int y = obj.getY() + obj.getHeight() / 2 - this.height / 2;
		this.setPosition(new Point(x, y));
	}
}