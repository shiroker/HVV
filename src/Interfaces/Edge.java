package Interfaces;

import java.util.ArrayList;

import implementationen.EdgeImpl;

public interface Edge {
	
	 static Edge createE(Vertex source, Vertex target){
		return  EdgeImpl.createE(source, target);
	}
	
	public Integer getAttribute();

	
	public void setAttribute(Integer attr);
	
	public Vertex getTarget();
	
	public Vertex getSource();
	
	public ArrayList<Vertex> getVertexes();
	
	
}


