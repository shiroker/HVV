package Interfaces;

import java.util.ArrayList;

import implementationen.BahnImpl;



public interface Bahn {
	
	public static Bahn createB(String name){
		return  BahnImpl.createB(name);
	}
	
	public String getName();
	
	public void setStation(Vertex v);
	
	public ArrayList<Vertex> getAllStationen();

}
