package api;

import org.junit.jupiter.api.Test;

class DWGraph_AlgoTest {
	
	@Test
	void checkCopy() {
		directed_weighted_graph g = new DWGraph_DS();
		node_data n = new NodeData(0); 
		node_data n1 = new NodeData(1); 
		node_data n2 = new NodeData(2); 
		node_data n3 = new NodeData(3); 
		node_data n4 = new NodeData(4); 
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.connect(n.getKey(), n1.getKey(), 2);
		g.connect(n.getKey(), n3.getKey(), 4);
		g.connect(n2.getKey(), n3.getKey(), 5);
		System.out.println(g.getV());
	}



}
