package shape;

import java.awt.*;

public class BasicClass extends BasicObject {

	public BasicClass(Point point) {
		super(point);
	}

	@Override
	public void paintShape(Graphics g) {
		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		int height = this.getHeight();

		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.drawLine(x, y + height / 3, x + width, y + height / 3);
		g.drawLine(x, y + height / 3 * 2, x + width, y + height / 3 * 2);
	}
}
