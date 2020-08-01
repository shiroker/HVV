package implementationen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import Interfaces.Bahn;
import Interfaces.Edge;
import Interfaces.GraphHVV;
import Interfaces.Vertex;

public class GraphHVVImpl implements GraphHVV {

	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> helpList;


	public static GraphHVVImpl createG(Vertex v) {
		return new GraphHVVImpl(v);
	}

	private GraphHVVImpl(Vertex v) {

		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		helpList = new ArrayList<Vertex>();
		

		addVertex(v);
	}

	@Override
	public void addVertex(Vertex v) {
		
			if(!this.vertices.contains(v)) this.vertices.add(v);
		
	}
	
	private ArrayList<Vertex> getVertices(){
	
		
		
		return this.vertices;
		
	}

	@Override
	public void deleteVertex(Vertex v) {
		
		// Achtung ! -> negative Agfrage hier unten
		
		if( this.vertices.size() != 1){
			 
		
			ArrayList<Edge> edgeResult = (ArrayList<Edge>) this.edges.stream()
					.filter(elem -> !(elem.getTarget().equals(v) || elem.getSource().equals(v)))
					.collect(Collectors.toList());
			this.edges = edgeResult;
		
		
		ArrayList<Vertex> vertexResult = (ArrayList<Vertex>) this.vertices.stream()
				.filter(elem -> !elem.equals(v))
				.collect(Collectors.toList());
		this.vertices = vertexResult;}
		
	}

	@Override
	public void addEdge(Vertex source, Vertex target) {
		addVertex(source);
		addVertex(target);
		Edge edge = Edge.createE(source, target);
				
				this.edges.add(edge);
		
		

	}
	
	@Override
	public ArrayList<Vertex> getEdges(){
		
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		for(Edge ed: this.edges){
			result.add(ed.getSource());
			result.add(ed.getTarget());
			
		}
		return result;
	}

	@Override
	public void deleteEdge(Vertex source, Vertex target) {
		
		ArrayList<Edge> result = (ArrayList<Edge>) this.edges.stream()
				.filter(elem -> !(elem.getTarget().equals(target) && elem.getSource().equals(source)))
				.collect(Collectors.toList());
		
		this.edges = result;
	
}

	@Override
	public Integer getTransitTime(Vertex v, Bahn b) {
		
			return v.getTranzitTime(b);
		
	}

	@Override
	public Integer getTravelTime(Vertex source, Vertex target) {
		
		for(Edge e: this.edges){
			if(e.getTarget().equals(target) && e.getSource().equals(source)){
				return e.getAttribute();
			}
		}
		return null;
	}

