package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;

import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import logic_basics.*;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

@SuppressWarnings("serial")
public class GraphDisplayPanel extends JPanel {

	private DirectedSparseGraph<Argument,AttackRelation> g;
	private AF framework;
	private BasicVisualizationServer<Argument,AttackRelation> vs;
	public enum ColorTab{AFF,EF};
	private ColorTab tab;

	public GraphDisplayPanel(ColorTab tab) {
		this.setLayout(new BorderLayout());
		this.tab = tab;
	}

	public void setGraph(AF framework) {
		if(framework == null) {
			this.removeAll();
			this.repaint();
			return;
		}
		/** nested class for node coloring **/
		Transformer<Argument,Paint> vertexColor = new Transformer<Argument,Paint>() {
			public Paint transform(Argument a) {
				if(tab.equals(ColorTab.EF)) {
					if(a.isPaintEF()) {
						return Color.GREEN;	
					}
				} else if(tab.equals(ColorTab.AFF)) {
					if(a.isPaintAFF()) {
						return Color.GREEN;
					}
				}
				return Color.RED;
			}
		};
		/** end of nested class **/

		/** nested class for node labeling **/
		Transformer<Argument,String> vertexLabel = new Transformer<Argument,String>() {
			public String transform(Argument a) {
				return a.getRef();
			}
		};
		/** end of nested class **/

		this.framework = framework;
		g = new DirectedSparseGraph<Argument,AttackRelation>();
		for(Argument a : this.framework.getAr().getArguments()) {
			g.addVertex(a);
		}
		for(AttackRelation rel : this.framework.getAtt().getAttacks()) {
			g.addEdge(rel,rel.getA1(),rel.getA2());
		}
		vs = new BasicVisualizationServer<Argument,AttackRelation>(new CircleLayout<Argument,AttackRelation>(g), new Dimension(500,700));
		vs.getRenderContext().setVertexLabelTransformer(vertexLabel);
		vs.getRenderContext().setVertexFillPaintTransformer(vertexColor);
		this.removeAll();
		this.add(vs,BorderLayout.CENTER);
	}
}
