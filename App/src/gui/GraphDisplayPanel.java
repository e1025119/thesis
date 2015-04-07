package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import logic_basics.*;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

@SuppressWarnings("serial")
public class GraphDisplayPanel extends JPanel {
	
	private DirectedSparseGraph<Argument,AttackRelation> g;
	private AF framework;
	private BasicVisualizationServer<Argument,AttackRelation> vs;
	
	public GraphDisplayPanel() {
		this.setLayout(new BorderLayout());
	}
	
	public void setGraph(AF framework) {
		this.framework = framework;
		g = new DirectedSparseGraph<Argument,AttackRelation>();
		for(Argument a : this.framework.getAr().getArguments()) {
			g.addVertex(a);
		}
		for(AttackRelation rel : this.framework.getAtt().getAttacks()) {
			g.addEdge(rel,rel.getA1(),rel.getA2());
		}
	    vs = new BasicVisualizationServer<Argument,AttackRelation>(new CircleLayout<Argument,AttackRelation>(g), new Dimension(520,700));
	    this.removeAll();
	    this.add(vs,BorderLayout.CENTER);
	}
}