	@Override
	public ArrayList<Vertex> getSource(Vertex v) {
		ArrayList<Vertex> result = new ArrayList<>() ;
		if(!getEdges().contains(v)) return result;
		try {
			
			for(Edge e: this.edges){
				if(e.getTarget().equals(v) && !result.contains(e.getSource())){
					result.add(e.getSource());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Vertex> getTarget(Vertex v) {
		ArrayList<Vertex> result = new ArrayList<>() ;
		if(!getEdges().contains(v)) return result;
		
		try {
			
			for(Edge e: this.edges){
				if(e.getSource().equals(v) && !result.contains(e.getTarget())){
					result.add(e.getTarget());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void setValEdge(Vertex source, Vertex target, Integer value) {
		for(Edge e: this.edges){
			if(e.getTarget().equals(target) && e.getSource().equals(source)){
				e.setAttribute(value);
			}
		}

	}
	

	@Override
	public Integer getValEdge(Vertex source, Vertex target) {

		for(Edge e: this.edges){
			if(e.getTarget().equals(target) && e.getSource().equals(source)){
				return e.getAttribute();
			}
		}
		return null;
		
	}

	@Override
	public ArrayList<Vertex> getVertexes() {
		return getVertices();
	}

	@Override
	public void setTranzitTime(Vertex station,Bahn bahn, Integer time) {
		
		station.setTranzitTime(bahn, time);
		
	}

	@Override
	public ArrayList<Vertex> getAdjacent(Vertex v) {
		this.helpList.clear();
		if(getEdges().contains(v)){
			  searchMe(1, v);
		}
		return (ArrayList<Vertex>) this.helpList.stream().filter(e -> !e.equals(v)).collect(Collectors.toList());
		
	}
	
	@Override
	public ArrayList<Vertex> getIncident(Vertex v) {
		this.helpList.clear();
		if(getEdges().contains(v)){
			searchMe(2, v);
		}
		ArrayList<Vertex> result = (ArrayList<Vertex>) this.helpList.stream().filter(e -> !e.equals(v)).collect(Collectors.toList());
		return result;
	}

	private  void searchMe(int i, Vertex v) {
		
		if(i == 1){
		for(Vertex target: getTarget(v)){
			
			if(this.helpList.contains(target)){
				continue;
			}
			this.helpList.add(target);
			searchMe(1,target);}
		}
		else{
			for(Vertex source: getSource(v)){
				if(this.helpList.contains(source)){
					continue;
				}
				this.helpList.add(source);
				searchMe(2,source);
			}
		}
		
		
		
		
	}



	public static GraphHVV importG(String filename) {
       
		Vertex dummy = Vertex.createV("dummy");
		GraphHVV output = GraphHVV.createG(dummy);
		boolean gerichtet = false;
		BufferedReader reader = null;
		
		try {
			File file = new File(filename);
			 reader = new BufferedReader(new FileReader(file));
			    String line;
			    
			    if((line = reader.readLine()) != null) //erste Zeile lesen, ob es ungerichtet oder gerichtet
			    {
			    	if(line.toLowerCase().equals("#gerichtet"))
			    	{
			    		gerichtet = true;
			    	}
			    	else if(line.toLowerCase().equals("#ungerichtet"))
			    	{
			    		gerichtet = false;
			    	}
			    }
			    while((line = reader.readLine()) != null){
			    	
			    	String[] newline = line.split(", ");
			    	
			    	//die beiden U- oder S-Bahn Stationen
			    	Vertex v1 = Vertex.createV( newline[0]);
			    	Vertex v2 = Vertex.createV( newline[1]);
			    	// die Dauer zw. zwei Stationen 
			    	int adgeVal = Integer.parseInt(newline[2]);
			    	output.addEdge(v1, v2);
			    	output.setValEdge(v1, v2, adgeVal);
			    	
			    	
			    	if(!gerichtet){
			    		output.addEdge(v2, v1);
				    	output.setValEdge(v2, v1, adgeVal);
				    	}
			    }reader.close();
			    
		} catch (Exception e) {

			//e.printStackTrace();
		}
		finally{
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		output.deleteVertex(dummy);
		return output;
	}

	@Override
	public void exportG(String filename, String gerichtet) {
		try {
			File file = new File(filename);
			
			if(file.exists()){
				file = new File(file.getName() + "_new");
			}
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			ArrayList<Vertex> edges = this.getEdges(); 
			
			bw.write(gerichtet);
			bw.newLine();
			
			String line;
			for(int i =0; i<edges.size(); i++){
				line = edges.get(i).getName() + "," + edges.get(i+1).getName() + ","
						+ this.getValEdge(edges.get(i), edges.get(i+1)) + "," + edges.get(i).getAllAnschluesse();
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	public void insertConnectionTrainsOrBusses(String filename){
		
		BufferedReader reader = null;
		
		try {
			File file = new File(filename);
			 reader = new BufferedReader(new FileReader(file));
			    String line;
			    
			 
			    while((line = reader.readLine()) != null){
			    	
			    	String[] newline = line.split(", ");
			    	
			    	
			    	Vertex stationName = Vertex.createV( newline[0]);
			    	Bahn bahn = Bahn.createB( newline[1]);
			    	// die Dauer zw. zwei Stationen 
			    	int time = Integer.parseInt(newline[2]);
			    	
			    	
			    	for(Vertex vert: getVertexes()){
			    		if(vert.getName().equals(stationName.getName())){
			    			vert.setAnschluss(bahn,time);
			    			setTranzitTime(vert, bahn, time);
			    			
			    		}
			    	}
			    	
			    	
			    	
			    	
			    }reader.close();
			    
		} catch (Exception e) {

			//e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	@Override
	public void printG(String filename) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
