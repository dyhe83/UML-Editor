package shape;

import port.*;

import java.awt.*;

public abstract class BasicObject extends Shape {

	private Port[] ports = {new UpPort(), new DownPort(), new LeftPort(), new RightPort()};

	public BasicObject() {
		this.setPaintPriority(2);
	}

	public BasicObject(Point point) {
		super(point);
		this.setPaintPriority(2);
	}

	@Override
	public void paint(Graphics g) {
		this.paintShape(g);
		if (this.isSelected()) {
			this.paintPort(g);
		}
		this.paintName(g);
	}

	protected abstract void paintShape(Graphics g);

	protected void paintPort(Graphics g) {
		for (Port port : this.ports) {
			port.calibrateBound(this);
			port.paint(g);
		}
	}

	protected void paintName(Graphics g) {
		g.drawString(this.getName(), this.getX(), this.getY() + this.getHeight() / 2);
	}

	@Override
	public Port getClosestPort(Point point) {
		Port closestPort = null;
		double minDis = Double.MAX_VALUE;
		for (Port port : this.ports) {
			port.calibrateBound(this);
			Point midPoint = port.getMidPoint();
			double dis = Point.distance(point.x, point.y, midPoint.x, midPoint.y);
			if (dis < minDis) {
				minDis = dis;
				closestPort = port;
			}
		}

		if (closestPort == null) {
			throw new NullPointerException();
		}

		return closestPort;
	}
}