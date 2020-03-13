package port;

import shape.UMLBasicObject;

import java.awt.*;

public class DownPort extends Port {

	@Override
	public Point getBorderPoint() {
		return new Point(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight());
	}

	@Override
	public void calibrateBound(UMLBasicObject obj) {
		int x = obj.getX() + obj.getWidth() / 2 - this.getWidth() / 2;
		int y = obj.getY() + obj.getHeight() - this.getHeight();
		this.setPosition(new Point(x, y));
	}
}