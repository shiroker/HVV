package implementationen;

import java.util.ArrayList;
import java.util.Arrays;

import Interfaces.Edge;
import Interfaces.Vertex;

public class EdgeImpl implements Edge {

	private Vertex target, source;
	private Integer attribute;
	
	
	private EdgeImpl(Vertex source, Vertex target){
		this.target = target;
		this.source = source;
		this.attribute = null;
	}
	
	public static EdgeImpl createE(Vertex source, Vertex target){
		return new EdgeImpl(source, target);
	}
	
	@Override
	public Integer getAttribute() {
		return this.attribute;
	}

	@Override
	public void setAttribute(Integer attr) {
		
		this.attribute = attr;

	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Edge))return false;
		
		Edge that =(Edge) other;
			if(!this.target.equals(that) || this.source.equals(that) || this.attribute != that.getAttribute()){
				return false;}
		
		
		return true;
		
	}
	
	@Override
	public String toString(){
		return this.target.getName() + " -> " + this.source.getName() ;
	}

	
	@Override
	public Vertex getTarget() {		return this.target;}

	@Override
	public Vertex getSource() {		return this.source;}

	@Override
	public ArrayList<Vertex> getVertexes() {
		
		return new ArrayList<Vertex>(Arrays.asList(this.source, this.target));
	}
	

	

}
