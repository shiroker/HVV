package tests;

import junit.framework.TestCase;


import Interfaces.*;

public class GraphHVVTest2 extends TestCase {
	

	public void testingCreateV() 
	{

		
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("A");
		Vertex V3 = Vertex.createV("B");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		assertNotSame(V1, V3);

	
		V1 = Vertex.createV("A 2");
		V2 = Vertex.createV("A 2");
		V3 = Vertex.createV("B 2");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		assertNotSame(V1, V3);

		V1 = Vertex.createV("A,2");
		V2 = Vertex.createV("A,2");
		V3 = Vertex.createV("B,2");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		assertNotSame(V1, V3);

		V1 = Vertex.createV("");
		V2 = Vertex.createV("");
		V3 = Vertex.createV("B,2");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		assertNotSame(V1, V3);

		V1 = Vertex.createV(",.#");
		V2 = Vertex.createV(",.#");
		V3 = Vertex.createV("+#+*");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		assertNotSame(V1, V3);
	}


	public void testingCreateG() 
	{
		Vertex V1 = Vertex.createV("aName");
		GraphHVV G1 = GraphHVV.createG(V1);
		assertNotNull(G1);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(V1, G1.getVertexes().get(0));

		Vertex VS = Vertex.createV("Y@XXB%");
		GraphHVV G2 = GraphHVV.createG(VS);
		assertNotNull(G2);
		assertEquals(1, G2.getVertexes().size());
		assertEquals(VS, G2.getVertexes().get(0));

		Vertex VL = Vertex.createV("");
		GraphHVV G3 = GraphHVV.createG(VL);		assertNotNull(G3);
		assertEquals(1, G3.getVertexes().size());
		assertEquals(VL, G3.getVertexes().get(0));
	}


