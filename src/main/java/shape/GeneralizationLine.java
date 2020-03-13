package shape;

import port.Port;

import java.awt.*;

public class GeneralizationLine extends ConnectionLine {

	public GeneralizationLine(Port fromPort, Port toPort) {
		super(fromPort, toPort);
	}

	@Override
	protected void paintArrow(Graphics g) {
		Point relayPoint = this.getRelayPoint(this.getFromPort().getBorderPoint(), this.getToPort().getBorderPoint());
		Point[] verticalPoints = this.getVerticalPoints(relayPoint, this.getToPort().getBorderPoint());
		int[] xSet = {verticalPoints[0].x, verticalPoints[1].x, this.getToPort().getBorderPoint().x};
		int[] ySet = {verticalPoints[0].y, verticalPoints[1].y, this.getToPort().getBorderPoint().y};
		g.drawPolygon(new Polygon(xSet, ySet, 3));
	}
}