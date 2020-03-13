package port;

import shape.BasicObject;

import java.awt.*;

public class DownPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.getX() + this.width / 2, this.getY() + this.height);
	}

	@Override
	public void calibrateBound(BasicObject obj) {
		int x = obj.getX() + obj.getWidth() / 2 - this.width / 2;
		int y = obj.getY() + obj.getHeight() - this.height;
		this.setPosition(new Point(x, y));
	}
}