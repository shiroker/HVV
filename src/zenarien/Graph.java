package zenarien;



import java.util.ArrayList;
import java.util.stream.Collectors;

import Interfaces.Bahn;
import Interfaces.Edge;
//import Interfaces.Edge;
import Interfaces.GraphHVV;
import Interfaces.Vertex;

public class Graph {

	
	
	
	
	
	public static void main(String[] args) {
		
		Vertex aeroport = Vertex.createV("Aeroport");
		Vertex ohlsdorf = Vertex.createV("Ohlsdorf");
		Vertex ruebenkamp = Vertex.createV("Ruebenkamp");
		Vertex hauptbahnHof = Vertex.createV("Hauptbahnhof");
		Vertex alterwoehr = Vertex.createV("AlterWoehr");
		Vertex sengelmannstrasse = Vertex.createV("Sengelmannstrasse");
		Vertex jungfernstieg = Vertex.createV("Jungfernstieg");
		Bahn bus234 = Bahn.createB("Bus234");
		
		
		
		GraphHVV gr = GraphHVV.createG(ohlsdorf);
		gr.addEdge(aeroport, ohlsdorf);
		gr.deleteEdge(aeroport, ohlsdorf);
		System.out.println(gr.getEdges());
		gr.addEdge(ohlsdorf, ruebenkamp);
		gr.addEdge(ohlsdorf, sengelmannstrasse);
		gr.addEdge(ruebenkamp, alterwoehr);
		gr.addEdge(alterwoehr, hauptbahnHof);
		gr.addEdge(hauptbahnHof, jungfernstieg);
		gr.setValEdge(ohlsdorf, ruebenkamp, 3);
		gr.setValEdge(ruebenkamp, alterwoehr, 2);
		gr.setValEdge(ohlsdorf, sengelmannstrasse, 3);
		gr.setValEdge(hauptbahnHof,jungfernstieg, 5);
		gr.setValEdge(jungfernstieg, Vertex.createV("Stadthausbruecke"), 3);//es gibt keine Kante
		Bahn b = Bahn.createB("S1"); 
		gr.setTranzitTime(ohlsdorf, b, 333333);
		gr.setTranzitTime(jungfernstieg, b, 233);
		gr.setTranzitTime(sengelmannstrasse, bus234, 987);
		gr.setTranzitTime(jungfernstieg, bus234, 5686565);
		
		
		System.out.println("---------------------------Graph-IMPORT----------------------------");
		
		GraphHVV gr1 = GraphHVV.importG("C:/Users/pc/Desktop/Programming/AllcodedProjects/Java/HVVGraph/hhSbahn.graph");
		gr1.getVertexes();
		gr.insertConnectionTrainsOrBusses("C:/Users/pc/Desktop/Programming/AllcodedProjects/Java/HVVGraph/Anschluesse_stationen.graph");
		
		System.out.println(ohlsdorf.getAllAnschluesse());
		
		
	
		
		
		
	
		
		
		
		
		
		

	}

}
