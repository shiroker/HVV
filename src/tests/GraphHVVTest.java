package tests;

import java.util.Collections;


import Interfaces.*;
import Interfaces.Vertex;
import junit.framework.TestCase;


public class GraphHVVTest extends TestCase{
	public void testCreate(){
	Vertex v1 = Vertex.createV("v");
	GraphHVV g = GraphHVV.createG(v1);
	
	assertTrue(g.getVertexes().contains(v1) && g.getVertexes().size() == 1);
}


public void testAddVertex() 
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	GraphHVV g = GraphHVV.createG(v1);	
	assertTrue(!g.getVertexes().contains(v2)); //Knoten darf noch nicht drin sein
	g.addVertex(v2);
	assertTrue(g.getVertexes().contains(v2)); //Knoten muss drin sein
	g.addVertex(v2);
	assertTrue(1 == Collections.frequency(g.getVertexes(), v2)); //Knoten darf nicht doppelt vorkommen
}


public void testDeleteVertex()
{
	Vertex v = Vertex.createV("v");
	GraphHVV g = GraphHVV.createG(v);
	g.deleteVertex(v);
	assertTrue(g.getVertexes().contains(v)); //den letzten Knoten duerfen wir nicht loeschen
	
	Vertex v2 = Vertex.createV("v2");
	g.addVertex(v2);
	g.deleteVertex(v2);
	assertTrue(!g.getVertexes().contains(v2));
}


public void testAddEdge()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	GraphHVV g = GraphHVV.createG(v1);	
	g.addEdge(v1, v2);
	assertTrue(g.getEdges().get(0).equals(v1) && g.getEdges().get(1).equals(v2));
}


public void testDeleteEdge()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	GraphHVV g = GraphHVV.createG(v1);	
	g.addEdge(v1, v2);
	assertTrue(g.getEdges().get(0).equals(v1));
	g.deleteEdge(v1, v2);
	assertTrue(g.getEdges().isEmpty());
}


public void testSetAtE()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	GraphHVV g = GraphHVV.createG(v1);	
	g.addEdge(v1, v2);
	g.setValEdge(v1, v2, 50);
	assertTrue(50 == g.getValEdge(v1, v2));
	g.setValEdge(v1, v2 , 25);
	assertTrue(25 == g.getValEdge(v1, v2));
}


public void testSetAtV()
{
	Vertex v1 = Vertex.createV("v1");
	GraphHVV g = GraphHVV.createG(v1);	
	g.setTranzitTime(v1,Bahn.createB("S1"), 10);
	assertTrue(10 == g.getTransitTime(v1,Bahn.createB("S1")));
	g.setTranzitTime(v1, Bahn.createB("S1"),30);
	assertTrue(30 == g.getTransitTime(v1, Bahn.createB("S1")));
}





public void testGetTarget()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	Vertex v3 = Vertex.createV("v3");
	Vertex v4 = Vertex.createV("v4");
	GraphHVV g = GraphHVV.createG(v1);	
	g.addEdge(v1, v2);
	g.addEdge(v1, v3);
	g.addEdge(v4, v1);
	
	assertTrue(g.getTarget(v1).get(0).equals(v2));
	assertTrue(g.getTarget(v1).get(1).equals(v3));
	assertTrue(g.getTarget(v4).contains(v1));
}


public void testGetSource()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	Vertex v3 = Vertex.createV("v3");
	Vertex v4 = Vertex.createV("v4");
	GraphHVV g = GraphHVV.createG(v1);
	
	g.addEdge(v1, v2);
	g.addEdge(v1, v3);
	g.addEdge(v4, v1);
	
	assertTrue(g.getSource(v2).contains(v1));
	assertTrue(g.getSource(v3).contains(v1));
	assertTrue(g.getSource(v1).get(0).equals(v4));
}


public void testGetEdges()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v7 = Vertex.createV("v7");
	Vertex v3 = Vertex.createV("v3");
	Vertex v4 = Vertex.createV("v4");
	GraphHVV g = GraphHVV.createG(v1);	
	g.addEdge(v1, v7);
	g.addEdge(v1, v3);
	g.addEdge(v4, v1);
	
	assertTrue(g.getEdges().get(0).equals(v1));
	assertTrue(g.getEdges().get(1).equals(v7));
	assertTrue(g.getEdges().get(2).equals(v1));
	assertTrue(g.getEdges().get(3).equals(v3));
	assertTrue(g.getEdges().get(4).equals(v4));
	assertTrue(g.getEdges().get(5).equals(v1));
}


public void testGetVertexes()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	GraphHVV g = GraphHVV.createG(v1);	
	g.addVertex(v2);
	
	assertTrue(g.getVertexes().contains(v1));
	assertTrue(g.getVertexes().contains(v2));
}


public void testGetValE()
{
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	GraphHVV g = GraphHVV.createG(v1);	
	
	g.addEdge(v1, v2);
	assertTrue(g.getValEdge(v1, v2) == null);
	g.setValEdge(v1, v2, 10);
	assertTrue(g.getValEdge(v1, v2) == 10);
}


public void testGetValV()
{
	Vertex v1 = Vertex.createV("v1");
	GraphHVV g = GraphHVV.createG(v1);	
	
	assertTrue(g.getTransitTime(v1, Bahn.createB("dummy")) == 0);
	g.setTranzitTime(v1,Bahn.createB("dummy"), 5);
	assertTrue(g.getTransitTime(v1,Bahn.createB("dummy")) == 5);
	g.setTranzitTime(v1, Bahn.createB("dummy"),10);
	assertTrue(g.getTransitTime(v1,Bahn.createB("dummy")) == 10);
}


public void testEqual()
{
	
	Vertex v1 = Vertex.createV("v1");
	Vertex v2 = Vertex.createV("v2");
	Vertex v3 = Vertex.createV("v3");
	Vertex v4 = Vertex.createV("v4");
	GraphHVV g = GraphHVV.createG(v1);	
	
	g.addEdge(v1, v2);
	g.addEdge(v1, v3);
	g.addEdge(v4, v1);
	
	Vertex v5 = Vertex.createV("v1");
	Vertex v6 = Vertex.createV("v2");
	Vertex v7 = Vertex.createV("v3");
	Vertex v8 = Vertex.createV("v4");
	GraphHVV g2 = GraphHVV.createG(v5);	
	
	g2.addEdge(v5, v7);
	g2.addEdge(v8, v5);
	g2.addEdge(v5, v6);
	
	//assertTrue(g.equal(g2));
}

}
