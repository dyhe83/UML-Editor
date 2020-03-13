package shape;

import port.Port;

import java.awt.*;

public class AssociationLine extends ConnectionLine {

	public AssociationLine(Port fromPort, Port toPort) {
		super(fromPort, toPort);
	}

	@Override
	public void paintArrow(Graphics g) {
		Point relayPoint = this.getRelayPoint(this.getFromPort().getBorderPoint(), this.getToPort().getBorderPoint());
		int x2 = relayPoint.x;
		int y2 = relayPoint.y;
		int x3 = this.getToPort().getBorderPoint().x;
		int y3 = this.getToPort().getBorderPoint().y;
		g.drawLine(x2, y2, x3, y3);
	}
}