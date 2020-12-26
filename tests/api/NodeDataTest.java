package api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.Node;

class NodeDataTest {
	
	
	@Test
	void testKey() {
	node_data n = new NodeData(4);
	assertEquals(4, n.getKey());
	node_data n2 = new NodeData("b",5);
	assertEquals(5, n2.getKey());
	node_data n3 = new NodeData();
	assertEquals(0,n3.getKey());
	}
	
	
	

}
