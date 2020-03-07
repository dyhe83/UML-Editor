package port;

import shape.BasicObject;

import java.awt.*;

public class DownPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.x + this.width / 2, this.y + this.height);
	}

	@Override
	public void calibrateBound(BasicObject obj) {
		this.x = obj.getX() + obj.getWidth() / 2 - this.width / 2;
		this.y = obj.getY() + obj.getHeight() - this.height;
	}
}