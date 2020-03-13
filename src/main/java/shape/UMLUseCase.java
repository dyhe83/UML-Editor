package shape;

import java.awt.*;

public class UMLUseCase extends UMLBasicObject {

	public UMLUseCase(Point point) {
		super(point);
	}

	@Override
	public void paintShape(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}