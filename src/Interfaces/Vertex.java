package Interfaces;


import java.util.ArrayList;

import implementationen.VertexImpl;

public interface Vertex {
	
	// a Vertex presents a U-, S- oder Bus-Station.
	
	public static Vertex createV(String name){
		return VertexImpl.createV(name);
	}
	
	public String getName();
	
	public void setName(String name);
	
	public Integer getTranzitTime(Bahn zuBahnX);
	
	public void setTranzitTime(Bahn bahn, Integer time);
	
	
	
	public ArrayList<Bahn> getAllAnschluesse();
	
	public void setAnschluss(Bahn welchBahn, Integer umstiegsZeit);

	

}