	public void testingAddVertex() {
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		GraphHVV G1 = GraphHVV.createG(V1);
		assertNotNull(G1);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		G1.addVertex(V2);
		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));

		Vertex VS1 = Vertex.createV("Y@B%");
		Vertex VS2 = Vertex.createV("Y@C%B");
		GraphHVV G2 = GraphHVV.createG(VS2);
		assertNotNull(G2);
		assertEquals(1, G2.getVertexes().size());
		assertEquals(true, G2.getVertexes().contains(VS2));
		G2.addVertex(VS1);
		assertTrue(2 == G2.getVertexes().size());
		assertEquals(true, G2.getVertexes().contains(VS1));
		assertEquals(true, G2.getVertexes().contains(VS2));

		Vertex VL1 = Vertex.createV("");
		Vertex VL2 = Vertex.createV("_");
		GraphHVV G3 = GraphHVV.createG(VL1);
		assertNotNull(G3);
		assertEquals(1, G3.getVertexes().size());
		assertEquals(true, G3.getVertexes().contains(VL1));
		G3.addVertex(VL2);
		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G3.getVertexes().contains(VL1));
		assertEquals(true, G3.getVertexes().contains(VL2));
	}


	public void testingDeleteVertex() {
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		GraphHVV G1 = GraphHVV.createG(V1);
		G1.addVertex(V2);
		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));
		
		G1.deleteVertex(V1);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(false, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));
		
		G1.addVertex(V1);
		G1.deleteVertex(V2);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(false, G1.getVertexes().contains(V2));

		
		G1.deleteVertex(Vertex.createV("nichtenthalten"));
		assertEquals(1, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(false, G1.getVertexes().contains(V2));

	}


	public void testingAddEdge() {
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		GraphHVV G1 = GraphHVV.createG(V1);
		G1.addVertex(V2);
		G1.addEdge(V1, V2);
		assertEquals(2, G1.getEdges().size());
		assertEquals(V1, G1.getEdges().get(0));
		assertEquals(V2, G1.getEdges().get(1));
		
	}



	 public void testingDeleteEdge() {
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 GraphHVV G1=GraphHVV.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	 G1.addEdge(V2, V1);
	
	
	 assertEquals(4,G1.getEdges().size());
	 assertEquals(V1,G1.getEdges().get(0));
	 assertEquals(V2,G1.getEdges().get(1));
	 assertEquals(V2,G1.getEdges().get(2));
	 assertEquals(V1,G1.getEdges().get(3));
	
	 G1.deleteEdge(V2, V1);
	 assertEquals(2,G1.getEdges().size());
	 assertEquals(V1,G1.getEdges().get(0));
	 assertEquals(V2,G1.getEdges().get(1));
	
	 G1.deleteEdge(V1, V2);
	 assertEquals(0,G1.getEdges().size());
	
	 }


	 public void testingSetAtE() {
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 GraphHVV G1=GraphHVV.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 G1.setValEdge(V1, V2,  22);
	 G1.setValEdge(V1, V2,  42);
	
	 G1.setValEdge(V2, V1, 22);
	 G1.setValEdge(V2, V1, 42);
	
	 G1.setValEdge(V1, V2, 21);
	
	
	 assertTrue(21 == G1.getValEdge(V1, V2));
	 assertTrue(null == G1.getValEdge(V2, V1));
	
	 }


	 public void testingSetAtV() {

		 
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 Vertex V3 = Vertex.createV("S");
	 GraphHVV G1=GraphHVV.createG(V1);
	
	 G1.setTranzitTime(V1,Bahn.createB("dummy"), 22);
	 G1.setTranzitTime(V1,Bahn.createB("dummy"), 42);
	 G1.setTranzitTime(V2,Bahn.createB("dummy") ,42);
	 G1.setTranzitTime(V1,Bahn.createB("dummy"), 21);
	
	 assertTrue(21 == G1.getTransitTime(V1,Bahn.createB("dummy")));
	 assertTrue(42 == G1.getTransitTime(V2,Bahn.createB("dummy")));
	 assertTrue(0 == G1.getTransitTime(V3,Bahn.createB("dummy")));
	 }



	public void testingExportG() {
	}

	public void testingImportG() {
	}
	
	public void testingGetIncident() {
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		GraphHVV G1 = GraphHVV.createG(V1);
		G1.addVertex(V2);
		G1.addVertex(V3);

		G1.addEdge(V1, V2);


		assertEquals(1, G1.getIncident(V2).size());
		assertEquals(V1, G1.getIncident(V2).get(0));


		assertEquals(0, G1.getIncident(V3).size());

	}

	public void testingGetAdjacent() {
		
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		GraphHVV G1 = GraphHVV.createG(V1);
		G1.addVertex(V2);
		G1.addVertex(V3);

		G1.addEdge(V1, V2);

		assertEquals(1, G1.getAdjacent(V1).size());
		assertEquals(V2, G1.getAdjacent(V1).get(0));

		
		assertEquals(0, G1.getAdjacent(V3).size());

		G1.addEdge(V1, V3);
		assertEquals(2, G1.getAdjacent(V1).size());
		assertEquals(V3, G1.getAdjacent(V1).get(1));

		//
	}


	 public void testingGetTarget() {
		 
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 Vertex V3=Vertex.createV("C");
	 GraphHVV G1=GraphHVV.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 assertEquals(1, G1.getTarget(V1).size());
	 assertEquals(V2,G1.getTarget(V1).get(0));
	
	 assertEquals(0, G1.getTarget(V2).size());

     assertTrue(G1.getTarget(V3).isEmpty());
	 }



	 public void testingGetSource() {
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 Vertex V3=Vertex.createV("C");
	 GraphHVV G1=GraphHVV.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 assertEquals(1, G1.getSource(V2).size());
	 assertEquals(V1,G1.getSource(V2).get(0));
	
	 assertEquals(0, G1.getSource(V1).size());
     assertTrue(G1.getSource(V3).isEmpty());
	
	 }


	public void testingGetEdges() {
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		GraphHVV G1 = GraphHVV.createG(V1);
		GraphHVV G2 = GraphHVV.createG(V1);
		G1.addVertex(V2);
		G2.addVertex(V2);

		G1.addEdge(V1, V2);

		assertEquals(2, G1.getEdges().size());
		assertEquals(true, G1.getEdges().contains(V1));
		assertEquals(true, G1.getEdges().contains(V2));
		assertEquals(false, G1.getEdges().contains(V3));
		assertEquals(0, G2.getEdges().size());

	}

	public void testingGetVertexes() {
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		GraphHVV G1 = GraphHVV.createG(V1);
		G1.addVertex(V2);

		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));
		assertEquals(false,G1.getVertexes().contains(V3));
	}


	 public void testingGetValE() {
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 GraphHVV G1=GraphHVV.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 G1.setValEdge(V1, V2, 22);
	 G1.setValEdge(V1, V2, 42);
	
	 G1.setValEdge(V2, V1, 22);
	 G1.setValEdge(V2, V1,  42);
	
	 G1.setValEdge(V1, V2, 21);
	

	 assertTrue(21 == G1.getValEdge(V1, V2));
	 assertTrue(null == G1.getValEdge(V2, V1));
	 assertTrue(null == G1.getValEdge(V1, V1));
	 }

	 public void testingGetValV() {
		// wie bei testingSetValV
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	
	 GraphHVV G1=GraphHVV.createG(V1);
	 
	 G1.setTranzitTime(V1,Bahn.createB("dummy"), 22);
	 G1.setTranzitTime(V1,Bahn.createB("dummy"), 42);
	 G1.setTranzitTime(V2, Bahn.createB("dummy"),42);
	 G1.setTranzitTime(V1, Bahn.createB("dummy"),21);
	 
	 assertEquals(21,(int) G1.getTransitTime(V1,Bahn.createB("dummy")));
	 assertEquals(42,(int) G1.getTransitTime(V2,Bahn.createB("dummy")));
	 }

}
