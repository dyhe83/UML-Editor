package shape;

import port.*;

import java.awt.*;

public class BasicObject extends Shape {

	private Port[] ports = {new UpPort(), new DownPort(), new LeftPort(), new RightPort()};

	public BasicObject(Point point) {
		super(point);
		paintPriority = 2;
	}

	// protected void calibratePort() {
	// //Up
	// ports[0].x = this.x + this.width / 2 - ports[0].width / 2;
	// ports[0].y = this.y;
	//
	// //Down
	// ports[1].x = this.x + this.width / 2 - ports[1].width / 2;
	// ports[1].y = this.y + this.height - ports[1].height;
	//
	// //Left
	// ports[2].x = this.x;
	// ports[2].y = this.y + this.height / 2 - ports[2].height / 2;
	//
	// //Right
	// ports[3].x = this.x + this.width - ports[3].width;
	// ports[3].y = this.y + this.height / 2 - ports[3].height / 2;
	// }

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
