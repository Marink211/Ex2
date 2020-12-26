package api;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class DWGraph_DSTest {
	
	@Test
	void testConnecntWithNull() {
		directed_weighted_graph g = new DWGraph_DS(); 
		node_data n = new NodeData("n",5);
		node_data m = new NodeData();
		g.addNode(n);
		g.addNode(m);
		assertEquals(2,g.getMC());
		assertEquals(2, g.nodeSize());
		g.connect(n.getKey(), m.getKey(), 2);
		assertEquals(3, g.getMC());
		assertEquals(1, g.edgeSize());
		g.removeNode(n.getKey());
		assertEquals(4,g.getMC());
		assertEquals(0,g.edgeSize());
		assertEquals(1,g.nodeSize());
		
	}
	@Test
	void testRemoveNode() {
		directed_weighted_graph g = new DWGraph_DS();
		node_data n1 = new NodeData("n1",5);
		node_data n2= new NodeData("n2",6);
		node_data n3 = new NodeData("n3",7);
		node_data n4 = new NodeData("n4",8);
		g.addNode(n1);
		g.connect(n1.getKey(), n2.getKey(), 2);
		System.out.println(g.nodeSize());
		assertEquals(1, g.nodeSize());
		g.addNode(n2);
		assertEquals(2, g.nodeSize());
		
		assertEquals(null, g.getEdge(n1.getKey(), n2.getKey()));
		g.connect(n1.getKey(), n2.getKey(), 2);
		assertEquals(null, g.getEdge(n2.getKey(), n1.getKey()));
		g.addNode(n3);
		g.connect(n1.getKey(), n3.getKey(), 4);
		assertEquals(2, g.edgeSize());
		g.addNode(n4);
		g.connect(n1.getKey(), n4.getKey(), 5);
		g.removeEdge(n1.getKey(),n2.getKey());
		assertEquals(null, g.getEdge(n1.getKey(),n2.getKey()));		
		g.connect(n2.getKey(), n1.getKey(), 3);
		System.out.println("print" + g.getE(n1.getKey()).toString());
		System.out.println(g.getV().toString());
		g.removeNode(n1.getKey()); 
		assertEquals(null, g.getEdge(n1.getKey(), n3.getKey()));
		
		
		
		
		
	}
	@Test 
	public void addEdge() {
		directed_weighted_graph g = new DWGraph_DS();
		node_data n1 = new NodeData("n1",5);
		node_data n2= new NodeData("n2",6);
		node_data n3 = new NodeData("n3",7);
		node_data n4 = new NodeData("n4",8);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.connect(n1.getKey(), n2.getKey(), 3);
		g.connect(n2.getKey(), n3.getKey(), 4);
		g.connect(n3.getKey(), n4.getKey(), 5);
		g.connect(n1.getKey(), n3.getKey(), 4);
		System.out.println(g.getEdge(n1.getKey(), n4.getKey()));
		System.out.println(g.getE(n1.getKey()));
		
	}



}
