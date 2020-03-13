package shape;

import port.Port;

import java.awt.*;

public class CompositionLine extends ConnectionLine {

	public CompositionLine(Port fromPort, Port toPort) {
		super(fromPort, toPort);
	}

	@Override
	public void paintArrow(Graphics g) {
		int x3 = this.getToPort().getBorderPoint().x;
		int y3 = this.getToPort().getBorderPoint().y;
		Point relayPoint = this.getRelayPoint(this.getFromPort().getBorderPoint(), this.getToPort().getBorderPoint());
		int x2 = relayPoint.x;
		int y2 = relayPoint.y;

		Point middlePoint = new Point((x2 + x3) / 2, (y2 + y3) / 2);
		Point[] point = this.getVerticalPoints(middlePoint, this.getToPort().getBorderPoint());
		int[] xSet = {x2, point[0].x, x3, point[1].x};
		int[] ySet = {y2, point[0].y, y3, point[1].y};
		g.drawPolygon(new Polygon(xSet, ySet, 4));
	}
}