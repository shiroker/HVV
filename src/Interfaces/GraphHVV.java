package Interfaces;

import java.util.ArrayList;

import implementationen.GraphHVVImpl;

public interface GraphHVV {
	
	public static GraphHVV createG(Vertex v){
		return GraphHVVImpl.createG(v);
	}
	
	public void addVertex(Vertex v);
	
	void deleteVertex(Vertex v);
	
	void addEdge(Vertex source, Vertex target);
	
	void deleteEdge(Vertex source, Vertex target);
	
	void setValEdge(Vertex source, Vertex target, Integer value);
	
	void setTranzitTime(Vertex v2,Bahn b, Integer time);
	
	public Integer getValEdge(Vertex source, Vertex target);
	
	public Integer getTransitTime(Vertex v, Bahn b);
	
	public Integer getTravelTime(Vertex source, Vertex target);
	
	public ArrayList<Vertex> getSource(Vertex v);
	
	public ArrayList<Vertex> getTarget(Vertex v);
	
	public ArrayList<Vertex> getAdjacent(Vertex v);
	
	public ArrayList<Vertex> getIncident(Vertex v);
	
	public ArrayList<Vertex> getEdges();
	
	ArrayList<Vertex> getVertexes();
	
	public static GraphHVV importG(String filename){
	
		return GraphHVVImpl.importG(filename);	
	}
	
	void exportG(String filename, String gerichtet);
	
	void printG(String filename);
	
	void insertConnectionTrainsOrBusses(String filename);

}
