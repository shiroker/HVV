package implementationen;

import java.util.ArrayList;

import Interfaces.Bahn;
import Interfaces.Vertex;

public class BahnImpl implements Bahn {
	
	private String name;
	private ArrayList<Vertex> allStationen;
	
	public static Bahn createB(String name){
		return  new BahnImpl(name);
	}
	
	private BahnImpl(String name){
		this.name = name;
		this.allStationen = new ArrayList<Vertex>();
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setStation(Vertex v) {
		this.allStationen.add(v);

	}

	@Override
	public ArrayList<Vertex> getAllStationen() {
		return this.allStationen;
	}
	
	@Override
	public boolean equals(Object object){
		boolean result = false; 
		if(object instanceof BahnImpl){
			Bahn other = (Bahn) object;
			result = other.getName().equals(this.name);
		}
		return result;
	}
	
	
	@Override
	public String toString(){
		return this.name;
	}

}
