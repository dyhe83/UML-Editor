package shape;

import java.awt.*;

public class BasicClass extends BasicObject {

	public BasicClass(Point point) {
		super(point);
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(super.x, super.y, super.width, super.height);
		g.drawLine(super.x, super.y + super.height / 3, super.x + super.width, super.y + super.height / 3);
		g.drawLine(super.x, super.y + super.height / 3 * 2, super.x + super.width, super.y + super.height / 3 * 2);
	}
}
