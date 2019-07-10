package shape;

import port.*;

import java.awt.*;

public class BasicObject extends Shape {

	private Port[] ports = {new UpPort(), new DownPort(), new LeftPort(), new RightPort()};

	public BasicObject(Point point) {
		super(point);
		paintPriority = 2;
	}

	@Override
	protected void paintPort(Graphics g) {
		for (Port port : ports) {
			port.calibrateBound(this);
			g.fillRect(port.x, port.y, port.width, port.height);
		}
	}

	@Override
	public Port getClosestPort(Point point) {
		Port closestPort = null;
		double minDis = Double.MAX_VALUE;
		for (Port port : ports) {
			port.calibrateBound(this);
			Point midPoint = port.getMidPoint();
			double dis = Point.distance(point.x, point.y, midPoint.x, midPoint.y);
			if (dis < minDis) {
				minDis = dis;
				closestPort = port;
			}
		}
		if (closestPort == null)
			throw new NullPointerException();
		return closestPort;
	}
}
