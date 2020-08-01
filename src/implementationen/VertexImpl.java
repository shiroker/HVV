package implementationen;

import java.util.ArrayList;

import Interfaces.Bahn;
import Interfaces.Vertex;

public class VertexImpl implements Vertex{
	

	private String _name;
	private ArrayList<Bahn> anschlussZuge;
	private ArrayList<Integer> umstiegsZeit;
	
	private VertexImpl(String name){
		this._name = name;
		this.anschlussZuge = new ArrayList<Bahn>();
		this.umstiegsZeit = new ArrayList<Integer>();
		
	}
	
	public static VertexImpl createV(String name){
		return new VertexImpl(name);
	}
	

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		this._name = name;
		
	}
	
	@Override
	public Integer getTranzitTime(Bahn zuBahnX){
		int result = 0;
		if(this.anschlussZuge.contains(zuBahnX)){
			for(int index =0; index <this.anschlussZuge.size(); index++){
				if(this.anschlussZuge.get(index).equals(zuBahnX)) {
					result = this.umstiegsZeit.get(index);
				}
			}
			
		}
		
		return result;
	}
	
	
	@Override
	public void setTranzitTime(Bahn bahn,Integer time){
		if(!this.anschlussZuge.contains(bahn)){
			this.anschlussZuge.add(bahn);
			this.umstiegsZeit.add(time);
		}
		else{
			for(int index = 0; index < this.anschlussZuge.size(); index++){
				if(bahn.equals(this.anschlussZuge.get(index))){
					this.umstiegsZeit.set(index, time);
				}
			}
		}
		
		
		
		
	}
	
	

	@Override
	public boolean equals(Object object){
		
		if(object instanceof VertexImpl ){
			
			VertexImpl other = (VertexImpl) object;
			if(!other.getName().equals(this.getName()))	return false;
			
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}

	@Override
	public ArrayList<Bahn> getAllAnschluesse() {
		
		ArrayList<Bahn> out = new ArrayList<Bahn>();
		this.anschlussZuge.stream().forEach(elem->{
			out.add(elem);
			}
		);
		
				
		return out;
	}

	@Override
	public void setAnschluss(Bahn welchBahn, Integer umstiegsZeit) {
		if(!this.anschlussZuge.contains(welchBahn)){
			this.anschlussZuge.add(welchBahn);
			this.umstiegsZeit.add(umstiegsZeit);
		}
		
	}

	
	
	

}
