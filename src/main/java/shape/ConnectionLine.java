package shape;

import port.Port;

import java.awt.*;

public class ConnectionLine extends Shape {
	private final double polygonSize = 300;
	Port fromPort, toPort;

	ConnectionLine(Port fromPort, Port toPort) {
		this.fromPort = fromPort;
		this.toPort = toPort;
		super.paintPriority = 3;
	}

	@Override
	public boolean isInside(Point mousePosition) {
		return false;
	}

	@Override
	public void paint(Graphics g) {
		paintLine(g);
		paintArrow(g);
	}

	private void paintLine(Graphics g) {
		int x1 = fromPort.getBorderPoint().x;
		int y1 = fromPort.getBorderPoint().y;
		Point relayPoint = getRelayPoint(fromPort.getBorderPoint(), toPort.getBorderPoint());
		int x2 = relayPoint.x;
		int y2 = relayPoint.y;
		g.drawLine(x1, y1, x2, y2);
	}

	protected void paintArrow(Graphics g) {
	}

	Point getRelayPoint(Point startPoint, Point endPoint) {
		double x1 = startPoint.x;
		double y1 = startPoint.y;
		double x3 = endPoint.x;
		double y3 = endPoint.y;
		double m = (y3 - y1) / (x3 - x1);
		double f = y1 - m * x1;

		double a = 1 + m * m;
		double b = -2 * x3 + 2 * m * f - 2 * m * y3;
		double c = x3 * x3 + f * f - 2 * f * y3 + y3 * y3 - polygonSize;
		double x21 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		double x22 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		double y21 = m * x21 + f;
		double y22 = m * x22 + f;

		if (Point.distance(x1, y1, x21, y21) < Point.distance(x1, y1, x22, y22))
			return (new Point((int) x21, (int) y21));
		else
			return (new Point((int) x22, (int) y22));
	}

	Point[] getVerticalPoint(Point startPoint, Point endPoint) {
		double x1 = startPoint.x;
		double y1 = startPoint.y;
		double x3 = endPoint.x;
		double y3 = endPoint.y;
		double m1 = (y3 - y1) / (x3 - x1);
		x3 = startPoint.x;
		y3 = startPoint.y;

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
		double b = -2 * x3 + 2 * m * f - 2 * m * y3;
		double c = x3 * x3 + f * f - 2 * f * y3 + y3 * y3 - polygonSize / 2;
		double x21 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		double x22 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		double y21 = m * x21 + f;
		double y22 = m * x22 + f;
		return new Point[]{(new Point((int) x21, (int) y21)), (new Point((int) x22, (int) y22))};
	}
}
