package shape;

import java.awt.*;

public class UseCase extends BasicObject {

	public UseCase(Point point) {
		super(point);
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(this.getX(), this.getY(), super.width, super.height);
	}
}
