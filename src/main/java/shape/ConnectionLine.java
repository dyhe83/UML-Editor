package shape;

import lombok.Getter;
import lombok.Setter;
import port.Port;

import java.awt.*;

@Getter
@Setter
public abstract class ConnectionLine extends UMLShape {
	private static final double POLYGON_SIZE = 300;
	private Port fromPort;
	private Port toPort;

	public ConnectionLine(Port fromPort, Port toPort) {
		this.setFromPort(fromPort);
		this.setToPort(toPort);
		this.setPaintPriority(3);
	}

	@Override
	public boolean isInside(Point mousePosition) {
		return false;
	}

	@Override
	public void paint(Graphics g) {
		this.paintLine(g);
		this.paintArrow(g);
	}

	private void paintLine(Graphics g) {
		int x1 = this.fromPort.getBorderPoint().x;
		int y1 = this.fromPort.getBorderPoint().y;
		Point relayPoint = this.getRelayPoint(this.fromPort.getBorderPoint(), this.toPort.getBorderPoint());
		int x2 = relayPoint.x;
		int y2 = relayPoint.y;
		g.drawLine(x1, y1, x2, y2);
	}

	protected abstract void paintArrow(Graphics g);

	protected Point getRelayPoint(Point startPoint, Point endPoint) {
		double x1 = startPoint.x;
		double y1 = startPoint.y;
		double x3 = endPoint.x;
		double y3 = endPoint.y;
		double m = (y3 - y1) / (x3 - x1);
		double f = y1 - m * x1;

		double a = 1 + m * m;
		double b = -2 * x3 + 2 * m * f - 2 * m * y3;
		double c = x3 * x3 + f * f - 2 * f * y3 + y3 * y3 - POLYGON_SIZE;
		double d = Math.sqrt(b * b - 4 * a * c);
		double x21 = (-b + d) / (2 * a);
		double x22 = (-b - d) / (2 * a);
		double y21 = m * x21 + f;
		double y22 = m * x22 + f;

		if (startPoint.distance(x21, y21) < startPoint.distance(x22, y22)) {
			return (new Point((int) x21, (int) y21));
		} else {
			return (new Point((int) x22, (int) y22));
		}
	}

	protected Point[] getVerticalPoints(Point startPoint, Point endPoint) {
		double x1 = startPoint.x;
		double y1 = startPoint.y;
		double x3 = endPoint.x;
		double y3 = endPoint.y;
		double m1 = (y3 - y1) / (x3 - x1);

		if (m1 > 0.8) {
			m1 = 0.8;
		} else if (m1 < -0.8) {
			m1 = -0.8;
		} else if (m1 < 0.2) {
			m1 = 0.2;
		} else if (m1 > -0.2) {
			m1 = -0.2;
		} else if (Double.isNaN(m1)) {
			m1 = 0.2;
		}

		double m = -1 / m1;
		double f = startPoint.y - m * startPoint.x;

		double a = 1 + m * m;
		double b = -2 * x1 + 2 * m * f - 2 * m * y1;
		double c = x1 * x1 + f * f - 2 * f * y1 + y1 * y1 - POLYGON_SIZE / 2;
		double d = Math.sqrt(b * b - 4 * a * c);
		double x21 = (-b + d) / (2 * a);
		double x22 = (-b - d) / (2 * a);
		double y21 = m * x21 + f;
		double y22 = m * x22 + f;
		return new Point[]{(new Point((int) x21, (int) y21)), (new Point((int) x22, (int) y22))};
	}
}
