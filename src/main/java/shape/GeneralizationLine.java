package shape;

import port.Port;

import java.awt.*;

public class GeneralizationLine extends ConnectionLine {

	public GeneralizationLine(Port fromPort, Port toPort) {
		super(fromPort, toPort);
	}

	@Override
	protected void paintArrow(Graphics g) {
		Point relayPoint = this.getRelayPoint(this.fromPort.getBorderPoint(), this.toPort.getBorderPoint());
		Point[] point = this.getVerticalPoint(relayPoint, this.toPort.getBorderPoint());
		int[] xSet = {point[0].x, point[1].x, this.toPort.getBorderPoint().x};
		int[] ySet = {point[0].y, point[1].y, this.toPort.getBorderPoint().y};
		g.drawPolygon(new Polygon(xSet, ySet, 3));
	}
}
