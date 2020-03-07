package port;

import shape.BasicObject;

import java.awt.*;

public class RightPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.x + this.width, this.y + this.height / 2);
	}

	@Override
	public void calibrateBound(BasicObject obj) {
		super.calibrateBound(obj);
		this.x = obj.getX() + obj.getWidth() - this.width;
		this.y = obj.getY() + obj.getHeight() / 2 - this.height / 2;
	}
}