package shape;

import port.Port;

import java.awt.*;

public class AssociationLine extends ConnectionLine {

	public AssociationLine(Port fromPort, Port toPort) {
		super(fromPort, toPort);
	}

	@Override
	public void paintArrow(Graphics g) {
		Point relayPoint = this.getRelayPoint(this.fromPort.getBorderPoint(), this.toPort.getBorderPoint());
		int x2 = relayPoint.x;
		int y2 = relayPoint.y;
		int x3 = this.toPort.getBorderPoint().x;
		int y3 = this.toPort.getBorderPoint().y;
		g.drawLine(x2, y2, x3, y3);
	}
}
